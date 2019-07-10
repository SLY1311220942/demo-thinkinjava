# 第十二章 通过异常处理错误
> Java的基本理念是“结构不佳的代码不能运行”。

## 12.1 概念
> “异常”这个词有“我对此感到意外”的意思。问题出现，你也许不知道如何处理，但是你知道不能置之不理。你要看是不是有别人或者在别的地方能处理这个问题。现在只是在当前环境没有足够的信息处理这个问题，所以把这个问题提交到一个更高级别的环境中，在这里将作出正确的决定。

> 使用异常带来的另一个明显的好处是可以简化错误处理代码的复杂度。

## 12.2 基本异常
> 异常情形是指阻止当前方法或作用域继续执行的问题。例如除数为0。

> 异常最重要的方面之一就是如果发生问题，它们将不允许程序沿着其正常的路径继续走下去。

> 异常允许我们强制程序停止运行，并告诉我们出了什么问题，或者强制程序处理问题，并返回稳定状态。

### 12.2.1 异常参数
> 与Java其它对象一样，我们总是用new在堆上创建异常对象，这也伴随着存储空间的分配和构造器的调用。所有标准异常都有两个构造器：一个是默认构造器；另一个是接受字符串作为参数，以便能把相关信息放入异常对象的构造器。

> throw new NullPointException("t = null")

> 在使用new创建了异常对象之后，此对象将传给throw。尽管返回的异常对象类型与方法设计的返回类型不一致，但从效果看，他就想是从方法“返回”的。抛出异常与方法正常返回值的相似之处到此为止，因为异常返回的“地点”和方法正常返回的“地点”完全不同。

> Throwable都可以抛。

## 12.3 捕获异常
> 监控区域：他是一段可能产生异常的代码，并且后面跟着处理这些异常的代码。

### 12.3.1 try块
> 如果方法内部抛出了异常，那么方法将在抛出异常的过程中结束。要是不希望方法结束，可以在方法内部设置一个特殊块来捕获异常（关键字try）：

```java
try {  
	// code that might generate exception  
}
```

### 12.3.2 异常处理程序
> 抛出的异常必须在某处得到处理，这个地点就是异常处理程序，针对每个要处理的异常，得准备相应的处理程序（关键字catch）。

```java
try {  
	// code that might generate exception  
} catch(Type1 id1) {
	// handle exception of type1
} catch(Type2 id2) {
	// handle exception of type2
} catch(Type3 id3) {
	// handle exception of type3
}
```

> 当异常被抛出时，异常处理机制近负责搜寻参数与异常类型相匹配的第一个处理程序。

### 12.3.3 终止与恢复
> 异常处理有两种模型：终止模型和恢复模型。

> java支持终止模型，这种模型中将假设错误非常关键，一旦异常被抛出，就表明错误无法挽回，也不能回来继续执行。

> 恢复模型意味着异常处理程序的作用是修正错误，然后重新调用出问题的方法，并认为第二次可以成功。Java如果要实现类似恢复的行为就不能抛出异常，而是调用方法来修正错误。

> 如果不是必须没有必要使用恢复模型，因为会导致代码的耦合带来维护的困难。

## 12.4 创建自定义异常
> 要自定义异常必须继承已有的异常类，最好是选择含义相近的已有异常。继承会创建默认构造器。

### 12.4.1 异常与日志记录
> 通常记录异常抛出处的栈轨迹。

## 12.5 异常说明
> Java鼓励人们把方法可能抛出的异常告知使用此方法的客户端程序员。

> 异常说明使用了附加关键字throws，后面接一个所有潜在异常类型的列表。

```java
void function1() throws TooBig, TooSmall, DivZero{
	
}
```

## 12.6 捕获所有异常
> 可以通过捕获基类Exception来做到这一点。

### 12.6.1 栈轨迹
> 可以通过getStackTrace()来直接访问，这个方法将返回一个由栈轨迹中的元素所构成的数组，其中每一个元素都表示栈中的一桢。

### 12.6.2 重新抛出异常
> 有时候希望重新将刚捕获的异常抛出，尤其是使用Exception时。

```java
catch(Exception e){
	throw e;
}
```

> 捕获异常后再抛出异常，有关原来异常发生点的信息会丢失，剩下的是与新异常抛出点有关的信息。

### 12.6.3 异常链
> 捕获一个异常后抛出另一个异常，并且希望将原始异常信息保存下来，这被称为异常链。

> Throwable的子类构造器可以接收一个cause对象作为参数，这个cause用来表示原始异常。这样把原始异常传递给新异常，即使在当前位置重新抛出了新异常，也能通过这个异常链追踪到异常最初发生的位置。

```java
try {
	g();
} catch (Exception e) {
	throw new RuntimeException(e);
}
```

## 12.7 Java标准异常
> Throwable这个Java类被用来表示任何可以作为异常被抛出的类。Throwable对象可以分为两种类型：Error用来表示编译时和系统错误；Exception是可以被抛出的基本类型。

### 12.7.1 特例：RuntimeException
> 属于运行时异常的类型有很多，他们会自动被Java虚拟机抛出，所以不必在异常说明中把他们列出来。这些异常都继承自RuntimeException，不需要在异常说明中声明方法将抛出RuntimeException异常（或是继承自RuntimeException的异常），他们被称为“不受检查异常”，在代码中还是可以抛出RuntimeException类型异常。

> 只能在代码中忽略RuntimeException类型的异常，其它类型的异常的处理是编译器强制实施的。究其原因，RuntimeException代表的是编程错误：
* 1.无法预料的错误（在你控制之外传递的null引用）。 
* 2.作为程序员，应该在代码中进行检查的错误（比如数组操作，得注意数组的大小）。在一个地方发送异常，常常导致在另一个地方导致错误。

