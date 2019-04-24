# 第二章 一切都是对象
> Java和C++是杂合型语言，但是java在面向对象上更纯粹一些。Java中几乎（基本类型不是对象）一切都是对象。

## 2.1 用引用操纵对象
> 操纵的标识符是对象的一个引用。为了安全，Java中最好在创建引用时将其初始化。

## 2.2 必须由你创建所有的对象

## 2.3 永远不需要销毁对象
> Java通过垃圾回收机制回收那些失去引用的对象，这样你就不用操心对象的回收了。有效的避免了内存泄漏问题。

## 2.4 创建新的数据类型：类
### 基本成员的默认值
> 只有作为成员变量时才会有默认值。

## 2.5 方法、参数和返回值
> Java的方法只能作为类的一部分来创建。方法只能通过对象调用，且这个对象必须能执行这个方法的调用（static方法是针对类的调用，并不依赖对象的存在）。

## 2.6 构建一个Java程序
> Java通过分包使得每一个类都是独一无二的。

## 2.7 你的第一个Java程序
> 略

## 2.8 注释和嵌入式文档
### 目的
> 为了方便维护文档将其与代码合二为一，swagger就是这种思想，但是swagger不能自定义一个参数结构，还有待加强。

### 范围
> Java只有public和protected才能注释，其它的即时注释了也不会再文档中展示。

### 嵌入式HTML
> Javadoc可以通过生成HTML文档传递HTML命令
> 不能加标题标签，比如：<hr>、<h1>等。因为Javadoc会自己生成。

### 个人感想
> 注释是非常重要的，但是总是不被重视，包括我自己很多人其实是不清真注释所有的用法的。

## 2.9 编码风格
> 这个我知道就不写了。

## 2.10 总结
