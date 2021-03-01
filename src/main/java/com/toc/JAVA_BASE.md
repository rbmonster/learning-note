<a name="index">**Index**</a>

<a href="#0">Java 基础</a>  
&emsp;<a href="#1">1. 基本数据类型</a>  
&emsp;&emsp;<a href="#2">1.1. 包装类型</a>  
&emsp;&emsp;&emsp;<a href="#3">1.1.1. 缓冲池</a>  
&emsp;&emsp;<a href="#4">1.2. BigDecimal</a>  
&emsp;<a href="#5">2. String</a>  
&emsp;&emsp;<a href="#6">2.1. String, StringBuffer and StringBuilder</a>  
&emsp;&emsp;&emsp;<a href="#7">2.1.1. 内部数据结构</a>  
&emsp;&emsp;&emsp;<a href="#8">2.1.2. AbstractStringBuilder 扩容</a>  
&emsp;<a href="#9">3. final 关键字</a>  
&emsp;<a href="#10">4. static 关键字</a>  
&emsp;<a href="#11">5. Object 通用方法</a>  
&emsp;&emsp;<a href="#12">5.1. equals()</a>  
&emsp;&emsp;<a href="#13">5.2. hashCode()</a>  
&emsp;&emsp;<a href="#14">5.3. toString()</a>  
&emsp;&emsp;<a href="#15">5.4. clone()</a>  
&emsp;&emsp;<a href="#16">5.5. wait、notify、notifyAll 相关</a>  
&emsp;&emsp;<a href="#17">5.6. 继承</a>  
&emsp;&emsp;<a href="#18">5.7. 抽象类与接口</a>  
&emsp;&emsp;&emsp;<a href="#19">5.7.1. 抽象类</a>  
&emsp;&emsp;&emsp;<a href="#20">5.7.2. 接口</a>  
&emsp;&emsp;&emsp;<a href="#21">5.7.3. 比较</a>  
&emsp;&emsp;<a href="#22">5.8. super关键字</a>  
&emsp;&emsp;<a href="#23">5.9. 重写与重载</a>  
&emsp;&emsp;&emsp;<a href="#24">5.9.1. 重写（Override）：</a>  
&emsp;&emsp;&emsp;<a href="#25">5.9.2. 重载（Overload）</a>  
&emsp;<a href="#26">6. 反射</a>  
&emsp;<a href="#27">7. 异常</a>  
&emsp;<a href="#28">8. 泛型</a>  
&emsp;<a href="#29">9. 注解</a>  
&emsp;<a href="#30">10. 线程</a>  
&emsp;&emsp;&emsp;<a href="#31">10.0.3. 线程状态</a>  
&emsp;&emsp;&emsp;<a href="#32">10.0.4. 创建一个线程的开销</a>  
&emsp;<a href="#33">11. 枚举类</a>  
&emsp;<a href="#34">12. 零散的点</a>  
&emsp;&emsp;<a href="#35">12.1. 方法调用的知识点</a>  
&emsp;&emsp;<a href="#36">12.2. 三大特性</a>  
&emsp;&emsp;<a href="#37">12.3. 序列化与反序列化</a>  
&emsp;&emsp;<a href="#38">12.4. java复制</a>  
# <a name="0">Java 基础</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

## <a name="1">基本数据类型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
八大基本数据类型
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
  - 占用8个字节，IEEE754
- char/16
- boolean/~

基本数据类型转换关系：byte→short(char)→int→long→float→double

### <a name="2">包装类型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
所谓包装类，就是能够直接将简单类型的变量表示为一个类，在执行变量类型的相互转换时，我们会大量使用这些包装类。
- 以下用途
1. 作为基本数据类型对应的类类型，提供了一系列实用的对象操作，如类型转换，进制转换等
2. 集合不允许存放基本数据类型，故常用包装类
3. 包含了每种基本类型的相关属性，如最大值，最小值，所占位数等