## 12.8 使用finally进行清理
> 对于有些代码我们希望不管try块是否抛出异常，他们都能得到执行。这时可以在异常处理程序后面加上finally子句。

```java
try {  
	// code that might generate exception  
} catch(Type1 id1) {
	// handle exception of type1
} catch(Type2 id2) {
	// handle exception of type2
} catch(Type3 id3) {
	// handle exception of type3
} finally {
	// activities that happen every time
}
```

### 12.8.1 finally用来做什么
> 对于没有垃圾回收和析构函数自动调用机制的语言来说，finally非常重要。但是java几乎不用关系垃圾回收，也没有析构函数可以调用，那么什么时候需要用的finally呢？当要把内存之外的资源恢复到它们初始状态时，就要用到finally子句。这种需要清理的资源包括：以打开的文件或网络连接，在屏幕上画的图像，甚至可以是外部世界的某个开关。

### 12.8.2 在return中使用finally
> 因为finally总会执行，所有在一个方法中，可以从多个点返回，并且可以保证重要的清理工作仍旧会执行。

### 12.8.2 缺憾：异常丢失
> 遗憾的是，Java的异常实现也有瑕疵。异常作为程序出错的标志，绝不应该被忽略，但它还是可能被轻易的忽略。用某些特殊的方式使用finally子句，就会发生这种情况。

```java
public class LostMessage {
	void f() throws VeryImportantException {
		throw new VeryImportantException();
	}
	
	void dispose() throws HoHumException {
		throw new HoHumException();
	}
	
	
	public static void main(String[] args) {
		try {
			LostMessage lostMessage = new LostMessage();
			try {
				lostMessage.f();
			} finally {
				lostMessage.dispose();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
```

> 输出中可以看到VeryImportantException不见了，它被finally中的HoHumException取代了。这是相当严重的缺陷，因为异常可能会以比例子更微妙和难以察觉的方式完全丢失。

## 12.9 异常的限制
> 当覆盖方法时，只能抛出在基类方法的异常说明里列出的那些异常。这个限制的作用是：当基类使用的代码应用到派生类对象的时候，一样能工作，异常也不例外。

```java
public class ParentClass {
	public void f() throws OneException {
		throw new OneException();
	}
}

public class ChildClass extends ParentClass{
	@Override
	public void f() throws TwoException  {
		throw new TwoException();
	}
}
```

> ChildClass编译会报错提示：Exception TwoException is not compatible with throws clause in ParentClass.f()。

> 但是子类重写方法可以抛出父类方法抛出异常的导出类

```java
@Override
public void f() throws ChildOneException  {
	throw new ChildOneException();
}
```

## 12.10 构造器
> “如果异常发生了，所有的东西能被正确的清理吗？”，尽管大多数情况下是非常安全的，但涉及构造器时，问题就出现了。构造器会把对象设置成安全的初始状态，但还会有别的动作，比如打开一个文件，这样的动作只有在对象使用完毕并且调用了特殊的清理方法之后才能得以清理。如果构造器内抛出了异常，这些清理行为也许就不能正常工作了。这意味着编写构造器时要格外小心。

> 即使使用finally也无法保证清理，因为如果构造器在其执行过程中半途而废，也许该对象的某些部分还没有被成功创建，而这些部分在finally子句中却是要被清理的。

> 对于构造阶段可能会抛出异常，并且要求清理的类，最安全的使用方式是使用嵌套try子句：

```java
public class CleanUp {
	public static void main(String[] args) {
		try {
			InputStream in = new FileInputStream("asdasda");
			try {
				System.out.println("operate");
			} finally {
				System.out.println("finally run");
				in.close();
			}
		} catch (Exception e) {
			System.out.println("InputStream construction failed");
		}
	}
}
```

## 12.11 异常匹配
> 抛出异常时，异常处理系统会按照代码的书写顺序找出“最近”的处理程序。找到处理程序之后，它就认为异常得到处理，然后就不再继续查找。查找的时候不要求抛出的异常与异常处理程序声明的异常完全匹配，派生类的对象也可以匹配其基类的处理程序。

## 12.12 其他可选方式
> 异常的初衷是为了方便处理错误。

> 只有在你知道如何处理时才捕获异常。

> 被检查的异常会强制你加上catch子句，但是此时你可能不知道如何处理（catch没有做处理），导致无意中吞食了异常。

> 异常的好来自于：
* 1.不在于编译器是否会强制程序员处理错误，而是要有一致的、使用异常来报告错误的模型。
* 2.不在于什么时候检查，而是一定要有类型检查。也就是说必须强制程序使用正确的类型，至于这种强制施加于编译时还是运行时，那倒没关系。

> 可以将“被检查的异常”转换为“不检查的异常”

```java
try {
	// 
} catch (TypeException e){
	throw new RuntimeException(e);
}
```

## 12.13 异常使用指南
> 应该在下列情况下使用异常:
* 1.在恰当的级别处理问题。（在知道该如何处理的情况下才捕获异常。）
* 2.解决问题并且重新调用产生异常的方法。
* 3.进行少许修补，然后绕过异常发生的地方继续执行。
* 4.用别的数据进行计算，以代替方法会返回的值。
* 5.把当前环境下能做的事尽量做完，然后把相同的异常抛到更高层。
* 6.把当前环境下能做的事尽量做完，然后把不同的异常抛到更高层。
* 7.终止程序。
* 8.进行简化。（如果你的异常模式使问题变得太复杂，那用起来会非常痛苦也很烦人。）
* 9.让类库和程序更安全。（这既是为调试做短期投资，也是在为程序的健壮性做长期投资。）

## 12.14 总结
> 异常最精髓的就是报告错误。

