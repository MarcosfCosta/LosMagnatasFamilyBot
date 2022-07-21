package com.lmf;

import com.lmf.command.BotCommandFactory;
import com.lmf.config.BotConfig;
import com.lmf.service.cache.CacheManager;
import com.lmf.service.job.JobManagerService;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
public class LMFConfiguration {

    protected JDA jda;

    @Autowired
    private BotCommandFactory botCommandFactory;
    @Autowired
    private BotConfig botConfig;
    @Autowired
    private JobManagerService jobManagerService;
    @Autowired
    private CacheManager cacheManager;

    public void initialize(){

        try {

            cacheManager.load();

            jobManagerService.initJobs();

            //TODO replace token by value from config
            jda = JDABuilder.createLight(botConfig.getBotToken(), Collections.emptyList())
                    .addEventListeners(botCommandFactory)
                    .build();

            botCommandFactory.getCommandMap().forEach((k, v) -> jda.upsertCommand(k.getName(), k.getDescription()).queue());

        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

}
