package com.example.usermanagement.commandBus;

import com.example.usermanagement.commands.Command;

import java.lang.reflect.InvocationTargetException;

public interface CommandBus {
    <U> U execute(Command command) throws InvocationTargetException, IllegalAccessException;
}
