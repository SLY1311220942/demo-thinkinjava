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

```java
/**
 * .new
 * @author sly
 * @time 2019年5月28日
 */
public class DotNew {
    public class Inner {
        public Inner() {
            System.out.println("new inner");
        }
    }

    public static void main(String[] args) {
        DotNew dotNew = new DotNew();
        Inner inner = dotNew.new Inner();
    }
}
```

```java
/**
 * .new在parcel上的应用
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class Parcel3 {
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

    public static void main(String[] args) {
        Parcel3 parcel3 = new Parcel3();
        Parcel3.Contents contents = parcel3.new Contents();
        Parcel3.Destination destination = parcel3.new Destination("水星");
        System.out.println(contents.value() + ":" + destination.readLabel());
    }
}
```

## 10.4 内部类向上转型
> 当将内部类向上转型为其基类，尤其是转型为一个接口的时候，内部类就有了用武之地。这是因为此内部类--某个接口的实现--能够完全看不可见，并且不可用。所得到的只是指向基类或接口的引用，所以能够很方便地隐藏实现细节。

```java
/**
 * 
 * @author sly
 * @time 2019年5月28日
 */
public interface Contents {
    int value();
}

/**
 * 
 * @author sly
 * @time 2019年5月28日
 */
public interface Destination {
    String readLabel();
}

/**
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class Parcel4 {
    private class PContents implements Contents {
        private int i = 11;

        @Override
        public int value() {
            return i;
        }

    }

    protected class PDestination implements Destination {
        private String label;

        private PDestination(String dest) {
            label = dest;
        }

        @Override
        public String readLabel() {
            return label;
        }
    }

    public Contents contents() {
        return new PContents();
    }

    public Destination destination(String dest) {
        return new PDestination(dest);
    }
}
```

## 10.5 在方法和作用域内的内部类
> 一个定义在方法中的类。

```java
import com.sly.demo.thinkinjava.chapter10.parcel4.Destination;

/**
 * 一个定义在方法中的类
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class Parcel5 {

    public Destination destination(String dest) {
        class PDestination implements Destination {
            private String label;

            private PDestination(String dest) {
                label = dest;
            }

            @Override
            public String readLabel() {
                return label;
            }
        }
        
        return new PDestination(dest);
    }
    
    public static void main(String[] args) {
        Parcel5 parcel5 = new Parcel5();
        Destination destination = parcel5.destination("土星");
        System.out.println(destination.readLabel());
    }
}
```

> 一个定义在作用域内的类，此作用域的方法的内部。

```java
/**
 * 一个定义在作用域内的类，此作用域在方法的内部
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class Parcel6 {
    
    private void internalTracking(boolean b) {
        if(b) {
            class TrackingShip{
                private String id;
                TrackingShip(String s){
                    id = s;
                }
                String getShip() {
                    return id;
                }
            }
            TrackingShip ship = new TrackingShip("ship");
            String s = ship.getShip();
            System.out.println(s);
        }
    }
    
    public void track() {
        internalTracking(true);
    }
    
    public static void main(String[] args) {
        Parcel6 parcel6 = new Parcel6();
        parcel6.track();
    }
}
```

## 10.6 匿名内部类
> 一个实现了接口的匿名类。

```java
/**
 * 一个实现了接口的匿名类
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class Parcel7 {
    public Contents contents() {
        return new Contents(){
            private int i = 15;

            @Override
            public int value() {
                return i;
            }
        };
    }
    
    public static void main(String[] args) {
        Parcel7 parcel7 = new Parcel7();
        Contents contents = parcel7.contents();
        System.out.println(contents.value());
    }
}

/**
 * 创建一个继承自接口的匿名类对象
 * @author sly
 * @time 2019年5月28日
 */
public class Parcel7b {
    class PContents implements Contents {
        private int i = 16;

        @Override
        public int value() {
            return i;
        }
    }

    public Contents contents() {
        return new PContents();
    }

    public static void main(String[] args) {
        Parcel7b parcel7b = new Parcel7b();
        Contents contents = parcel7b.contents();
        System.out.println(contents.value());
    }
}
```

> 一个匿名类，它扩展了有非默认构造器的类。