包装类都为final 不可继承

包装类型都继承了Number抽象类

```
Integer x = 2;     // 装箱 调用了 Integer.valueOf(2)
int y = x;         // 拆箱 调用了 X.intValue()
```

new Integer(123) 与 Integer.valueOf(123) 的区别在于：
- new Integer(123) 每次都会新建一个对象；
- Integer.valueOf(123) 会使用缓存池中的对象，多次调用会取得同一个对象的引用。
    - valueOf() 方法的实现比较简单，就是先判断值是否在缓存池中，如果在的话就直接返回缓存池的内容

#### <a name="3">缓冲池</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
包装类型内存使用 private static class IntegerCache，声明一个内部使用的缓存池
- 如Integer中有个静态内部类IntegerCache，里面有个cache[],也就是Integer常量池，常量池的大小为一个字节（-128~127）
- 为啥把缓存设置为[-128，127]区间？性能和资源之间的权衡。
在 jdk 1.8 所有的数值类缓冲池中，Integer 的缓冲池 IntegerCache 很特殊，这个缓冲池的下界是 - 128，上界默认是 127，但是这个上界是可调的，在启动 jvm 的时候，通过 -XX:AutoBoxCacheMax=<size> 来指定这个缓冲池的大小。

基本类型对应的缓冲池如下：
- boolean values: true and false
- all byte values
- short values: between -128 and 127
- int values: between -128 and 127
- char: in the range \u0000 to \u007F
    
### <a name="4">BigDecimal</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
BigDecimal 主要用于处理解决精度丢失问题
- float和double类型主要是为了科学计算和工程计算而设计的。执行二进制浮点运算，这是为了在广泛的数字范围上提供较为精确的快速近似计算而精心设计的。然而，它们并没有提供完全精确的结果
```
float a = 1.0f - 0.9f;
float b = 0.9f - 0.8f;
System.out.println(a);// 0.100000024
System.out.println(b);// 0.099999964
System.out.println(a == b);// false
```
    
## <a name="5">String</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
String 被声明为 final，因此它不可被继承
- Java 8 中，String 内部使用 char 数组存储数据。
- Java 9 之后，String 类的实现改用 byte 数组存储字符串，同时使用 coder 来标识使用了哪种编码。
- 对String对象的任何改变都不影响到原对象，相关的任何change操作都会生成新的对象
  
不可变的好处
1. 可以缓存 hash 值
2. String Pool 的需要。如果一个 String 对象已经被创建过了，那么就会从 String Pool 中取得引用。只有 String 是不可变的，才可能使用 String Pool。
3. 安全性。String 经常作为参数，String 不可变性可以保证参数不可变。如网络传输
4. 线程安全
  
new String("abc") :创建两String对象，（前提是 String Pool 中还没有 "abc" 字符串对象）。

java 8 字符串常量池放置于方法区中

### <a name="6">String, StringBuffer and StringBuilder</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
intern() 方法： 
- 当一个字符串调用 intern() 方法时，如果 String Pool 中已经存在一个字符串和该字符串值相等（使用 equals() 方法进行确定），那么就会返回 String Pool 中字符串的引用；
- 否则，就会在 String Pool 中添加一个新的字符串，并返回这个新字符串的引用。

String、StringBuilder、StringBuffer三者的执行效率：
- StringBuilder > StringBuffer > String
- 当然这个是相对的，不一定在所有情况下都是这样。
- 比如String str = "hello"+ "world"的效率就比 StringBuilder st  = new StringBuilder().append("hello").append("world")要高。

对于三者使用的总结：
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

#### <a name="7">内部数据结构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
java 8 为char[] 数据

#### <a name="8">AbstractStringBuilder 扩容</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
扩容的大小是新字符串的长度的2倍，然后再加上2。
> 在使用StringBuilder的时候，append()之后，我们一般会在后面在加上一个分隔符，例如逗号，也就是再加上一个char，而char在java中占2个字节，避免了因为添加分隔符而再次引起扩容。


