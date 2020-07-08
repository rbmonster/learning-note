package com.learning.design.command;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: RemoteLoader
 * @Author: sanwu
 * @Date: 2020/7/8 23:52
 */
public class RemoteLoader {
    public static void main(String[] args) {
        RemoteControlInvoker remoteControlInvoker = new RemoteControlInvoker();
        LightReceiver lightReceiver = new LightReceiver();
        LightOnCommand lightOnCommand = new LightOnCommand(lightReceiver);
        LightOffCommand lightOffCommand = new LightOffCommand(lightReceiver);
        remoteControlInvoker.setOffCommand(RemoteControlInvoker.LIGHT_COMMAND,lightOffCommand);
        remoteControlInvoker.setOnCommand(RemoteControlInvoker.LIGHT_COMMAND, lightOnCommand);

        remoteControlInvoker.invokeOffCommand(RemoteControlInvoker.LIGHT_COMMAND);
        remoteControlInvoker.invokeOnCommand(RemoteControlInvoker.LIGHT_COMMAND);
    }
}
