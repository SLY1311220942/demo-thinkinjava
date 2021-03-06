# 第九章 接口
> 接口和内部类为我们提供了一种将接口与实现分离的更加结构化的方法。

## 9.1 抽象类和抽象方法
> 包含抽象方法的类叫抽象类，抽象类用abstract修饰，抽象类不能new对象。

> 抽象可以没有抽象方法。即使没有抽象方法抽象类也不能实例化。

> 没有抽象方法的抽象类的价值在于：实例化了没有意义，因为抽象类已经把方法都实现了。而且它不需要通过不同的子类对象来保存不同的状态（针对抽象类里的方法）。

## 9.2 接口
> 一个接口表示：“所有实现了该特定接口的方法看起来都像这样”。

## 9.3 完全解耦
> 如果一个方法操作的是类，那么你就只能使用这个类及其子类。但是如果使用的是接口那么所有实现了该接口的类就都可以使用。使用接口更加的灵活。

## 9.4 Java的多重继承
> 接口可以被多继承。因为接口不含实现，就算多个接口方法重复也没关系，因为实现一定是在导出类中，不存在不知道要执行哪一个的问题。

## 9.5 通过继承来扩展接口
> 通过继承可以很容易地在接口中添加新的方法声明，还可以通过继承在新接口中组合数个接口，这两种情况否可以获得新的接口。

> 在打算组合的接口中使用相同名称的方法通常会造成混乱，尽量避免（参数相同，返回值不同的无法编译通过）。

## 9.6 适配接口
> 接口最吸引人的地方是，同一个接口可以具有很多不同的实现，它的通常提现形式是：一个接受接口类型的方法，而该接口的实现和向该方法传递的对象则取决于方法的使用者。

> “你可以用任何你想要的对象来调用我的方法，只要你的对象遵循我的接口”。

## 9.7 接口中的域
> 因为你放入接口中的任何域都是static和final的，因此接口成为了一种很便捷的用来创建常量组的工具。在Java SE5中没有必要这样使用了，因为有了enum枚举。

> 接口中的域是自动public的。

### 9.7.1 初始化接口中的域
> 接口中的域不能是“空 final”，但是可以被非常量表达式初始化。

> 域是static的，所以他们就在类第一次被加载时初始化，这发生在任何域被首次访问时。

## 9.8 嵌套接口
> 接口可以嵌套在类或其它接口中。

> 实现一个private接口只是一种方式，它可以强制该接口中的方法定义不要添加任何类型信息（也就是说，不允许向上转型）。

## 9.9 接口与工厂
> 接口是实现多重继承的途径，而生成遵循某个接口的对象的典型方式就是工厂方法设计模式。这与直接调用构造器不同，我们在工厂对象上调用的是创建方法，而该工厂对象将生成接口的某个实现对象。理论上通过这种方式我们可以将代码和接口完全分离，这就使我们可以透明的将某个实现替换为另一个实现。

## 9.10 总结
> “确定接口是理想的选择，因而应该总是选择接口而不是具体的类。”这其实是一种引诱，许多人都掉进了这种陷阱。抽象性应该根据实际的需求产生，否则会带来额外的复杂性。恰当的选择是先选择类，当接口变得有必要时进行重构。
 