## <a name="9">final 关键字</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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

方法：声明方法不能被子类重写。

private 方法隐式地被指定为 final，如果在子类中定义的方法和基类中的一个 private 方法签名相同，此时子类的方法不是重写基类方法，而是在子类中定义了一个新的方法。

类:声明类不允许被继承。

## <a name="10">static 关键字</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
静态变量
- 静态变量：又称为类变量，也就是说这个变量属于类的，类所有的实例都共享静态变量，可以直接通过类名来访问它。静态变量在内存中只存在一份。
    - 针对于静态变量储存在方法区中，而静态对象方法区存储引用，对象放在堆中。
- 实例变量：每创建一个实例就会产生一个实例变量，它与该实例同生共死。
```
public class A {

    private int x;         // 实例变量
    private static int y;  // 静态变量
}
```

静态方法：静态方法在类加载的时候就存在了，它不依赖于任何实例。所以静态方法必须有实现，也就是说它不能是抽象方法。
- 只能访问所属类的静态字段和静态方法，方法中不能有 this 和 super 关键字，这两个关键字与具体对象关联

静态语句块：静态语句块在类初始化时运行一次。

静态内部类：非静态内部类依赖于外部类的实例，也就是说需要先创建外部类实例，才能用这个实例去创建非静态内部类。而静态内部类不需要。
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

静态导包：在使用静态变量和方法时不用再指明 ClassName。增强可读性。
- import static com.xxx.ClassName.*
  
初始化顺序 ：静态变量和静态语句块优先于实例变量和普通语句块，静态变量和静态语句块的初始化顺序取决于它们在代码中的顺序。
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
存在继承的情况下，初始化顺序为：
- 父类（静态变量、静态语句块）
- 子类（静态变量、静态语句块）
- 父类（实例变量、普通语句块）
- 父类（构造函数）
- 子类（实例变量、普通语句块）
- 子类（构造函数）
    
## <a name="11">Object 通用方法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

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

### <a name="12">equals()</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
等价与相等
- 对于基本类型，== 判断两个值是否相等，基本类型没有 equals() 方法。
- 对于引用类型，== 判断两个变量是否引用同一个对象，而 equals() 判断引用的对象是否等价。

如何重写：
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
### <a name="13">hashCode()</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
hashCode()：hashCode是jdk根据对象的地址或者字符串或者数字算出来的int类型的数值.
- 对象按照自己不同的特征尽量的有不同的哈希码，作用是用于快速查找
- 另一个应用就是hash集合的使用

重写hashcode方法:
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

为什么要使用 31 ?
- 原因一：更少的乘积结果冲突
- 原因二：与 31 相乘可以转换成移位和减法：31*x == (x<<5)-x，编译器会自动进行这个优化

### <a name="14">toString()</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
默认返回 对象名@4554617c 这种形式，其中 @ 后面的数值为散列码的无符号十六进制表示。

### <a name="15">clone()</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
主要用于对象的拷贝克隆，如果一个对象不实现clone接口方法，默认抛出CloneNotSupportedException

对象的拷贝分为浅拷贝与深拷贝
- 浅拷贝就是返回同一个引用的对象。
- 深拷贝就是返回和原始对象的引用类型引用不同对象。
  
### <a name="16">wait、notify、notifyAll 相关</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
如果当前线程没有获得一个对象的监听器，调用该方法就会抛出一个IllegalMonitorStateException
```
{
        Test test = new Test();
        //  直接调用报错
        //   test.wait();
        synchronized (test) {
            test.wait();
        }
}
// synchronized 方法不抛异常
   synchronized void test() throws InterruptedException {
        this.wait();
    }
```
获得当前对象的监听器的方式：
- 执行此对象的同步 (Sychronized) 实例方法
- 执行在此对象上进行同步的 synchronized 语句的方法
- 对于 Class 类型的对象，执行该类的同步静态方法
 
