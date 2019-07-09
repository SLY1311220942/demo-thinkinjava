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

 |     |     |
 | :-: | :-: |
 |  d  | 整数型（十进制） |
 |  c  | Unicode字符 |
 |  b  | Boolean值 |
 |  s  | String |
 |  f  | 浮点数（十进制） |
 |  e  | 浮点数（科学计数） |
 |  x  | 整数（十六进制） |
 |  h  | 散列码（十六进制） |
 |  %  | 字符（%） |

### 13.5.6 String.format()
> 它接收和Formatter.format()方法一样的参数。

## 13.6 正则表达式
> 正则表达式是一种强大而灵活的文本处理工具。

### 13.6.1 基础
> 一般来说正则表达式就是以某种方式描述字符串：“如果一个字符串含有这些东西，那么它就是我正在找的东西。”

### 13.6.2 创建正则表达式

> 字符

 |        |     |
 | :-:    | :-: |
 | B      | 指定字符B  |
 | \xhh   | 十六进制值为0xhh的字符    |
 | \uhhhh | 十六进制表示为0xhhhh的Unicode字符   |
 | \t     | 制表符Tab    |
 | \n     | 换行符    |
 | \r     | 回车    |
 | \f     | 换页    |
 | \e     | 转义（Escape）    |

> 字符类

 |              |     |
 | :-:          | :-: |
 | .            | 任意字符 |
 | [abc]        | 包含abc的任何字符（和a|b|c作用相同） |
 | [^abc]       | 除了abc之外的任何字符（否定） |
 | [a-zA-Z]     | 从a到z或从A到Z的任何字符（范围） |
 | [abc[hij]]   | 任意a、b、c、h、i和j字符（与a|b|c|h|i|j作用相同）（合并） |
 | [a-z&&[hij]] | 任意h、i或j（交） |
 | \s           | 空白符（空格、tab、换行、换页和回车） |
 | \S           | 非空白符[^\s] |
 | \d           | 数字[0-9] |
 | \D           | 非数字[^0-9] |
 | \w           | 词字符[a-zA-Z0-9] |
 | \W           | 非词字符[^\w] |
 
> 逻辑操作符
 
 |     |     |
 | :-: | :-: |
 | XY  | Y跟在X后面 |
 | X|Y | X或Y |
 | (X) | 捕获组，可以在表达式中用\i引用第i个捕获组 |
 
> 边界匹配符
 |     |     |
 | :-: | :-: |
 | ^   | 一行的起始 |
 | $   | 一行的结束 |
 | \b  | 词的边界 |
 | \B  | 非词的边界 |
 | \G  | 前一个匹配的结束 |

### 13.6.3 量词

* 贪婪型：量词总是贪婪的除非有其它选项被设置。贪婪表达式会为所有可能的模式发现尽可能多的匹配
* 勉强型：用问号来指定，这个量词匹配满足模式所需的最少字符数。因此也不称为懒惰的、最少匹配的、非贪婪的或不贪婪的。
* 占有型：当正则表达式被应用于字符串时，它会产生相当多的状态，以便在匹配失败时可以回溯。

| 贪婪型    | 勉强型       | 占有型      | 如何匹配 |
| :-:    | :-:     | :-:     | :-:     |
| X?     | X??     | X?+     | 一个或零个X |
| X\*    | X\*?    | X*+     | 零个或多个X |
| X+     | X+?     | X++     | 一个或多个X |
| X{n}   | X{n}?   | X{n}+   | 恰好n次X |
| X{n,}  | X{n,}?  | X{n,}+  | 至少n次X |
| X{n,m} | X{n,m}? | X{n,m}+ | X至少n次,且不超过m次 |

### 13.6.3 Pattern和Matcher
> 一般来说比起使用功能有限的String类，我们更愿意构造功能强大的正则表达式对象。


## 13.7 扫描输入
> 

## 13.8 StringTokenizer
> 

## 13.9 总结
> 