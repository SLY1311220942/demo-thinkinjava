# 第十五章 泛型
> 一般的类和方法，只能使用具体的类型：要么是基本类型，要么是自定义类。如果需要编写可以应用于多种类型的代码，这种刻板的限制对代码的束缚就会很大。

## 15.1 与C++的比较
> 了解C++模板有助于了解Java泛型的基础

## 15.2 简单泛型
> 泛型出现最引人注意的原因是为了创建容器类。

### 15.2.1 一个元组类库
> 因为方法只能返回一个对象，所以需要一个对象对返回的多个对象进行包装。

> 这个概念称为元组，它是将一组对象打包存储于其中一个单一对象。这个容器运行读取其中的元素，但不允许向其中放入新对象（这个概念也被称为数据传送对象或信使）。

### 15.2.1 一个堆栈类
```java
public class LinkedStack<T> {
	private static class Node<U> {
		U item;
		Node<U> next;
		Node(){
			item = null;
			next = null;
		}
		Node(U item,Node<U> next){
			this.item = item;
			this.next = next;
		}
		boolean end() {
			return item == null && next == null;
		}
	}
	
	private Node<T> top = new Node<T>();
	
	public void push(T item){
		top = new Node<T>(item, top);
	}
	
	public T pop() {
		T result = top.item;
		if(!top.end()) {
			top = top.next;
		}
		return result;
	}
	
	public static void main(String[] args) {
		LinkedStack<String> lss = new LinkedStack<>();
		for (String s : "phaser or stun!".split(" ")) {
			lss.push(s);
		}
		String s;
		while ((s = lss.pop()) != null) {
			System.out.println(s);
		}
	}
}
```
> 这个例子使用了一个末端哨兵来判断堆栈何时为空。


## 15.3 泛型接口
> 泛型也可用于接口。例如生成器，这是一种专门创建对象的类。实际上，这是工厂设计模式的一种应用。

```java
public class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {
	private Class[] types = { Latter.class, Mocha.class, Cappuccino.class, Americano.class, Breve.class };

	private Random random = new Random(47);

	private int size = 0;

	public CoffeeGenerator() {
	}

	public CoffeeGenerator(int size) {
		this.size = size;
	}

	@Override
	public Iterator<Coffee> iterator() {
		return new CoffeeIterator();
	}

	@Override
	public Coffee next() {
		try {
			return (Coffee) types[random.nextInt(types.length)].newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	class CoffeeIterator implements Iterator<Coffee> {
		int count = size;

		@Override
		public boolean hasNext() {

			return count > 0;
		}

		@Override
		public Coffee next() {
			count--;
			return CoffeeGenerator.this.next();
		}

	}

}
```

## 15.4 泛型方法
> 泛型也可以用在方法上。定义泛型方法只需要将泛型参数列表置于返回值之前。

```
public class GenericMethod {
    public <T> void f(T t) {
        System.out.println(t.getClass().getSimpleName());
    }
    
    
    public static void main(String[] args) {
        GenericMethod genericMethod = new GenericMethod();
        genericMethod.f(1);
        genericMethod.f("");
        genericMethod.f('1');
        genericMethod.f(true);
    }
}

result:
Integer
String
Character
Boolean
```


### 15.4.1 杠杆利用类型推断
> 这种情况在1.8里已经没有了。应该是已经实现了根据定义来推断类型。

```
Map<String, List<? extends PetTest>> map = new HashMap<String, List<? extends PetTest>>();
```

> 只有在赋值时类型推断才有效。这一条在1.8里同样不适用了。

```
public void f2(Map<String, List<? extends PetTest>> map) {
        
}

public static <K,V> Map<K,V> map(){
    //return new HashMap<K,V>();
    return new HashMap<>();
}

//这行不报错
genericMethod.f2(GenericMethod.map());
```

> 换句话说这个当初令作者吐槽的问题已经解决了。

### 15.4.2 可变参数的泛型方法
> 常见于参数为list的，必须写在方法最后，且一个方法只能有一个。

```
public void f3(String...names) {
    for (String name : names) {
        System.out.println(name);
    }
}
```

### 15.4.3 用于Generator的泛型方法
> 利用生成器我么可以很方便的填充一个Collection。

### 15.4.4 一个通用的Generator
> 默认调用无参构造方法来生成对象，这也就是为什么创建一个类最好有无参构造方法的原因，如果没有很多框架或者工具就没法用了，应为他们new对象一般使用默认构造方法。

### 15.4.5 简化元组的使用
> 就是利用重载多写几个。

### 15.4.6 一个实用的Set类
> 用来做集合相关操作，相加，相交，相减...

## 15.5 匿名内部类
> 泛型可以用于内部类以及匿名内部类。

## 15.6 构建复杂模型
> 泛型的一个重要好处是能够简单而安全的创建复杂的模型，例如我们可以简单的创建list元组。

```
public class TupleList<A,B,C,D> extends ArrayList<FourTuple<A,B,C,D>>{
	public static void main(String[] args) {
		TupleList<Vehicle,Amphibian,String,Integer> tl = new TupleList<Vehicle,Amphibian,String,Integer>();
		tl.add(TupleTest.h());
		tl.add(TupleTest.h());
		for(FourTuple<A,B,C,D> i:tl){
			System.out.println(i);
		}
	}
}
```

## 15.7 擦除的神秘之处

### 15.7.1 C++方式
```
public static void main(String[] args) {
	Class c1 = new ArrayList<Integer>().getClass();
	Class c2 = new ArrayList<String>().getClass();
	System.out.println(c1 == c2);
}

result:
true
```

> 根据jdk文档的描述，Class.getTypeParameters()将“返回一个TypeVariable对象数组，表示有泛型声明所声明的参数”。但是实际上你能发现的只有用作参数占位符的标识符。

> 在泛型代码内部，无法获得任何有关泛型参数类型的信息。

> Java泛型是使用擦除来实现的，这意味着当你使用泛型时，任何具体的类型信息都被擦除了，你唯一知道的就是你在使用一个对象。

> 为了解决类型被擦除导致一些操作无法执行，可以给泛型定义边界例如：Manipulator<T extends HasF>。泛型参数将擦除到它遇到的第一个边界。

### 15.7.2 迁移兼容性
> 之所以使用擦除来实现泛型是应为要兼容以前的类库代码，所以这只是个无奈的选择并不是语言的特色。

### 15.7.3 擦除的问题
> 擦除的代价是：泛型不能用于显示的引用运行时类型的操作之中。例如转型、instanceof操作和new表达式。因为所有关于参数的信息都丢失了，无论何时你在编写泛型代码时，都要提醒自己，你只是看起来好像拥有有关参数类型信息而已。

### 15.7.4 边界处的动作
> 

## 15.8 擦除的补偿
> 

## 15.9 边界

## 15.10 通配符

## 15.11 问题

## 15.12 自限定的类型

## 15.13 动态类型安全

## 15.14 异常

## 15.15 混型

## 15.16 潜在类型机制

## 15.17 对缺乏潜在类型机制的补偿

## 15.18 将函数对象用作策略

## 15.19 总结：转型整的如此之糟吗？