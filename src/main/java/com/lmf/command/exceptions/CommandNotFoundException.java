package com.lmf.command.exceptions;

public class CommandNotFoundException extends Exception{

    public CommandNotFoundException() {
        super("Command not found!");
    }
}
