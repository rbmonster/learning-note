package com.four.cycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: ClassA
 * @Author: sanwu
 * @Date: 2020/11/12 19:34
 */
@Component
public class ClassA {
    private  ClassB classB;

    @Autowired
    public ClassA(ClassB classB) {
        this.classB = classB;
    }

    public ClassB getClassB() {
        return classB;
    }

    public void setClassB(ClassB classB) {
        this.classB = classB;
    }
}
