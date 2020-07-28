package com.learning.jvm;

/**
 * <pre>
 * @Description:
 * 划重点！！！！！
 * output:
 * I am Son. I have money $0
 * I am Son. I have money $4
 * this gay has money $2
 *
 * 解析第一个I am son 是父类构造函数对showMoney的调用，调用时一次虚方法调用，实际执行的是son的方法，这时候父类的money初始化完成，但是子类还未初始化默认为0。
 * 第二个I am son执行的是子类的showMoney，这时候子类的money已经初始化。
 * 而最后一句话是通过静态类型访问到父类中的money，输出了2。
 * </pre>
 *
 * @version v1.0
 * @ClassName: FieldHasNoPolymorphic
 * @Author: sanwu
 * @Date: 2020/7/28 23:08
 */
public class FieldHasNoPolymorphic {
    static class Father {
        public int money = 1;
        public Father() {
            money = 2;
            showMoney();
        }

        public void showMoney() {
            System.out.println("I am father. I have money $"+money);
        }
    }

    static class Son extends Father {
        public int money = 3;
        public Son() {
            money = 4;
            showMoney();
        }

        public void showMoney() {
            System.out.println("I am Son. I have money $"+money);
        }
    }

    public static void main(String[] args) {
        Father gay = new Son();
        System.out.println("this gay has money $" +gay.money);
        NoStaticFather gay2 = new NoStaticSon();
        System.out.println("this is not static gay. He has money $" + gay2.money);
    }
}

class NoStaticFather{
    public int money = 1;
    public NoStaticFather() {
        money = 2;
        showMoney();
    }

    public void showMoney() {
        System.out.println("I am NoStaticFather. I have money $"+money);
    }
}

class NoStaticSon extends NoStaticFather{
    public int money = 3;
    public NoStaticSon() {
        money = 4;
        showMoney();
    }

    public void showMoney() {
        System.out.println("I am Son. I have money $"+money);
    }
}
