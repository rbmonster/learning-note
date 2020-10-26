# Java 基础

## 基本数据类型
- 八大基本数据类型
    - byte/8
      - 取值范围为-128~127，占用1个字节
    - short/16
      - 取值范围为-32768~32767，占用2个字节
    - int/32
      - 占用4个字节（-2的31次方到2的31次方-1） 
    - float/32
      - 占用4个字节 （-3.40292347E+38~3.40292347E+38）
    - long/64
      - 占用8个字节（-2的63次方到2的63次方-1）
    - double/64
      - - 占用8个字节，IEEE754
    - char/16
    - boolean/~
- 基本数据类型转换关系：byte→short(char)→int→long→float→double

### 包装类型
- 所谓包装类，就是能够直接将简单类型的变量表示为一个类，在执行变量类型的相互转换时，我们会大量使用这些包装类。
- 以下用途
    1. 作为基本数据类型对应的类类型，提供了一系列实用的对象操作，如类型转换，进制转换等
    2. 集合不允许存放基本数据类型，故常用包装类
    3. 包含了每种基本类型的相关属性，如最大值，最小值，所占位数等
- 包装类都为final 不可继承
- 包装类型都继承了Number抽象类

```
Integer x = 2;     // 装箱 调用了 Integer.valueOf(2)
int y = x;         // 拆箱 调用了 X.intValue()
```

- new Integer(123) 与 Integer.valueOf(123) 的区别在于：
  - new Integer(123) 每次都会新建一个对象；
  - Integer.valueOf(123) 会使用缓存池中的对象，多次调用会取得同一个对象的引用。
    - valueOf() 方法的实现比较简单，就是先判断值是否在缓存池中，如果在的话就直接返回缓存池的内容

#### 缓冲池
- 包装类型内存使用 private static class IntegerCache，声明一个内部使用的缓存池
  - 如Integer中有个静态内部类IntegerCache，里面有个cache[],也就是Integer常量池，常量池的大小为一个字节（-128~127）

- 在 jdk 1.8 所有的数值类缓冲池中，Integer 的缓冲池 IntegerCache 很特殊，这个缓冲池的下界是 - 128，上界默认是 127，但是这个上界是可调的，在启动 jvm 的时候，通过 -XX:AutoBoxCacheMax=<size> 来指定这个缓冲池的大小。

- 基本类型对应的缓冲池如下：
    - boolean values: true and false
    - all byte values
    - short values: between -128 and 127
    - int values: between -128 and 127
    - char: in the range \u0000 to \u007F
    
## String
- String 被声明为 final，因此它不可被继承
  - Java 8 中，String 内部使用 char 数组存储数据。
  - Java 9 之后，String 类的实现改用 byte 数组存储字符串，同时使用 coder 来标识使用了哪种编码。
  - 对String对象的任何改变都不影响到原对象，相关的任何change操作都会生成新的对象
  
- 不可变的好处
  1. 可以缓存 hash 值
  2. String Pool 的需要。如果一个 String 对象已经被创建过了，那么就会从 String Pool 中取得引用。只有 String 是不可变的，才可能使用 String Pool。
  3. 安全性。String 经常作为参数，String 不可变性可以保证参数不可变。如网络传输
  4. 线程安全
  
- new String("abc") :创建两String对象，（前提是 String Pool 中还没有 "abc" 字符串对象）。
- java 8 字符串常量池放置于方法区中
### String, StringBuffer and StringBuilder
- intern() 方法： 
  - 当一个字符串调用 intern() 方法时，如果 String Pool 中已经存在一个字符串和该字符串值相等（使用 equals() 方法进行确定），那么就会返回 String Pool 中字符串的引用；
  - 否则，就会在 String Pool 中添加一个新的字符串，并返回这个新字符串的引用。
- String、StringBuilder、StringBuffer三者的执行效率：
　　- StringBuilder > StringBuffer > String
　　- 当然这个是相对的，不一定在所有情况下都是这样。
　　- 比如String str = "hello"+ "world"的效率就比 StringBuilder st  = new StringBuilder().append("hello").append("world")要高。
- 对于三者使用的总结：
    1. 操作少量的数据: 适用String
    2. 单线程操作字符串缓冲区下操作大量数据: 适用StringBuilder
    3. 多线程操作字符串缓冲区下操作大量数据: 适用StringBuffer
```
    String a = "hello2"; 　  
    String b = "hello";       
    String c = b + 2;       
    System.out.println((a == c));
    输出结果为:false。由于有符号引用的存在，所以  String c = b + 2;不会在编译期间被优化，不会把b+2当做字面常量来处理的

    String a = "hello2";   　
    final String b = "hello";       
    String c = b + 2;       
    System.out.println((a == c));
    输出结果为：true。对于被final修饰的变量，会在class文件常量池中保存一个副本，也就是说不会通过连接而进行访问
```


