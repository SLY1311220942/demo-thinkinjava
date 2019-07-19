# 第十四章 类型信息
> 运行时类型信息使得你可以在程序运行时发现和使用类型信息。

## 14.1 为什么需要RTTI（Run-Time Type Identification）
> 运行时类型识别（RTTI）

## 14.2 Class对象
> Class对象包含了与类有关的信息。Class对象就是用来创建类的所有的“常规”对象的。Class对象被保存在一个同名的.class文件中。为了生成这个对象，运行这个Java程序的虚拟机（JVM）将使用被称为“类加载器”的子系统。

> 所有类都是在对其第一次使用时，动态加载到JVM中的。当程序创建第一个对类的静态成员引用时，就会加载这个类。这个证明构造器也是类的静态方法，即使构造器之前没有使用static关键字。

> 类加载器首先检查这个类的Class对象是否已经加载，如果尚未加载，默认类加载器就会根据类名查找.class文件。在这个类的字节码被加载时，它会接受验证，以确保其没有被破坏，并且不包含不良java代码。

```java
class Candy {
	static {
		System.out.println("Loading Candy");
	}
}

class Gum {
	static {
		System.out.println("Loading Gum");
	}
}

class Cookie {
	static {
		System.out.println("Loading Cookie");
	}
}

public class SweetShop {
	public static void main(String[] args) throws ClassNotFoundException {
		new Candy();
		System.out.println("-------------------");
		Class.forName("com.sly.demo.thinkinjava.chapter14.load.Gum");
		System.out.println("-------------------");
		new Cookie();
	}
}

result:
Loading Candy
-------------------
Loading Gum
-------------------
Loading Cookie
```

### 14.2.1 类字面常量
> Java还提供另一种方式生成对Class对象的引用，即使用类字面常量。  
> Cookie.class;

> 这样不仅更简单，而且更安全，因为它在编译时就会收到检查。并且它根除了对forName()方法的调用，所以也更高效。

> 类字面常量不仅可以用于普通类，也可用于接口、数组以及基本类型数据。另外基本类型包装类有个字段TYPE，指向其对应的基本类型Class的引用。

 |     |     |
 | :-: | :-: |
 | boolean.class | Boolean.TYPE |
 | char.class	 | Character.TYPE |
 | byte.class	 | Byte.TYPE |
 | short.class	 | Short.TYPE |
 | int.class	 | Integer.TYPE |
 | long.class	 | Long.TYPE |
 | float.class	 | Float.TYPE |
 | double.class	 | Double.TYPE |
 | void.class	 | Void.TYPE |

> 使用.class方式来创建对Class对象引用时并不会自动的初始化该Class对象。初始化被延迟到了对静态方法或者非常数静态域进行首次引用时才执行。

```java
class InitTable {
	static final int staticFinal = 47;
	static final int staticFinal2 = ClassInitialization.rand.nextInt(1000);
	static {
		System.out.println("Initializing InitTable");
	}
}

class InitTable2 {
	static final int staticNonFinal = 147;
	static {
		System.out.println("Initializing InitTable2");
	}
}

class InitTable3 {
	static final int staticNonFinal = 74;
	static {
		System.out.println("Initializing InitTable3");
	}
}

public class ClassInitialization {
	public static Random rand = new Random(47);
	
	public static void main(String[] args) throws ClassNotFoundException {
		Class initTable= InitTable.class;
		System.out.println("-------------after create InitTable ref------------");
		System.out.println(InitTable.staticFinal);
		System.out.println(InitTable.staticFinal2);
		System.out.println(InitTable2.staticNonFinal);
		Class<?> initTable3 = Class.forName("com.sly.demo.thinkinjava.chapter14.load.InitTable3");
		System.out.println("-------------after create InitTable3 ref------------");
		System.out.println(InitTable3.staticNonFinal);
	}
}

result:
-------------after create InitTable ref------------
47
Initializing InitTable
258
147
Initializing InitTable3
-------------after create InitTable3 ref------------
74
```

> 为使用类而做的准备工作分三步：
* 1.加载，这是由类加载器执行的。该步骤将查找字节码，并从这些字节码中创建一个Class对象。
* 2.链接，在链接阶段将验证类中的字节码，为静态域分配存储空间，并且如果必须的话，将解析这个类创建的对其他类的所有引用。
* 3.初始化，如果该类具有超类，则对其初始化，执行静态初始化器和静态初始化块

