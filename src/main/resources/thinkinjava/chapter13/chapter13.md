# 第十三章 字符串
> 可以证明，字符串操作是计算机程序设计中最常见的行为。

## 13.1 不可变String
> String对象是不可变的，所有修改String的操作实际上都是创建了一个新的String。

## 13.2 重载“+”与StringBuilder
> String对象不可变，你可以给它任意多的别名，因为String只读特性，任何引用都不可能改变它的值，因此也不会对其它引用产生影响。

## 13.3 无意识递归
> Java中的每个类从根本上都是继承自Object，标准容器类也不例外。因此容器类都有toString()方法，并且覆写了该方法，使得它生成的String能够表达自身，以及容器所包含的对象。

> 在toString()方法里使用打印this会导致递归调用从而报出异常。这时如果需要获取对象内存地址需要使用Object的toString()方法。

```java
	public String toString(){
		return "toString" + this;
	}
```

## 13.4 String上的操作
> 即String类型的方法（这里不一一列出了）。

## 13.5 格式化输出

### 13.5.1 System.out.printf()
> System.out.printf("Row [%d %.1f]\n", x, y);

### 13.5.2 System.out.format()
> System.out.format("Row [%d %.1f]\n", x, y);

```java
	public static void main(String[] args) {
		int x = 1;
		double y = 1.5;
		System.out.println("Row [" + x + " " + y + "]");
		System.out.format("Row [%d %.1f]\n", x, y);
		System.out.printf("Row [%d %.1f]\n", x, y);
	}
	
	Row [1 1.5]
	Row [1 1.5]
	Row [1 1.5]
```

### 13.5.3 Formatter类

```java
	public static void main(String[] args) {
		int x = 1;
		double y = 1.5;
		Formatter formatter = new Formatter(System.out);
		formatter.format("Row [%d %.1f]\n",  x, y);
		formatter.close();
	}
	
	Row [1 1.5]
```

### 13.5.4 格式化说明符
> 在插入数据时，如果想要控制空格与对齐，你需要更精细复杂的格式修饰符。以下是其抽象语法：

> %\[argument_index$]\[flags]\[width]\[.precision]conversion

> 最常见的是控制一个域的最小尺寸，可以通过制定width来实现。Formatter对象通过在必要时添加空格来确保一个域至少达到某个长度。默认情况下数据是右对齐，可以通过使用“-”标志来改变对齐方向。与width相对的是precision，它是指明最大长度。width可以应用于各种类型的数据转换，并且其行为方式都一样。precision则不然，不是所有类型都可以使用precision，并且应用于不同数据类型时precision意义也不同（String时表示输出字符最大数量，浮点时表示小数位显示数量）。

### 13.5.5 Formatter转换

 |    		 |     	|  		|  		|
 | :-: 		 | :-:	 | :-: 	| :-:  	|
 | d | 整数型（十进制） | e | 浮点数（科学计数） |
 | c | Unicode字符 | x | 整数（十六进制） |
 | b | Boolean值 | h | 散列码（十六进制） |
 | s | String | % | 字符（%） |
 | f | 浮点数（十进制） |  |  |

## 13.6 正则表达式
> 

## 13.7 扫描输入
> 

## 13.8 StringTokenizer
> 

## 13.9 总结
> 