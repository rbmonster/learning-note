 # 注解与spring注解



java.lang.annotation 提供了四种元注解，专门注解其他的注解（在自定义注解的时候，需要使用到元注解）：
1. @Documented：注解是否将包含在JavaDoc中
2. @Retention：什么时候使用该注解
    - RetentionPolicy.SOURCE : 在编译阶段丢弃。这些注解在编译结束之后就不再有任何意义，所以它们不会写入字节码。@Override, @SuppressWarnings都属于这类注解。
    - RetentionPolicy.CLASS : 在类加载的时候丢弃。在字节码文件的处理中有用。注解默认使用这种方式
    - RetentionPolicy.RUNTIME : 始终不会丢弃，运行期也保留该注解，因此可以使用反射机制读取该注解的信息。我们自定义的注解通常使用这种方式。
3. @Target – 注解用于什么地方
    - ElementType.CONSTRUCTOR: 用于描述构造器
    - ElementType.FIELD: 成员变量、对象、属性（包括enum实例）
    - ElementType.LOCAL_VARIABLE: 用于描述局部变量
    - ElementType.METHOD: 用于描述方法
    - ElementType.PACKAGE: 用于描述包
    - ElementType.PARAMETER: 用于描述参数
    - ElementType.TYPE: 用于描述类、接口(包括注解类型) 或enum声明 常见的@Component、@Service
4. @Inherited – 是否允许子类继承该注解
    - @Inherited 元注解是一个标记注解，@Inherited 阐述了某个被标注的类型是被继承的。如果一个使用了@Inherited 修饰的annotation 类型被用于一个class，则这个annotation 将被用于该class 的子类

编写注解的规则
1. Annotation 型定义为@interface。
2. 参数成员只能用public 或默认(default) 这两个访问权修饰
3. 参数成员只能用基本类型byte、short、char、int、long、float、double、boolean八种基本数据类型和String、Enum、Class、annotations等数据类型，以及这一些类型的数组。
4. 要获取类方法和字段的注解信息，必须通过Java的反射技术来获取 Annotation 对象

## @Valid 字段验证注解

1. 新增注解类
```java


@Documented
@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = SupportCurrencyValidator.class
)
public @interface SupportCurrency {

    boolean required() default true;

    int length() default 3;

    // 用于错误抛出的消息提醒
//    String message() default "{javax.validation.constraints.DecimalMin.message}";
    String message() default "系统不支持该币种交易";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

```