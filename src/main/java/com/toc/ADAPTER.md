<a name="index">**Index**</a>

<a href="#0">适配器模式</a>  
# <a name="0">适配器模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 适配器模式，作为连接两个接口的桥梁,把一个类的接口变换成客户端所期待的另一种接口，从而使原本接口不匹配而无法一起工作的两个类能够在一起工作。

- target: 期待调用的接口
- Adaptee：需要适配的接口
- Adapter： 适配器通过继承Target，调用target的接口，实际适配到调用Adaptee方法

- another version中的方法为抽象了Adaptee，适配继承的情况。并在Adapter向上转型设值调用

![Image text](https://upload-images.jianshu.io/upload_images/944365-24c6bf44da1b79ad.png?imageMogr2/auto-orient/strip|imageView2/2/w/510/format/webp)