## final 关键字
- 数据：声明数据为常量，可以是编译时常量，也可以是在运行时被初始化后不能被改变的常量。
- 对于基本类型，final 使数值不变；
- 对于引用类型，final 使引用不变，也就不能引用其它对象，但是被引用的对象本身是可以修改的。
```
final StringBuilder stringBuilder = new StringBuilder("123");
        System.out.println(stringBuilder);
        stringBuilder.append(11);
        System.out.println(stringBuilder);
        // 报错
//        stringBuilder = new StringBuilder("123123");
```

- 方法：声明方法不能被子类重写。
- private 方法隐式地被指定为 final，如果在子类中定义的方法和基类中的一个 private 方法签名相同，此时子类的方法不是重写基类方法，而是在子类中定义了一个新的方法。

- 类:声明类不允许被继承。

## static 关键字
- 静态变量
  - 静态变量：又称为类变量，也就是说这个变量属于类的，类所有的实例都共享静态变量，可以直接通过类名来访问它。静态变量在内存中只存在一份。
    - 针对于静态变量储存在方法区中，而静态对象方法区存储引用，对象放在堆中。
  - 实例变量：每创建一个实例就会产生一个实例变量，它与该实例同生共死。
```
public class A {

    private int x;         // 实例变量
    private static int y;  // 静态变量
}
```

- 静态方法：静态方法在类加载的时候就存在了，它不依赖于任何实例。所以静态方法必须有实现，也就是说它不能是抽象方法。
  - 只能访问所属类的静态字段和静态方法，方法中不能有 this 和 super 关键字，这两个关键字与具体对象关联

- 静态语句块：静态语句块在类初始化时运行一次。

- 静态内部类：非静态内部类依赖于外部类的实例，也就是说需要先创建外部类实例，才能用这个实例去创建非静态内部类。而静态内部类不需要。
  - 注意这边是内部类
```
public class OuterClass {

    class InnerClass {
    }

    static class StaticInnerClass {
    }

    public static void main(String[] args) {
        // InnerClass innerClass = new InnerClass(); // 'OuterClass.this' cannot be referenced from a static context
        OuterClass outerClass = new OuterClass();
        InnerClass innerClass = outerClass.new InnerClass();
        StaticInnerClass staticInnerClass = new StaticInnerClass();
    }
}
```

- 静态导包：在使用静态变量和方法时不用再指明 ClassName。增强可读性。
  - import static com.xxx.ClassName.*
  
- 初始化顺序 ：静态变量和静态语句块优先于实例变量和普通语句块，静态变量和静态语句块的初始化顺序取决于它们在代码中的顺序。
```
// 1
public static String staticField = "静态变量";
// 2
static {
    System.out.println("静态语句块");
}
// 3
public String field = "实例变量";
// 4
{
    System.out.println("普通语句块");
}

最后才是构造函数的初始化。
// 5
public InitialOrderTest() {
    System.out.println("构造函数");
}
```
- 存在继承的情况下，初始化顺序为：
    - 父类（静态变量、静态语句块）
    - 子类（静态变量、静态语句块）
    - 父类（实例变量、普通语句块）
    - 父类（构造函数）
    - 子类（实例变量、普通语句块）
    - 子类（构造函数）
    
## Object 通用方法

``` 

public native int hashCode()
public boolean equals(Object obj)

protected native Object clone() throws CloneNotSupportedException

public String toString()

public final native Class<?> getClass()

protected void finalize() throws Throwable {}

// 线程间协作相关
//Exception in thread "main" java.lang.IllegalMonitorStateException
public final native void notify()
public final native void notifyAll()
public final native void wait(long timeout) throws InterruptedException
public final void wait(long timeout, int nanos) throws InterruptedException
public final void wait() throws InterruptedException
```

### equals()
- 等价与相等
    - 对于基本类型，== 判断两个值是否相等，基本类型没有 equals() 方法。
    - 对于引用类型，== 判断两个变量是否引用同一个对象，而 equals() 判断引用的对象是否等价。
- 如何重写：
  1. 检查是否为同一个对象的引用，如果是直接返回 true；
  2. 检查是否是同一个类型，如果不是，直接返回 false；
  3. 将 Object 对象进行转型；
  4. 判断每个关键域是否相等。
```
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    EqualExample that = (EqualExample) o;

    if (x != that.x) return false;
    if (y != that.y) return false;
    return z == that.z;
}
```
### hashCode()
- hashCode()：hashCode是jdk根据对象的地址或者字符串或者数字算出来的int类型的数值.
  - 对象按照自己不同的特征尽量的有不同的哈希码，作用是用于快速查找
  - 另一个应用就是hash集合的使用
- 重写hashcode方法:
```
    private String name;
	private int age;
	
    @Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + name.hashCode();
		result = result * 31 + age;
		
		return result;
	}
```