### <a name="17">继承</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Java 中有三个访问权限修饰符：private、protected 以及 public，
- 如果不加访问修饰符，表示包级可见。
- protected 用于修饰成员，表示在继承体系中成员对于子类可见，但是这个访问修饰符对于类没有意义。
- private 仅自己可见
- public 所有均可见

- private 和 protected 不能修饰类。
- 设计良好的模块会隐藏所有的实现细节，把它的 API 与它的实现清晰地隔离开来。模块之间只通过它们的 API 进行通信。
- 如果子类的方法重写了父类的方法，那么子类中该方法的访问级别不允许低于父类的访问级别。这是为了确保可以使用父类实例的地方都可以使用子类实例去代替，也就是确保满足里氏替换原则。

### <a name="18">抽象类与接口</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
#### <a name="19">抽象类</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 如果一个类中包含抽象方法，那么这个类必须声明为抽象类。
- 抽象类和抽象方法都使用 abstract 关键字进行声明。

抽象类和普通类最大的区别是，抽象类不能被实例化，只能被继承。

#### <a name="20">接口</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 接口是抽象类的延伸，在 Java 8 之前，它可以看成是一个完全抽象的类，也就是说它不能有任何的方法实现。
- 从 Java 8 开始，接口也可以拥有默认的方法实现
- 接口的成员（字段 + 方法）默认都是 public 的，并且不允许定义为 private 或者 protected。
- 接口的字段默认都是 static 和 final 的。

#### <a name="21">比较</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
从设计层面上看
- 抽象类的实现目的，是代码复用，一种模板设计的方式，可以让这些类都派生于一个抽象类。
- 接口的设计目的，是对类的行为进行约束（更准确的说是一种“有”约束，因为接口不能规定类不可以有什么行为），也就是提供一种机制，可以强制要求不同的类具有相同的行为。

- 从使用上来看，一个类可以实现多个接口，但是不能继承多个抽象类。
- 接口的字段只能是 static 和 final 类型的，而抽象类的字段没有这种限制。
- 接口的成员只能是 public 的，而抽象类的成员可以有多种访问权限。

设计上对比：
- 抽象类： 拓展继承该抽象类的模块的类的行为功能（开放闭合原则）
- 接口：约束继承该接口的类行为（依赖倒置原则）

### <a name="22">super关键字</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 访问父类的构造函数：可以使用 super() 函数访问父类的构造函数，从而委托父类完成一些初始化的工作。
- 访问父类的成员：如果子类重写了父类的某个方法，可以通过使用 super 关键字来引用父类的方法实现。

### <a name="23">重写与重载</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
#### <a name="24">重写（Override）：</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
重写：存在于继承体系中，指子类实现了一个与父类在方法声明上完全相同的一个方法。

重写有以下三个限制：
1. 子类方法的访问权限必须大于等于父类方法；
2. 子类方法的返回类型必须是父类方法返回类型或为其子类型。
3. 子类方法抛出的异常类型必须是父类抛出异常类型或为其子类型。
 
使用 @Override 注解，可以让编译器帮忙检查是否满足上面的三个限制条件。

#### <a name="25">重载（Overload）</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
存在于同一个类中，指一个方法与已经存在的方法名称上相同，但是参数类型、个数、顺序至少有一个不同。
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
## <a name="26">反射</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
反射可以提供运行时的类信息，并且这个类可以在运行时才加载进来，甚至在编译时期该类的 .class 不存在也可以加载进来。
- 当编译一个新类时，会产生一个同名的 .class 文件，该文件内容保存着 Class 对象。
- 类加载相当于 Class 对象的加载，类在第一次使用时才动态加载到 JVM 中。
- 也可以使用 Class.forName("com.mysql.jdbc.Driver") 这种方式来控制类的加载，该方法会返回一个 Class 对象。
  