```java
/**
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class Wapping {
    private int x;
    
    public Wapping(int x) {
        this.x = x;
    }
    
    public int value() {
        return x;
    }
}

/**
 * 一个匿名类，它扩展了有非默认构造器的类
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class Parcel8 {
    
    /**
     * Wapping只是具有具体实现的普通类，但它还是被其导出类当作公共“接口”使用
     * @param x
     * @return
     * @author sly
     * @time 2019年5月28日
     */
    public Wapping wapping(int x) {
        return new Wapping(x) {
            @Override
            public int value() {
                return super.value() * 47;
            }
        };
    }
    
    public static void main(String[] args) {
        Parcel8 parcel8 = new Parcel8();
        Wapping wapping = parcel8.wapping(10);
        System.out.println(wapping.value());
    }

}
```

> 一个匿名类，它执行字段初始化。

```java
import com.sly.demo.thinkinjava.chapter10.parcel4.Destination;

/**
 * 一个匿名类，它执行字段的初始化
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class Parcel9 {
    /**
     * “如果定义一个匿名内部类，并且希望它使用一个在其外部定义的对象，那么编译器会要求其参数引用是final的”，
     *  如果你使用jdk1.8版本不写final是不会报错的，因为编译器默认为final。
     * @param dest
     * @return
     * @author sly
     * @time 2019年5月30日
     */
    public Destination destination(final String dest) {
        return new Destination() {
            private String lable = dest;
            @Override
            public String readLabel() {
                return lable;
            }
        };
    }
    
    public static void main(String[] args) {
        Parcel9 parcel9 = new Parcel9();
        Destination destination = parcel9.destination("海王星");
        System.out.println(destination.readLabel());
    }
}
```

> 一个匿名类，它通过实例初始化实现构造（匿名类不可能有构造器）。

```java
/**
 * 
 * @author sly
 * @time 2019年5月30日
 */
public abstract class Base {
    public Base(int i) {
        System.out.println("Base Constructor i = " + i);
    }

    abstract void f();
}

/**
 * 一个匿名类，它通过实例初始化实现构造器（匿名类不可能有命名构造器）
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class Parcel10 {

    /**
     * 这里的i不需要是final的，因为他是传递给基类使用而不是在匿名内部类中使用。
     * 
     * @param i
     * @return
     * @author sly
     * @time 2019年5月30日
     */
    public Base getBase(int i) {
        return new Base(i) {

            @Override
            void f() {
                System.out.println("I'am f();");
            }
        };
    }

    public static void main(String[] args) {
        Parcel10 parcel10 = new Parcel10();
        Base base = parcel10.getBase(45);
        base.f();
    }
}
```

### 10.6.1 再访工厂方法
> 使用内部类实现工厂方法。

> 可以看到ServiceImpl1、ServiceImpl2构造方法都是私有的，并且没有任何必要去创建作为工厂的具名类。另外你只需要单一的工厂对象，因此本例中它被创建为Service实现中的一个static域。这样的语法更有实际意义。

```java
/**
 * service接口
 * 
 * @author sly
 * @time 2019年5月31日
 */
public interface Service {
	/**
	 * 方法二
	 * 
	 * @author sly
	 * @time 2019年5月31日
	 */
	void methodOne();

	/**
	 * 方法一
	 * 
	 * @author sly
	 * @time 2019年5月31日
	 */
	void methodTwo();
}

/**
 * service工厂接口
 * 
 * @author sly
 * @time 2019年5月31日
 */
public interface ServiceFactory {
	Service getService();
}

/**
 * Service具体实现一
 * 
 * @author sly
 * @time 2019年5月31日
 */
public class ServiceImpl1 implements Service {

	private ServiceImpl1() {

	}

	@Override
	public void methodOne() {
		System.out.println("ServiceImpl1:methodOne");
	}

	@Override
	public void methodTwo() {
		System.out.println("ServiceImpl1:methodTwo");
	}

	public static ServiceFactory factory = new ServiceFactory() {

		@Override
		public Service getService() {
			return new ServiceImpl1();
		}

	};

}

/**
 * Service具体实现二
 * 
 * @author sly
 * @time 2019年5月31日
 */
public class ServiceImpl2 implements Service {

	private ServiceImpl2() {

	}

	@Override
	public void methodOne() {
		System.out.println("ServiceImpl2:methodOne");
	}

	@Override
	public void methodTwo() {
		System.out.println("ServiceImpl2:methodTwo");
	}

	public static ServiceFactory factory = new ServiceFactory() {

		@Override
		public Service getService() {
			return new ServiceImpl2();
		}
	};

}

/**
 * 
 * @author sly
 * @time 2019年5月31日
 */
public class Factories {
	public static void serviceCustomer(ServiceFactory factory) {
		Service service = factory.getService();
		service.methodOne();
		service.methodTwo();
	}
	
	public static void main(String[] args) {
		serviceCustomer(ServiceImpl1.factory);
		System.out.println("==========================");
		serviceCustomer(ServiceImpl2.factory);
	}
}
```