### 14.2.2 泛化的Class引用
> 为了在使用泛化的Class引用时放松限制，我使用了通配符，它是Java泛型的一部分。通配符是“?”，表示“任何事物”。

> Class<?>优于平凡的Class，即便它们是等价的。

> 为了创建一个Class引用，它被限制为某种类型，或该类型的子类型，你需要将通配符和extends关键字结合，创建一个范围：Class<? extends Number>。

> 如果你手头是超类，那编译器只允许你声明超类引用的是“某个类，它是FancyToy的超类”。就像Class<? Super FancyToy>。这看上去有些奇怪，因为getSuperClass返回的是基类（不是接口），并且在编译期就知道它是什么类型了。

```java
class Toy {

}

class FancyToy extends Toy {

}

public class ToyTest {
	public static void main(String[] args) throws Exception {
		Class<FancyToy> ftClass = FancyToy.class;
		FancyToy fancyToy = ftClass.newInstance();
		Class<? super FancyToy> up = ftClass.getSuperclass();
		// 编译不通过
		// Class<Toy> superclass = ftClass.getSuperclass();
		Object newInstance = up.newInstance();
	}
}
```

### 14.2.3 新的转型语法
> Class引用的转型语法，cast()方法。cast()方法接受参数对象，并将其转型为Class引用的类型。

## 14.3 类型转换前先做检查
> 已知的几种RTTI形式
* 1.传统的类型转换。由RTTI确保类型转换的正确性，如果执行了一个错误的类型转换，就会抛出一个ClassCastException异常。
* 2.代表对象的类型的Class对象。通过查询Class对象可以获取运行时所需的信息。
* 3.关键字instanceof。它返回一个布尔值，告诉我们对象是不是某个特定类型的实例。

### 14.3.1 使用类字面常量
> Pet.class;

### 14.3.2 动态的instanceof
> Class对象的isInstance()方法提供了一种动态的检查对象的途径。

### 14.3.3 动态的instanceof
> Class对象的isAssignableFrom()方法判断是否为某个类的父类。

## 14.4 注册工厂
> 说白了就是讲能生成的目标类型统一放到一处管理起来。

```java
public interface Factory<T> {
    T create();
}

class Part {
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    static List<Factory<? extends Part>> partFactories = new ArrayList<Factory<? extends Part>>();
    static {
        partFactories.add(new FuelFilter.Factory());
        partFactories.add(new AirFilter.Factory());
        partFactories.add(new FanBelt.Factory());
        partFactories.add(new GeneratorBelt.Factory());
    }
    
    private static Random rand = new Random(47);
    
    public static Part createRandom() {
        int n = rand.nextInt(partFactories.size());
        return partFactories.get(n).create();
    }
}

class Filter extends Part {

}

class FuelFilter extends Filter {
    public static class Factory implements com.sly.demo.thinkinjava.chapter14.registryfactory.Factory<FuelFilter> {
        @Override
        public FuelFilter create() {
            return new FuelFilter();
        }
    }
}

class AirFilter extends Filter {
    public static class Factory implements com.sly.demo.thinkinjava.chapter14.registryfactory.Factory<AirFilter> {
        @Override
        public AirFilter create() {
            return new AirFilter();
        }
    }
}

class Belt extends Part {

}

class FanBelt extends Belt {
    public static class Factory implements com.sly.demo.thinkinjava.chapter14.registryfactory.Factory<FanBelt> {
        @Override
        public FanBelt create() {
            return new FanBelt();
        }
    }
}

class GeneratorBelt extends Belt {
    public static class Factory implements com.sly.demo.thinkinjava.chapter14.registryfactory.Factory<GeneratorBelt> {
        @Override
        public GeneratorBelt create() {
            return new GeneratorBelt();
        }
    }
}

public class RegistryFactory {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Part.createRandom());
        }
    }
}

result:
FanBelt
AirFilter
FanBelt
FuelFilter
FuelFilter
FanBelt
FuelFilter
AirFilter
FanBelt
FanBelt
```

## 14.5 instanceof与Class的等价性


## 14.6 反射：运行时的类信息


## 14.7 动态代理


## 14.8 空对象


## 14.9 接口与类型信息


## 14.10 总结

