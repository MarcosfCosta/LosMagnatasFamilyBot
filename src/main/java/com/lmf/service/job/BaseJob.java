package com.lmf.service.job;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.springframework.stereotype.Component;

@Component
public interface BaseJob {
    void execute();
}
