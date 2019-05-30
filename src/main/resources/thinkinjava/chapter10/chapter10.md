# 第十章 内部类
> 可以将一个类的定义放到另一个类的定义内部，这就是内部类。

> 内部类是一种非常有用的特性，因为它允许你把一下逻辑相关的类组织在一起，并控制位于内部的类的可视性。然而必须要了解，内部类和组合完全是不同的概念，这一点很重要。

## 10.1 创建内部类
> 创建内部类就如同你想的一样将类的定义放到外围内里面。

```java
/**
 * 创建内部类
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class Parcel1 {

	class Contents {
		private int i = 11;

		public int value() {
			return i;
		}
	}

	class Destination {
		private String label;

		public Destination(String whereTo) {
			this.label = whereTo;
		}

		String readLabel() {
			return label;
		}
	}
	
	public void ship(String dest) {
		Contents contents = new Contents();
		Destination destination = new Destination(dest);
		System.out.println(contents.value() + ":" + destination.readLabel());
	}
	
	public static void main(String[] args) {
		Parcel1 parcel1 = new Parcel1();
		parcel1.ship("火星");
	}
}
```

> 返回一个指向内部类的引用（比如to()方法）

```java
/**
 * 返回一个内部类的引用
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class Parcel2 {
	class Contents {
		private int i = 11;

		public int value() {
			return i;
		}
	}
	
	class Destination {
		private String label;

		public Destination(String whereTo) {
			this.label = whereTo;
		}

		String readLabel() {
			return label;
		}
	}
	
	public Destination to(String dest) {
		return new Destination(dest);
	}
	
	public Contents contents() {
		return new Contents();
	}
	
	public void ship(String dest) {
		Contents contents = contents();
		Destination destination = to(dest);
		System.out.println(contents.value() + ":" + destination.readLabel());
	}
	
	public static void main(String[] args) {
		Parcel2 parcel2 = new Parcel2();
		parcel2.ship("木星");
		
		Destination destination = parcel2.to("土星");
		System.out.println(destination.readLabel());
	}
}
```


## 10.2 链接到外部类
> 当生成一个内部类对象时，此对象与制造它的外围对象（enclosing object）之间就有了一种联系，所以它能访问外围对象的所有成员，而不需要任何特殊环境。此外，内部类还拥有其外围类的所有元素的访问权。

> 当某个外围类对象创建一个内部类对象时，此内部类对象必定会秘密捕获一个指向外围类对象的引用。然后在你访问此外围类成员时，就是用那个引用来选择外围类的成员。

> 下面是一个“迭代器”模式的例子。

```java
/**
 * 选择器接口
 * 
 * @author sly
 * @time 2019年5月28日
 */
public interface Selector {
	boolean end();

	Object current();

	void next();
}

/**
 * 内部类可以访问外围类的方法和字段
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class Sequence {
	private Object[] items;
	private int next = 0;

	public Sequence(int size) {
		items = new Object[size];
	}

	public void add(Object object) {
		if (next < items.length) {
			items[next++] = object;
		}
	}

	private class SequenceSelector implements Selector {
		private int i = 0;

		@Override
		public boolean end() {
			return i == items.length;
		}

		@Override
		public Object current() {
			return items[i];
		}

		@Override
		public void next() {
			if (i <= items.length) {
				i++;
			}
		}

	}

	public Selector selector() {
		return new SequenceSelector();
	}

	public static void main(String[] args) {
		Sequence sequence = new Sequence(10);
		for (int i = 0; i < 10; i++) {
			sequence.add(Integer.toString(i));
		}
		Selector selector = sequence.selector();
		while (!selector.end()) {
			System.out.println(selector.current());
			selector.next();
		}
	}

}
```

## 10.3 使用this和new
> 当你需要生成对外部类对象的引用，可以使用外部类的名字后紧跟“.this”。这样产生的引用自动具有正确的类型，这一点在编译期就被知晓并受到检查，因此没有任何运行时开销。

```java
/**
 * .this
 * @author sly
 * @time 2019年5月28日
 */
public class DotThis {
	public void f() {
		System.out.println("DotThis.this.f()");
	}
	
	public class Inner {
		public void f() {
			System.out.println("Inner.f()");
		}
		
		public DotThis outer() {
			return DotThis.this;
		} 
	}
	
	public Inner inner() {
		return new Inner();
	}
	
	public static void main(String[] args) {
		DotThis dotThis = new DotThis();
		DotThis.Inner inner = dotThis.inner();
		inner.outer().f();
		inner.f();
	}
}
```

## 10.4 内部类向上转型
> 

## 10.5 在方法和作用域内的内部类
> 

## 10.6 匿名内部类
> 

## 10.7 嵌套类
> 

## 10.8 为什么需要内部类
> 

## 10.9 内部类的继承
> 

## 10.10 内部类可以被覆盖吗
> 

## 10.11 局部内部类
> 

## 10.12 内部类标识符
> 

## 10.13 总结
> 
