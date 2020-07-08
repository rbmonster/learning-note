package com.learning.design.command;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Command
 * @Author: sanwu
 * @Date: 2020/7/8 23:31
 */
public interface Command {
    void execute();
    void undo();
}
