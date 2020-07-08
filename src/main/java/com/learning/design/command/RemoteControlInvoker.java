package com.learning.design.command;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: RemoteControl
 * @Author: sanwu
 * @Date: 2020/7/8 23:37
 */
public class RemoteControlInvoker {
    public static String LIGHT_COMMAND = "light";
    private Map<String,Command> onControlMap = new HashMap<>();
    private Map<String, Command> offControlMap = new HashMap<>();

    public void setOnCommand(String commandName, Command command) {
        onControlMap.put(commandName, command);
    }

    public void setOffCommand(String commandName, Command command) {
        offControlMap.put(commandName, command);
    }

    public void invokeOnCommand(String commandName) {
        onControlMap.get(commandName).execute();
    }

    public void  invokeOffCommand(String commandName){
        offControlMap.get(commandName).execute();
    }
}
