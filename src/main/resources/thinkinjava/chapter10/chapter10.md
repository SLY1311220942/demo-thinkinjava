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
