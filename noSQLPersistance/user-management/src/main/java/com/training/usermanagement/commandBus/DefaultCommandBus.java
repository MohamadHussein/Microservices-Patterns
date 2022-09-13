package com.example.usermanagement.commandBus;

import com.example.usermanagement.commandHandler.CommandHandler;
import com.example.usermanagement.commands.Command;
import com.example.usermanagement.utils.Tuple;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DefaultCommandBus implements CommandBus, BeanPostProcessor {

    private final Map<Class, Tuple<Object, Method>> commandHandlerMap = new ConcurrentHashMap<>();

    @Override
    public <U> U execute(Command command) {
        Tuple<Object, Method> tuple = commandHandlerMap.get(command.getClass());
        Method method = tuple.get_2();
        return (U) ReflectionUtils.invokeMethod(method,tuple.get_1(),command);


    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        ReflectionUtils.doWithMethods(bean.getClass(),method -> {
                    CommandHandler annotation = method.getAnnotation(CommandHandler.class);
                    if(annotation != null){
                        if(method.getParameterTypes().length != 1)return;
                        Class<?> commandType = method.getParameterTypes()[0];
                        commandHandlerMap.put(commandType,new Tuple<>(bean,method));
                    }
                }
                );

        return bean;
    }

}
