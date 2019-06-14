# 第十一章 持有对象
> 如果一个程序只包含固定数量的且其生命期都是已知的对象，那么这是一个非常简单的程序。

> 通常，程序总是根据运行时才知道的某些条件去创建对象。在此之前不会知道所需要对象的数量，甚至不知道确切的类型。为了解决这个问题，需要在任意时刻任意位置创造任意数量的对象。所以就不能靠创建命名的引用来持有每一个对象。

## 11.1 泛型和类型安全的容器
> 泛型java5才有的新特性，List<?>尖括号括起来的是类型参数（可以有多个），它指定了这个容器实例可以保存的实例类型。通过使用泛型，就可以在编译期防止将错误类型的对象放置到容器中。在取出对象时也不需要类型转换了。

## 11.2 基本概念
> Java容器类类库的用途是保存对象，其中分为两大类型。
* 1.Collection。一个独立元素的序列，这些元素都服从一条或多条规则。
* 2.Map。一组成对的键值对对象，允许你使用键来查找值。

## 11.3 添加一组容器
> Collection构造器可以接受另一个Collection，用它将自身初始化。但是使用Collection.addAll()要快得多，而且首先创建一个空的list然后使用addAll()这种方式很方便，因此它是首选方式。

## 11.4 容器的打印
```java
public class PrintContainer {
	
	public static Collection<String> fill(Collection<String> collection) {
		collection.add("cat");
		collection.add("dog");
		collection.add("bird");
		collection.add("dog");
		return collection;
	}
	
	public static Map<String,String> fill(Map<String,String> map){
		map.put("cat", "猫");
		map.put("dog", "狗");
		map.put("bird", "鸟");
		map.put("dog", "狗");
		return map;
	}
	
	public static void main(String[] args) {	
		System.out.println(fill(new ArrayList<>()));
		System.out.println(fill(new LinkedList<>()));
		System.out.println(fill(new HashSet<>()));
		System.out.println(fill(new TreeSet<>()));
		System.out.println(fill(new LinkedHashSet<>()));
		
		System.out.println(fill(new HashMap<>()));
		System.out.println(fill(new TreeMap<>()));
		System.out.println(fill(new LinkedHashMap<>()));
	}
}

result:
[cat, dog, bird, dog]
[cat, dog, bird, dog]
[cat, bird, dog]
[bird, cat, dog]
[cat, dog, bird]
{cat=猫, bird=鸟, dog=狗}
{bird=鸟, cat=猫, dog=狗}
{cat=猫, dog=狗, bird=鸟}
```

## 11.5 List


## 11.6 迭代器


## 11.7 LinkedList


## 11.8 Stack


## 11.9 Set


## 11.10 Map


## 11.11 Queue


## 11.12 Collection和Iterator


## 11.13 Foreach与迭代器


## 11.14 总结


