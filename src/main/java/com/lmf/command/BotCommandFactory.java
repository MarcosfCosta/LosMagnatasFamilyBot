package com.lmf.command;

import com.lmf.command.enums.CommandEnum;
import com.lmf.command.exceptions.CommandNotFoundException;
import com.lmf.config.ClashRoyaleServiceConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Data
public class BotCommandFactory extends ListenerAdapter {

    @Autowired
    private BotCommand botCommand;

    @Autowired
    private LocalLeaderboardCommand localLeaderboardCommand;


    private Map<CommandEnum, BotCommand> commandMap = new HashMap<CommandEnum, BotCommand>() {{
        //put(CommandEnum.LOCAL_LEADERBOARD, new LocalLeaderboardCommand());
    }};

    @PostConstruct
    public void initFactory() {
        commandMap.put(CommandEnum.LOCAL_LEADERBOARD, localLeaderboardCommand);
    }

   /* private static BotCommandFactory instance = null;

    private BotCommandFactory() {
    }

    public static synchronized BotCommandFactory getInstance() {
        if (instance == null) {
            instance = new BotCommandFactory();
        }
        return instance;
    }*/

//    public static Map<CommandEnum, BotCommand> getCommands() {
//        return commandMap;
//    }

    public Optional<BotCommand> getCommandEnumByName(String commandName) {
        return commandMap.entrySet().stream()
                         .filter(x -> x.getKey().getName().equals((CommandEnum.get(commandName)).get().getName()))
                         .map(x -> x.getValue())
                         .findFirst();

    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        try {
            botCommand = this.getCommandEnumByName(event.getName()).orElseThrow(() ->
                    new CommandNotFoundException());

            botCommand.execute(event);

        } catch (CommandNotFoundException e) {
            log.error(MessageFormat.format("Error command not found: {0}", e));
        } catch (Exception e) {
            log.error(MessageFormat.format("Error while trying to read the command: {0}", e));
        }
    }
}