## 10.7 嵌套类
> 如果不需要内部类对象与其外围类对象之间有联系，那么可以将内部类声明为static。这通常称为嵌套类。
* 要创建嵌套类对象，并不需要其外围类对象。
* 不能从嵌套类对象中访问非静态的外围类对象。

> 嵌套类与普通内部类还有一个区别。普通内部类的字段与方法，只能放在类的外部层次上，所有普通内部类不能有static数据和static字段，也不能包含嵌套类。但嵌套类可以包含所有这些东西。

```java
import com.sly.demo.thinkinjava.chapter10.parcel4.Contents;
import com.sly.demo.thinkinjava.chapter10.parcel4.Destination;

/**
 * 
 * @author sly
 * @time 2019年5月31日
 */
public class Parcel11 {
    private static class ParcelContents implements Contents {
        private int i = 11;

        @Override
        public int value() {
            return i;
        }

    }

    protected static class ParcelDestination implements Destination {
        private String lable;

        public ParcelDestination(String whereTo) {
            lable = whereTo;
        }

        @Override
        public String readLabel() {
            return lable;
        }

        // 嵌套类可以包含其它静态元素
        static int x = 10;

        public static void f() {
            System.out.println(x);
        };

        static class AnotherLevel {
            static int x = 17;

            public static void f() {
                System.out.println(x);
            };

        }
        
    }
    
    public static Destination destination(String s) {
        return new ParcelDestination(s);
    }
    
    public static Contents contents() {
        return new ParcelContents();
    }
    
    public static void main(String[] args) {
        Destination destination = destination("冥王星");
        Contents contents = contents();
        System.out.println(contents.value());
        System.out.println(destination.readLabel());
        
    }
}
```

### 10.7.1 接口内部的类
> 正常情况下，不能再接口内部放置任何代码，但嵌套类可以作为接口的一部分。你放到接口中的任何类都自动是public和static的。因为类是static的，只是将嵌套类置于接口的命名空间内，这并不违反接口的规则。

```java
/**
 * 接口嵌套类
 * 
 * @author sly
 * @time 2019年6月4日
 */
public interface ClassInInterface {
    void how();
    class TestClassInInterface implements ClassInInterface{

        @Override
        public void how() {
            System.out.println("TestClassInInterface");
        }
        
        public static void main(String[] args) {
            System.out.println("TestClassInInterface");
        }
    }
}
```

### 10.7.2 从多层嵌套类中访问外部类的成员
> 一个内部类被嵌套多少层并不重要--它能透明地访问所有它所嵌入的外围类的所有成员。

```java
/**
 * 
 * @author sly
 * @time 2019年6月4日
 */
class MNA {
    private void f() {
        System.out.println("fff");
    }

    class A {
        private void g() {
            System.out.println("ggggg");
        }

        public class B {
            void h() {
                f();
                g();
            }
        }
    }
}

public class MultiNestingAccess {
    public static void main(String[] args) {
        MNA mna = new MNA();
        A mnaa = mna.new A();
        B mnaab = mnaa.new B();

        mnaab.h();
    }
}
```

## 10.8 为什么需要内部类
> 每个内部类都能独立地继承自一个（接口的）实现，所以无论外围类是否已经继承了某个（接口的）实现，对于内部类都没有影响。

> 如果没有内部类提供的、可以继承多个具体的或抽象的类的能力，一些设计与编程问题就难以解决。从这个角度看，内部类使得多重继承的解决方案变得完整。接口解决了部分问题，而内部类有效的实现了“多重继承”。也就是说，内部类允许继承多个非接口类型（类或抽象类）。

