### 方法引用，将方法作为参数
File::isHidden

调用 File类的isHidden() 方法

### 从方法引用 到 lambda表达式（匿名函数）


### 灵活的API - 将方法作为参数
通过默认


### Stream
新的Stream API和Java现有的集合API的行为差不多：它们都能够访问数据项目的序列。     
不过，Collection主要是为了存储和访问数据，而Stream则主要用于描述对数据的计算

```java
List<Apple> heavyApples = inventory.stream().filter((Apple a) -> a.getWeight() > 150).collect(toList());
```

### 默认方法
改变已发布的接口而不破坏已有的实现

Java 8在接口声明中使用新的default关键字 来表示 接口可以包含 实现类没有提供实现 的方法签名

## 2.5 小结
#### 行为参数化
一个方法接受多个不同的行为作为参数，并在内部使用它们，完成不同行为的能力 ， 

将接口作为参数，传递接口的不同实现类

# 第三章 Lambda表达式
Lambda表达式可以看作匿名功能，它基本上就是没有声明名称的方法，  
但和匿名类一样，它也可以作为参数传递给一个方法，简洁地表示一个行为或传递代码。

Lambda表达式可以理解为简洁地表示可传递的匿名函数的一种方式

它没有名称，但它有参数列表、函数主体、返回类型，可能还有一个可以抛出的异常列表

函数主体为表达式（没有花括号）时，默认携带了 return

#### Lambda demo
```java
(String s) -> s.length()

(Apple a) -> a.getWeight() > 150

(int x, int y) -> {
    System.out.println("Result:");
    System.out.println(x+y);
}

() -> 42

(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())
```


## 3.2 在哪里以及如何使用Lambda
1. Lambda表达式可以被赋给一个变量

2. 可以在函数式接口上使用Lambda表达式, 作为函数式接口一个具体实现的实例

### 3.2.1 函数式接口  @FunctionalInterface
接口现在还可以拥有默认方法（即在类没有对方法进行实现时，其主体为方法提供默认实现的方法）。
哪怕有很多默认方法，==只要接口只定义了一个抽象方法==，它就仍然是一个函数式接口。

```java
class test {
  public static void process(Runnable r){
    r.run();
  }
public static void main(String[] args){
  Runnable r1 = () -> System.out.println("Hello World 1");
  Runnable r2 = new Runnable(){
      public void run(){
      System.out.println("Hello World 2");
      }
  };
  process(r1);
  process(r2);
  process(() -> System.out.println("Hello World 3"));
}
}
```


## 3.3 把Lambda 付诸实践：环绕执行模式

3.3.1 第1 步：记得行为参数化

3.3.2 第2 步：使用函数式接口来传递行为

3.3.3 第3 步：执行一个行为

3.3.4 第4 步：传递Lambda









