package com.lmf.command.enums;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

public enum CommandEnum {

    LOCAL_LEADERBOARD("localleaderboard", "List the local leaderboard in the clan wars");

    private String name;
    private String description;

    CommandEnum(String name, String description){
        this.name = name;
        this.description = description;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    public static Optional<CommandEnum> get(String commandReceived) {
        return Arrays.stream(CommandEnum.values())
                .filter(env -> env.name.equals(commandReceived))
                .findFirst();
    }

}