Class 和 java.lang.reflect 一起对反射提供了支持，java.lang.reflect 类库主要包含了以下三个类：
- Field ：可以使用 get() 和 set() 方法读取和修改 Field 对象关联的字段；
- Method ：可以使用 invoke() 方法调用与 Method 对象关联的方法；
- Constructor ：可以用 Constructor 的 newInstance() 创建新的对象。

反射的优点：
- **可扩展性**   ：应用程序可以利用全限定名创建可扩展对象的实例，如com.demo.Test。
- 调试器和测试工具： 调试器需要能够检查一个类里的私有成员。测试工具可以利用反射来自动地调用类里定义的可被发现的 API 定义，以确保一组测试中有较高的代码覆盖率。
- 开发工具：如IDEA开发工具可以从反射中获取类的信息，帮助开发人员代码编写。
  
反射的缺点：如果一个功能可以不用反射完成，那么最好就不用。
- **性能开销**   ：反射涉及了动态类型的解析，所以 JVM 无法对这些代码进行优化。因此，反射操作的效率要比那些非反射操作低得多。
- **安全限制**   ：使用反射技术要求程序必须在一个没有安全限制的环境中运行。
- 内部暴露： 反射破坏了封装性，可能会导致意料之外的副作用，这可能导致代码功能失调并破坏可移植性
  
通过反射创建对象
```
//获取 Person 类的 Class 对象
Class clazz=Class.forName("reflection.Person");
//使用.newInstane 方法创建对象
Person p=(Person) clazz.newInstance();
//获取构造方法并创建对象
Constructor c=clazz.getDeclaredConstructor(String.class,String.class,int.class);
//创建对象并设置属性
Person p1=(Person) c.newInstance("李四","男",20);
```

## <a name="27">异常</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Throwable 可以用来表示任何可以作为异常抛出的类，分为两种： Error 和 Exception。
- 其中 Error 用来表示 JVM 无法处理的错误，

Exception 分为两种：
- 受检异常 ：需要用 try...catch... 语句捕获并进行处理，并且可以从异常中恢复；
- 非受检异常 ：是程序运行时错误，例如除 0 会引发 Arithmetic Exception，此时程序崩溃并且无法恢复
- 运行时异常（runtime exception）

RuntimeException是一种Unchecked Exception，即表示编译器不会检查程序是否对RuntimeException作了处理，在程序中不必捕获RuntimException类型的异常，也不必在方法体声明抛出RuntimeException类。一般来说，RuntimeException发生的时候，表示程序中出现了编程错误，所以应该找出错误修改程序，而不是去捕获RuntimeException。
- 常见RuntimeException异常：NullPointException、ClassCastException、IllegalArgumentException、IndexOutOfBoundException
  
如果try语句里有return，返回的是try语句块中变量值。 

详细执行过程如下：
1. 如果有返回值，就把返回值保存到局部变量中；
2. 执行jsr指令跳到finally语句里执行；
3. 执行完finally语句后，返回之前保存在局部变量表里的值。
4. 针对对象引用的返回，如果finally中有修改值，返回的是引用的对象。
**如果try，finally语句里均有return，忽略try的return，而使用finally的return.**
  
## <a name="28">泛型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
泛型的本质是参数化类型，也就是所操作的数据类型被指定为一个参数。
- 在集合中存储对象并在使用前进行类型转换是多么的不方便。泛型防止了那种情况的发生。它提供了编译期的类型安全，确保你只能把正确类型的对象放入集合中，避免了在运行时出现ClassCastException。
- 使用T, E or K,V等被广泛认可的类型占位符。
  
泛型有三种常用的使用方式：泛型类，泛型接口和泛型方法。
- 限定通配符和非限定通配符 
  - 非限定通配符：另一方面<?>表示了非限定通配符，因为<?>可以用任意类型来替代。
  - 一种是<? extends T>它通过确保类型必须是T的子类来设定类型的上界
  - 另一种是<? super T>它通过确保类型必须是T的父类来设定类型的下界
  - 泛型类型必须用限定内的类型来进行初始化，否则会导致编译错误。
  
