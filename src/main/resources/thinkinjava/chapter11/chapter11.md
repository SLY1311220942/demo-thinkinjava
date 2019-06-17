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
> 分为两种类型:
* ArrayList  
> 长于随机访问元素，但是插入和移除元素较慢。
* LinkedList  
> 插入和删除代价低，提供了优化的顺序访问，随机访问相对较慢，特性集较ArrayList更大。

## 11.6 迭代器
> 任何容器类，都必须有某种方式可以插入元素并将它们再次取回。毕竟持有事务是容器最基本的工作。

> 如果从更高层次思考，会发现有个缺点：要使用容器，必须对容器确切类型编程。如果只是使用容器并且不关心容器的确切类型，那么如何才能不重写代码就可以应用于不同类型的容器。

> 迭代器（也是一种设计模式）可用于达成此目的。迭代器是一个对象，它的工作是遍历并选择序列中的对象，而客户端程序员不必知道或关系该序列的的底层构造。迭代器通常被称为轻量级对象：创建它们的代价小。

### 11.6.1 ListIterator
> ListIterator是一个更强大的Iterator子类型，它只能用于各种list类型的访问。ListIterator可以双向移动。

## 11.7 LinkedList
> LinkedList还添加了可以使其用作栈，队列和双端队列的方法。

## 11.8 Stack
> “栈通”常指“后进先出”的容器。有时候栈也被称为叠加栈，因为最后“压入”栈的元素，第一个“弹出”栈。

## 11.9 Set
> Set不保存重复元素。如何你试图将相同对象的多个实例添加到Set中，那么他就会阻止这种重复现象。Set中最常使用的是测试归属性，你可以很容易的询问某个对象是否在Set中。

> HashSet是无序的，TreeSet是有序的。

## 11.10 Map
> 将对象映射到其它对象的能力是一种解决编程问题的杀手锏。

## 11.11 Queue


## 11.12 Collection和Iterator


## 11.13 Foreach与迭代器


## 11.14 总结


