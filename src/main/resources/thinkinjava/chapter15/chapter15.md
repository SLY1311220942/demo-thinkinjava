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

### 15.4.1 杠杆利用类型推断
> 只有在赋值时类型推断才有效。

## 15.5 匿名内部类

## 15.6 构建复杂模型

## 15.7 擦除神秘之处

## 15.8 擦除的补偿

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