-  为什么要使用 31 ?
  - 原因一：更少的乘积结果冲突
  - 原因二：与 31 相乘可以转换成移位和减法：31*x == (x<<5)-x，编译器会自动进行这个优化

### toString()
默认返回 对象名@4554617c 这种形式，其中 @ 后面的数值为散列码的无符号十六进制表示。

### clone()
- 主要用于对象的拷贝克隆，如果一个对象不实现clone接口方法，默认抛出CloneNotSupportedException
- 对象的拷贝分为浅拷贝与深拷贝
  - 浅拷贝就是返回同一个引用的对象。
  - 深拷贝就是返回和原始对象的引用类型引用不同对象。
  
### wait、notify、notifyAll 相关
 - 如果当前线程没有获得一个对象的监听器，调用该方法就会抛出一个IllegalMonitorStateException
```
{
        Test test = new Test();
        //  直接调用报错
        //   test.wait();
        synchronized (test) {
            test.wait();
        }
}
// synchronized 方法抛异常
   synchronized void test() throws InterruptedException {
        this.wait();
    }
```
- 获得当前对象的监听器的方式：
  - 执行此对象的同步 (Sychronized) 实例方法
  - 执行在此对象上进行同步的 synchronized 语句的方法
  - 对于 Class 类型的对象，执行该类的同步静态方法
 
### 继承
- Java 中有三个访问权限修饰符：private、protected 以及 public，
  - 如果不加访问修饰符，表示包级可见。
  - protected 用于修饰成员，表示在继承体系中成员对于子类可见，但是这个访问修饰符对于类没有意义。
  - private 仅自己可见
  - public 所有均可见

- private 和 protected 不能修饰类。
- 设计良好的模块会隐藏所有的实现细节，把它的 API 与它的实现清晰地隔离开来。模块之间只通过它们的 API 进行通信。
- 如果子类的方法重写了父类的方法，那么子类中该方法的访问级别不允许低于父类的访问级别。这是为了确保可以使用父类实例的地方都可以使用子类实例去代替，也就是确保满足里氏替换原则。

### 抽象类与接口
#### 抽象类
- 如果一个类中包含抽象方法，那么这个类必须声明为抽象类。
- 抽象类和抽象方法都使用 abstract 关键字进行声明。

抽象类和普通类最大的区别是，抽象类不能被实例化，只能被继承。

#### 接口
- 接口是抽象类的延伸，在 Java 8 之前，它可以看成是一个完全抽象的类，也就是说它不能有任何的方法实现。
- 从 Java 8 开始，接口也可以拥有默认的方法实现
- 接口的成员（字段 + 方法）默认都是 public 的，并且不允许定义为 private 或者 protected。
- 接口的字段默认都是 static 和 final 的。

#### 比较
- 从设计层面上看
  - 抽象类的实现目的，是代码复用，可以让这些类都派生于一个抽象类。
  - 接口的设计目的，是对类的行为进行约束（更准确的说是一种“有”约束，因为接口不能规定类不可以有什么行为），也就是提供一种机制，可以强制要求不同的类具有相同的行为。
- 从使用上来看，一个类可以实现多个接口，但是不能继承多个抽象类。
- 接口的字段只能是 static 和 final 类型的，而抽象类的字段没有这种限制。
- 接口的成员只能是 public 的，而抽象类的成员可以有多种访问权限。

### super关键字
- 访问父类的构造函数：可以使用 super() 函数访问父类的构造函数，从而委托父类完成一些初始化的工作。
- 访问父类的成员：如果子类重写了父类的某个方法，可以通过使用 super 关键字来引用父类的方法实现。

### 重写与重载
#### 重写（Override）：
- 重写：存在于继承体系中，指子类实现了一个与父类在方法声明上完全相同的一个方法。
- 重写有以下三个限制：
  1. 子类方法的访问权限必须大于等于父类方法；
  2. 子类方法的返回类型必须是父类方法返回类型或为其子类型。
  3. 子类方法抛出的异常类型必须是父类抛出异常类型或为其子类型。
 
- 使用 @Override 注解，可以让编译器帮忙检查是否满足上面的三个限制条件。

#### 重载（Overload）
- 存在于同一个类中，指一个方法与已经存在的方法名称上相同，但是参数类型、个数、顺序至少有一个不同。
  - 应该注意的是，返回值不同，其它都相同不算是重载。
  - 重载方法的优先级，char->int->long->float->double ->Character -> Serializable -> Object ,基本类型的重载方法会按此优先级寻找对应的方法，若重载的方法参数与调用的方法不一致，则会向父类查找匹配上相同类型的方法。
```
    public static void sayHello(int arg) {
        System.out.println("this is int " +arg);
    }

    public static void sayHello(long arg) {
        System.out.println("this is long " +arg);
    }

    public static void main(String[]args) {
        sayHello('a');
    }
//output 
this is int 97
```