类型擦除: Java的泛型基本上都是在编译器这个层次上实现的，在生成的字节码中是不包含泛型中的类型信息的，使用泛型的时候加上类型参数，在编译器编译的时候会去掉，这个过程成为类型擦除。
- 如在代码中定义List<Object>和List<String>等类型，在编译后都会变成List，JVM看到的只是List，而由泛型附加的类型信息对JVM是看不到的。
- 类型擦除后保留的原始类型，最后在字节码中的类型变量变成真正类型。无论何时定义一个泛型，相应的原始类型都会被自动提供，无限定的变量用Object替换。

泛型擦除的例子： 本应该只能储存Integer，在通过反射调用方法时，却可以添加String数据
```
 public static void main(String[] args) throws Exception {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);  //这样调用 add 方法只能存储整形，因为泛型类型的实例为 Integer
        list.getClass().getMethod("add", Object.class).invoke(list, "asd");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
// output
//1
//asd
```

类型擦除后保留的原始类型：在调用泛型方法时，可以指定泛型，也可以不指定泛型。
- 在不指定泛型的情况下，泛型变量的类型为该方法中的几种类型的同一父类的最小级，直到Object
- 在指定泛型的情况下，该方法的几种类型必须是该泛型的实例的类型或者其子类
```
Number f = Test.add(1, 1.2); //这两个参数一个是Integer，以风格是Float，所以取同一父类的最小级，为Number  
Object o = Test.add(1, "asd"); //这两个参数一个是Integer，以风格是Float，所以取同一父类的最小级，为Object  
  
//这是一个简单的泛型方法  
public static <T> T add(T x,T y){  
    return y;  
}    
```

泛型使用的一个例子
```
public class Box<T> {
    // T stands for "Type"
    private T t;
    public void set(T t) { this.t = t; }
    public T get() { return t; }
    public <E> E get(E param) {
        // do some logic 
        return (E)obj;
    }
}
```

Java不能实现真正的泛型，只能使用类型擦除来实现伪泛型，这样虽然不会有类型膨胀问题，但是也引起来许多新问题

## <a name="29">注解</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
java.lang.annotation 提供了四种元注解，专门注解其他的注解（在自定义注解的时候，需要使用到元注解）：
- @Documented：注解是否将包含在JavaDoc中
- @Retention：什么时候使用该注解 
    - RetentionPolicy.SOURCE : 在编译阶段丢弃。这些注解在编译结束之后就不再有任何意义，所以它们不会写入字节码。@Override, @SuppressWarnings都属于这类注解。
    - RetentionPolicy.CLASS : 在类加载的时候丢弃。在字节码文件的处理中有用。注解默认使用这种方式
    - RetentionPolicy.RUNTIME : 始终不会丢弃，运行期也保留该注解，因此可以使用反射机制读取该注解的信息。我们自定义的注解通常使用这种方式。
- @Target – 注解用于什么地方
     - ElementType.CONSTRUCTOR: 用于描述构造器
     - ElementType.FIELD: 成员变量、对象、属性（包括enum实例）
     - ElementType.LOCAL_VARIABLE: 用于描述局部变量
     - ElementType.METHOD: 用于描述方法
     - ElementType.PACKAGE: 用于描述包
     - ElementType.PARAMETER: 用于描述参数
     - ElementType.TYPE: 用于描述类、接口(包括注解类型) 或enum声明 常见的@Component、@Service
- @Inherited – 是否允许子类继承该注解
    - @Inherited 元注解是一个标记注解，@Inherited 阐述了某个被标注的类型是被继承的。如果一个使用了@Inherited 修饰的annotation 类型被用于一个class，则这个annotation 将被用于该class 的子类

