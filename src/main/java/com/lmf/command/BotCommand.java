package com.lmf.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.springframework.stereotype.Component;

@Component
public interface BotCommand {
    void execute(SlashCommandInteractionEvent event);
}