```java
interface A {

}

interface B {

}

class X implements A, B {

}

class Y implements A {
	B makeB() {
		return new B() {

		};
	}
}

/**
 * 
 * @author sly
 * @time 2019年6月6日
 */
public class MultiInterfaces {
	static void takesA(A a) {

	}

	static void takesB(B b) {

	}

	public static void main(String[] args) {
		X x = new X();
		Y y = new Y();
		takesA(x);
		takesA(y);
		takesB(x);
		takesB(y.makeB());
	}
}
```

> 使用内部类还可以获得其它特性。
> * 1.内部类可以有多个实例，每个实例都有自己的状态信息，并且与其外围类对象的信息相互独立。
> * 2.在单个外围类中，可以让多个内部类以不同的方式实现同一个接口，或继承同一个类。
> * 3.创建内部类对象的时刻并不依赖于外围类对象的创建。
> * 4.内部类并没有令人疑惑的“is-a”关系，它就是一个独立的实体。

```java
class D {

}

abstract class E {

}

class Z extends D {
	E makeE() {
		return new E() {

		};
	}
}

/**
 * 
 * @author sly
 * @time 2019年6月6日
 */
public class MultiImplementation {
	static void takeD(D d) {

	}

	static void takeE(E e) {

	}

	public static void main(String[] args) {
		Z z = new Z();
		takeD(z);
		takeE(z.makeE());
	}
}
```

### 10.8.1 闭包与回调
> 闭包（closure）是一个可调用的对象，它记录了一些信息，这些信息来自于创建它的作用域。可以看出内部类是面向对象的闭包，因为它不仅包含外围类对象（创建内部类的作用域）的信息。还自动拥有一个指向此外围类对象的引用，在此作用域内，内部类有权操作所以成员，包括private成员。

```java
interface Incrementable {
	void increment();
}

class Callee1 implements Incrementable {
	private int i = 0;
	@Override
	public void increment() {
		i ++;
		System.out.println(i);
	}

}

class MyIncrement {
	public void increment() {
		System.out.println("other operation");
	}

	static void f(MyIncrement myIncrement) {
		myIncrement.increment();
	}
}

class Callee2 extends MyIncrement {
	private int i = 0;

	public void increment() {
		super.increment();
		i++;
		System.out.println(i);
	}

	private class Closure implements Incrementable {
		@Override
		public void increment() {
			Callee2.this.increment();
		}
	}

	Incrementable getCallBackReference() {
		return new Closure();
	}
}

class Caller {
	private Incrementable callbackReference;

	Caller(Incrementable incrementable) {
		callbackReference = incrementable;
	}

	void go() {
		callbackReference.increment();
	}
}

public class CallBacks {
	public static void main(String[] args) {
		Callee1 callee1 = new Callee1();
		Callee2 callee2 = new Callee2();
		
		MyIncrement.f(callee2);
		
		Caller caller1 = new Caller(callee1);
		Caller caller2 = new Caller(callee2.getCallBackReference());
		
		System.out.println("===========");
		
		caller1.go();
		caller1.go();
		System.out.println("===========");
		caller2.go();
		caller2.go();
	}
}
```

### 10.8.2 内部类与控制框架
> 应用程序框架就是被设计用来解决某类特定问题的一个类或一组类。

```java
/**
 * 
 * @author sly
 * @time 2019年6月6日
 */
public abstract class Event {
	private long eventTime;
	protected final long delayTime;

	public Event(long delayTime) {
		this.delayTime = delayTime;
	}

	public void start() {
		eventTime = System.nanoTime() + delayTime;
	}

	public boolean ready() {
		return System.nanoTime() >= eventTime;
	}

	public abstract void action();
}

/**
 * 
 * @author sly
 * @time 2019年6月6日
 */
public class Controller {
	private List<Event> eventList = new ArrayList<>();
	public void addEvent(Event event) {
		eventList.add(event);
	}
	
	public void run() {
		while(eventList.size() > 0) {
			for (Event event : new ArrayList<>(eventList)) {
				if(event.ready()) {
					System.out.println(event);
					event.action();
					eventList.remove(event);
				}
			}
		}
	}
}
```