编写注解的规则
1. Annotation 型定义为@interface。
2. 参数成员只能用public 或默认(default) 这两个访问权修饰
3. 参数成员只能用基本类型byte、short、char、int、long、float、double、boolean八种基本数据类型和String、Enum、Class、annotations等数据类型，以及这一些类型的数组。
4. 要获取类方法和字段的注解信息，必须通过Java的反射技术来获取 Annotation 对象
```
 @Target(FIELD)
 @Retention(RUNTIME)
 @Documented
 public @interface FruitName {
     String value() default "";
 }
```

## <a name="30">线程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
#### <a name="31">线程状态</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/concurrent/picture/threadState.jpg)

- 新建（NEW）：创建后尚未启动。
- 可运行（RUNABLE）：正在 Java 虚拟机中运行。但是在操作系统层面，它可能处于运行状态，也可能等待资源调度（例如处理器资源），资源调度完成就进入运行状态。所以该状态的可运行是指可以被运行，具体有没有运行要看底层操作系统的资源调度。
- 阻塞（BLOCKED）：请求获取 monitor lock 从而进入 synchronized 函数或者代码块，但是其它线程已经占用了该 monitor lock，所以出于阻塞状态。要结束该状态进入从而 RUNABLE 需要其他线程释放 monitor lock。
- 无限期等待（WAITING）：等待其它线程显式地唤醒。
   - 阻塞和等待的区别在于，阻塞是被动的，它是在等待获取 monitor lock。而等待是主动的，通过调用  Object.wait() 等方法进入。

| 进入方法 | 退出方法 |
| --- | --- |
| 没有设置 Timeout 参数的 Object.wait() 方法 | Object.notify() / Object.notifyAll() |
| 没有设置 Timeout 参数的 Thread.join() 方法 | 被调用的线程执行完毕 |
| LockSupport.park() 方法 | LockSupport.unpark(Thread) |

- 限期等待（TIMED_WAITING）：无需等待其它线程显式地唤醒，在一定时间之后会被系统自动唤醒。
  - 调用 Thread.sleep() 方法使线程进入限期等待状态时，常常用“使一个线程睡眠”进行描述。调用 Object.wait() 方法使线程进入限期等待或者无限期等待时，常常用“挂起一个线程”进行描述。睡眠和挂起是用来描述行为，而阻塞和等待用来描述状态。

| 进入方法 | 退出方法 |
| --- | --- |
| Thread.sleep() 方法 | 时间结束 |
| 设置了 Timeout 参数的 Object.wait() 方法 | 时间结束 / Object.notify() / Object.notifyAll()  |
| 设置了 Timeout 参数的 Thread.join() 方法 | 时间结束 / 被调用的线程执行完毕 |
| LockSupport.parkNanos() 方法 | LockSupport.unpark(Thread) |
| LockSupport.parkUntil() 方法 | LockSupport.unpark(Thread) |

- 死亡（TERMINATED）：可以是线程结束任务之后自己结束，或者产生了异常而结束。

#### <a name="32">创建一个线程的开销</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
JVM 在背后帮我们做了哪些事情：
1. 它为一个线程栈分配内存，该栈为每个线程方法调用保存一个栈帧
2. 每一栈帧由一个局部变量数组、返回值、操作数堆栈和常量池组成
3. 一些支持本机方法的 jvm 也会分配一个本机堆栈
4. 每个线程获得一个程序计数器，告诉它当前处理器执行的指令是什么
5. 系统创建一个与Java线程对应的本机线程
6. 将与线程相关的描述符添加到JVM内部数据结构中
7. 线程共享堆和方法区域


