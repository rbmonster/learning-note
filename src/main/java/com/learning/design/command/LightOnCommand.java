package com.learning.design.command;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: LightCommand
 * @Author: sanwu
 * @Date: 2020/7/8 23:32
 */
public class LightOnCommand implements Command {
    private LightReceiver light;

    public LightOnCommand(LightReceiver light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