> 在目前的设计中你并不知道Event到底做了什么，这正是此设计的关键所在，“使变化的事物与不变的事物相互分离”。

> 这正是内部类要做的事情，内部类允许
> * 1.控制框架的完整实现是由单个类创建的，从而使得实现细节被封装起来。内部类用来表示解决问题所必须的各种不同的action()。
> * 2.内部类能够很容易的访问外围类的任意成员，所以可以避免这种实现变得笨拙。如果没有这种能力，代码将变得令人讨厌，以至于你肯定会选择别的方法。

> 例子太长请看代码：com.sly.demo.thinkinjava.chapter10.event

## 10.9 内部类的继承
> 因为内部类的构造器必须连接到指向其外围类对象的引用，所以继承内部类时，事件有点复杂。问题在于那个指向外围类对象的“秘密的”引用必须初始化，而在导出类中不再存在可连接的默认对象。为解决这个问题必须使用特殊的语法来说明它们之间的关联。

```java
class WithInner {
	class Inner {

	}
}

public class InheritInner extends WithInner.Inner {
	InheritInner(WithInner wi) {
		wi.super();
	}
	
	public static void main(String[] args) {
		WithInner inner = new WithInner();
		InheritInner inheritInner = new InheritInner(inner);
	}
}
```

> 可以看到,InheritInner只继承自内部类，而不是外围类。但是当要生成一个构造器时，默认构造器并不算好，而且不能只是传递一个指向外围类对象的引用。必须在构造器内使用如下语法：
> enclosingClassReference.super();

## 10.10 内部类可以被覆盖吗

```java
class Egg {
	private Yolk y;

	protected class Yolk {
		public Yolk() {
			System.out.println("Egg.Yolk()");
		}
	}

	public Egg() {
		System.out.println("New Egg()");
		y = new Yolk();
	}
}

public class BigEgg extends Egg{
	public class Yolk{
		public Yolk() {
			System.out.println("BigEgg.Yolk()");
		}
	}
	
	public static void main(String[] args) {
		new BigEgg();
	}
}
```

> 当继承了某个外围类时，内部类并没有发生什么特别神奇的变化。这两个内部类是两个独立的实体，各自在自己的命名空间内。当然明确的继承某个内部类也是可以的：

```java
class Egg2 {
	protected class Yolk {

		public Yolk() {
			System.out.println("Egg2.Yolk()");
		}

		public void f() {
			System.out.println("Egg2.f()");
		}
	}

	private Yolk yolk = new Yolk();

	public Egg2() {
		System.out.println("New Egg2()");
	}

	public void insertYolk(Yolk yolk) {
		this.yolk = yolk;
	}

	public void g() {
		yolk.f();
	}
}

public class BigEgg2 extends Egg2 {
	public class Yolk extends Egg2.Yolk {
		public Yolk() {
			System.out.println("BigEgg2.Yolk()");
		}

		public void f() {
			System.out.println("BigEgg2.f()");
		}
	}

	public BigEgg2() {
		insertYolk(new Yolk());
	}

	public static void main(String[] args) {
		BigEgg2 bigEgg2 = new BigEgg2();
		bigEgg2.g();
	}
}
```

## 10.11 局部内部类
> 可以再代码块里创建内部类，典型方式是在一个方法体的里面创建。局部内部类不能有访问说明符，因为他不是外围类的一部分；但是它可以访问当前代码块内的常量，以及此外围类的所有成员。

> 使用局部内部类而不使用匿名内部类唯一的理由是我们需要一个已命名的构造器，或者需要重载构造器。而匿名内部类只能用于实例的初始化。所以使用局部内部类而不使用匿名内部类的另一个理由就是，需要不止一个该内部类对象。

## 10.12 内部类标识符
> 每个内都会产生一个.class文件，其中包含了如何创建该类型的对象的全部信息（此信息产生一个“meta-class”，叫做Class对象）。内部类也必须生成一个.class文件以包含他们的Class对象信息。这些文件的命名有严格的规则：外围内名称 + $ + 内部类名称。如果内部类是匿名的，编译器会简单地产生一个数字作为其标识符。如果内部类嵌套在其它内部类中，只需直接将他们的名字加在其外围类标识符与$后面。

![命名](../../image/chapter10/10-001.png)

## 10.13 总结
> 还需要时间去理解。
