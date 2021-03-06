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
```java
class Base {
	
}

class Drived extends Base {
	
}

public class InstanceofClass {
	static void test(Object x) {
		System.out.println("Test x of type:" + x.getClass());
		System.out.println("x instanceof Base:" + (x instanceof Base));
		System.out.println("x instanceof Drived:" + (x instanceof Drived));
		System.out.println("Base.isInstance(x):" + Base.class.isInstance(x));
		System.out.println("Drived.isInstance(x):" + Drived.class.isInstance(x));
		System.out.println("x.getClass() == Base.class:" + (x.getClass() == Base.class));
		System.out.println("x.getClass() == Drived.class:" + (x.getClass() == Drived.class));
		System.out.println("x.getClass().equals(Base.class):" + (x.getClass().equals(Base.class)));
		System.out.println("x.getClass().equals(Drived.class):" + (x.getClass().equals(Drived.class)));
	}
	
	public static void main(String[] args) {
		test(new Base());
		test(new Drived());
	}
}

result:
Test x of type:class com.sly.demo.thinkinjava.chapter14.instclass.Base
x instanceof Base:true
x instanceof Drived:false
Base.isInstance(x):true
Drived.isInstance(x):false
x.getClass() == Base.class:true
x.getClass() == Drived.class:false
x.getClass().equals(Base.class):true
x.getClass().equals(Drived.class):false
Test x of type:class com.sly.demo.thinkinjava.chapter14.instclass.Drived
x instanceof Base:true
x instanceof Drived:true
Base.isInstance(x):true
Drived.isInstance(x):true
x.getClass() == Base.class:false
x.getClass() == Drived.class:true
x.getClass().equals(Base.class):false
x.getClass().equals(Drived.class):true
```

> test()使用两种instanceof来执行类型检查，获取Class引用后用==和equals()来检查对象是否相等，生成的结果使人放心instanceof()和isInstance()结果相同，==和equals()也一样。但是instanceof保留了类型概念，但是==和equals就没有类型概念。

## 14.6 反射：运行时的类信息


### 14.6.1 类方法提取器
> 通常你不需要直接使用反射工具，但是它们在你需要创建更加动态的代码时会有用。

> Class的getMethods()和getContructors()方法分别返回Method对象的数组和Constructor对象的数组。

## 14.7 动态代理
> 代理是基本的设计模式之一，它是你为了提供额外的或不同的操作，而插入的用来代替“实际”对象的对象。这些操作通常涉及与“实际”对象通讯，因此代理通常充当中间人的角色。

> Java的动态代理比代理的思想更向前迈进了一步，因为它们可以动态的创建代理并动态的处理对所代理方法的调用。在动态代理上所做的所有调用都会被重定向到单一的调用处理器上，它的工作是揭示调用的类型并确定相应的对策。

> 通过调用静态方法Proxy.newProxyInstance()可以创建动态代理，这个方法需要得到一个类加载器（你通常可以从已经加载的对象获取其类加载器，然后传递给它），一个你希望该代理实现的接口列表（不是类或抽象类），以及InvocationHandler的一个实现。

```java
class DynamicProxyHandler implements InvocationHandler {
	private Object proxied;

	public DynamicProxyHandler(Object proxied) {
		this.proxied = proxied;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("**** proxy:" + proxy.getClass() + " method:" + method + " args:" + args);
		if(args != null) {
			for (Object arg : args) {
				System.out.println("   " + arg);
			}
		}
		return method.invoke(proxied, args);
	}

}

public class SimpleDynamicProxyDemo {
	public static void customer(Interface iface) {
		iface.doSomthing();
		iface.somthingElse("bonobo");
	}
	
	public static void main(String[] args) {
		RealObject real = new RealObject();
		customer(real);
		Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(), new Class[]{Interface.class}, new DynamicProxyHandler(real));
		customer(proxy);
	}
}

result:
doSomthing
somthingElse bonobo
**** proxy:class com.sly.demo.thinkinjava.chapter14.proxy.$Proxy0 method:public abstract void com.sly.demo.thinkinjava.chapter14.proxy.Interface.doSomthing() args:null
doSomthing
**** proxy:class com.sly.demo.thinkinjava.chapter14.proxy.$Proxy0 method:public abstract void com.sly.demo.thinkinjava.chapter14.proxy.Interface.somthingElse(java.lang.String) args:[Ljava.lang.Object;@6bc7c054
   bonobo
somthingElse bonobo
```