## <a name="33">枚举类</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
枚举类比较使用==，同样也可以使用equals方法，Enum类中重写了equals实际上还是调用==方法。
```
 /**
     * Returns true if the specified object is equal to this
     * enum constant.
     *
     * @param other the object to be compared for equality with this object.
     * @return  true if the specified object is equal to this
     *          enum constant.
     */
    public final boolean equals(Object other) {
        return this==other;
    }
```
为什么使用==比较？
- 因为枚举类在jvm编译成class文件后，实际编译成使用final 修饰的class，final修饰就意味着实例化后不可修改，且都指向堆中的同一个对象


普通的一个枚举类
```
public enum t {
    SPRING,SUMMER,AUTUMN,WINTER;
}
```

反编译后的代码
```
public final class T extends Enum
{
    private T(String s, int i)
    {
        super(s, i);
    }
    public static T[] values()
    {
        T at[];
        int i;
        T at1[];
        System.arraycopy(at = ENUM$VALUES, 0, at1 = new T[i = at.length], 0, i);
        return at1;
    }

    public static T valueOf(String s)
    {
        return (T)Enum.valueOf(demo/T, s);
    }

    public static final T SPRING;
    public static final T SUMMER;
    public static final T AUTUMN;
    public static final T WINTER;
    private static final T ENUM$VALUES[];
    static
    {
        SPRING = new T("SPRING", 0);
        SUMMER = new T("SUMMER", 1);
        AUTUMN = new T("AUTUMN", 2);
        WINTER = new T("WINTER", 3);
        ENUM$VALUES = (new T[] {
            SPRING, SUMMER, AUTUMN, WINTER
        });
    }
}
```

## <a name="34">零散的点</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="35">方法调用的知识点</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 按值调用(call by value)表示方法接收的是调用者提供的值，
- 而按引用调用（call by reference)表示方法接收的是调用者提供的变量地址。
- 方法体传递参数时，无论是值还是对象都是“值”传递。引用类型传递的是引用变量的地址。
```
public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student s1 = new Student("小张");
		Student s2 = new Student("小李");
		Test.swap(s1, s2);
		System.out.println("s1:" + s1.getName());
		System.out.println("s2:" + s2.getName());
	}

	public static void swap(Student x, Student y) {
		Student temp = x;
		x = y;
		y = temp;
		System.out.println("x:" + x.getName());
		System.out.println("y:" + y.getName());
	}
// output
x:小李
y:小张
s1:小张
s2:小李
```
### <a name="36">三大特性</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 封装:封装是指把一个对象的状态信息（也就是属性）隐藏在对象内部，不允许外部对象直接访问对象的内部信息。
- 继承:不同类型的对象，相互之间经常有一定数量的共同点。 extends
- 多态:表示一个对象具有多种的状态。具体表现为父类的引用指向子类的实例。

### <a name="37">序列化与反序列化</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- transient 关键字的作用是：阻止实例中那些用此关键字修饰的的变量序列化；当对象被反序列化时，被 transient 修饰的变量值不会被持久化和恢复。transient 只能修饰变量，不能修饰类和方法。
- 序列化ID：` private static final long serialVersionUID` 该ID决定着是否能够成功反序列化！简单来说，java的序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的。
- 获取键盘输入的两种方式：
```
//通过 Scanner
Scanner input = new Scanner(System.in);
String s  = input.nextLine();
input.close();

方法 2：通过 BufferedReader
BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
String s = input.readLine();
```
- Arrays.asList():返回的并不是 java.util.ArrayList ，而是 java.util.Arrays 的一个内部类,这个内部类并没有实现集合的add()、remove()、clear()会抛出异常unSupportedOperationException。

### <a name="38">java复制</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
对于基本类型，直接赋值复制，对于对象类型分为浅拷贝与深拷贝
1. 浅拷贝：对引用数据类型进行引用传递般的拷贝，此为浅拷贝。
2. 深拷贝：对基本数据类型进行值传递，对引用数据类型，创建一个新的对象，并复制其内容，此为深拷贝。
    - 深拷贝的另一种方式，使用序列化和反序列化，获取一个新对象。