## 14.8 空对象
```java
public interface Null {

}

public class Person {
	public final String first;
	public final String last;
	public final String address;

	public Person(String first, String last, String address) {
		this.first = first;
		this.last = last;
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Person: " + first + " " + last + " " + address;
	}
	
	public static class NullPerson extends Person implements Null{

		private NullPerson() {
			super("None", "None", "None");
		}
		
		@Override
		public String toString() {
			return "NullPerson";
		}
	}
	
	public static final Person NULL = new NullPerson();
}

public class Position {
	private String title;
	private Person person;
	
	public Position(String jobTitle,Person employee) {
		this.title = jobTitle;
		this.person = employee;
		if(person == null) {
			this.person = Person.NULL;
		}
	}
	
	public Position(String jobTitle) {
		this.title = jobTitle;
		this.person = Person.NULL;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
		if(person == null) {
			this.person = Person.NULL;
		}
	}
	
	@Override
	public String toString() {
		return "Position: " + title + " " + person;
	}
	
}

public class Staff extends ArrayList<Position> {

	private static final long serialVersionUID = 1L;

	public void add(String title,Person person) {
		add(new Position(title, person));
	}
	
	public void add(String...titles) {
		for (String title : titles) {
			add(new Position(title));
		}
	}
	
	public Staff(String...titles) {
		add(titles);
	}
	
	public boolean positionAvailable(String title) {
		for (Position position : this) {
			if(position.getTitle().equals(title) && position.getPerson() == Person.NULL) {
				return true;
			}
		}
		return false;
	}
	
	public void fillPosition(String title,Person hire) {
		for (Position position : this) {
			if(position.getTitle().equals(title) && position.getPerson() == Person.NULL) {
				position.setPerson(hire);
				return ;
			}
		}
		throw new RuntimeException("Position:" + title + " not avaliable");
	}
	
	public static void main(String[] args) {
		Staff staff = new Staff("Persident","CTO","Marketing Manager","Product Manager","Project Lead",
				"Software Engineer","Software Engineer","Software Engineer","Software Engineer",
				"Test Engineer","Technical Writer");
		staff.fillPosition("Persident", new Person("Me","Last","The top lonely At"));
		staff.fillPosition("Project Lead", new Person("Janet","Planner","The Burbs"));
		if(staff.positionAvailable("Software Engineer")) {
			staff.fillPosition("Software Engineer", new Person("Bob","Coder","Bright Light City"));
		}
		System.out.println(staff);
	}
}

result:
[
	Position: Persident Person: Me Last The top lonely At, 
	Position: CTO NullPerson, 
	Position: Marketing Manager NullPerson, 
	Position: Product Manager NullPerson, 
	Position: Project Lead Person: Janet Planner The Burbs, 
	Position: Software Engineer Person: Bob Coder Bright Light City, 
	Position: Software Engineer NullPerson, 
	Position: Software Engineer NullPerson, 
	Position: Software Engineer NullPerson, 
	Position: Test Engineer NullPerson, 
	Position: Technical Writer NullPerson
]

```

我觉得上面这个结构可以用来做点什么，但是还没有想到比较好的应用场景。


下面是一种命令模式类型

```java
public interface Operation {
	String description();
	void command();
}

public interface Robot {
	String name();
	String model();
	List<Operation> Operations();
	
	class Test {
		public static void test(Robot r) {
			if(r instanceof Null) {
				System.out.println("[Null Robot]");
			}
			System.out.println("Robot name: " + r.name());
			System.out.println("Robot model: " + r.model());
			for (Operation operation : r.Operations()) {
				System.out.println(operation.description());
				operation.command();
			}
		}
	}
}

public class SnowRemoveRobot implements Robot{
	private String name;
	
	public SnowRemoveRobot(String name) {
		this.name = name;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public String model() {
		return "SnowRobot Series 11";
	}

	@Override
	public List<Operation> Operations() {
		
		return Arrays.asList(
			new Operation() {
				@Override
				public String description() {
					return name + " can shovel snow";
				}
				@Override
				public void command() {
					System.out.println(name + " shoveling snow");
				}
			},
			new Operation() {
				@Override
				public String description() {
					return name + " can chip ice";
				}
				@Override
				public void command() {
					System.out.println(name + " chipping ice");
				}
			},
			new Operation() {
				@Override
				public String description() {
					return name + " can clear the roof";
				}
				@Override
				public void command() {
					System.out.println(name + " clearing roof");
				}
			}
		);
	}
	
	public static void main(String[] args) {
		Robot.Test.test(new SnowRemoveRobot("Slusher"));
	}
}

result:
Robot name: Slusher
Robot model: SnowRobot Series 11
Slusher can shovel snow
Slusher shoveling snow
Slusher can chip ice
Slusher chipping ice
Slusher can clear the roof
Slusher clearing roof
```

### 14.8.1 模拟对象与桩
> 空对象的逻辑变体是模拟对象与桩。与空对象一样，它们都表示在最终的程序中所使用的“实际”对象。但是，模拟对象和桩都只是假扮可以传递实际信息的存活对象，而不是像空对象那样可以成为null的一种更加智能的替代物。

> 模拟对象和桩之间的差异在于程度不同。模拟对象往往是轻量级和自测级，通常很多模拟对象被创建出来是为了处理各种不同的测试情况。桩只是返回桩数据，它通常是重量级的，并且经常在测试之间被复用。桩可以根据它们被调用的方式，通过配置进行修改，因此桩是一种复杂对象，它要做很多事。

## 14.9 接口与类型信息
> interface关键字的一个重要的目标就是允许程序员隔离构件，进而降低耦合性。

> 对域来说没有任何方式可以阻止反射到达并调用那些非公共访问权限的方法，即便是private域。但是final域在遭遇修改时是安全的。运行时系统会在不抛出任何异常的情况下接受任何修改常识，但实际上不会发生任何修改（使用1.8版本压根就不让编译通过）。

## 14.10 总结

