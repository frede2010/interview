# Java基础 
* 面向对象的特征：继承、封装和多态
   1. 封装
      * 封装给对象提供了隐藏内部特性和行为的能力，对象提供一些能被其他对象访问的方法来改
        变它内部的数据。
      * 在 Java 当中，有 4 种修饰符： public， private ， protected和 default。每一种修饰符
        给其他的位于同一个包或者不同包下面对象赋予了不同的访问权限。
        下面列出了使用封装的一些好处
        1. 通过隐藏对象的属性来保护对象内部的状态。
        2. 提高了代码的可用性和可维护性，因为对象的行为可以被单独的改变或者是扩展。
        3. 禁止对象之间的不良交互提高模块化
   2. 继承
      * 继承（extend）给对象提供了从基类获取字段和方法的能力。两个类若存在IS-A的关系就可以使用继承，继承提供了代码的重用行，也可以在不修改类的情况下给现存的类添加新特性
   3. 多态
      * 多态是编程语言给不同的底层数据类型做相同的接口展示的一种能力。
      * 多态的三个必要条件
         1. 继承
         2. 重写
         3. 父类引用指向子类对象
      ```
      public class Test {
          public static void main(String[] args) {
            show(new Cat());  // 以 Cat 对象调用 show 方法
            show(new Dog());  // 以 Dog 对象调用 show 方法
                      
            Animal a = new Cat();  // 向上转型  
            a.eat();               // 调用的是 Cat 的 eat
            Cat c = (Cat)a;        // 向下转型  
            c.work();        // 调用的是 Cat 的 work
        }  
                  
          public static void show(Animal a)  {
            a.eat();  
              // 类型判断
              if (a instanceof Cat)  {  // 猫做的事情 
                  Cat c = (Cat)a;  
                  c.work();  
              } else if (a instanceof Dog) { // 狗做的事情 
                  Dog c = (Dog)a;  
                  c.work();  
              }  
          }  
      }
       
      abstract class Animal {  
          abstract void eat();  
      }  
        
      class Cat extends Animal {  
          public void eat() {  
              System.out.println("吃鱼");  
          }  
          public void work() {  
              System.out.println("抓老鼠");  
          }  
      }  
        
      class Dog extends Animal {  
          public void eat() {  
              System.out.println("吃骨头");  
          }  
          public void work() {  
              System.out.println("看家");  
          }  
      }
      ```
* final, finally, finalize 的区别
   1. final
      * 修饰符（关键字）如果一个类被声明为final，意味着它不能再派生出新的子类，不能作为父类被继承。因此一个类不能既被声明为 abstract的，又被声明为final的。
      * 将变量或方法声明为final，可以保证它们在使用中不被改变。
         1. 被声明为final的变量必须在声明时给定初值，而在以后的引用中只能读取，不可修改。对final变量的访问在编译期间都会直接被替代为真实的值。
         2. 被声明为final的方法也同样只能使用，不能重写。
 
* Exception、Error、运行时异常与一般异常有何异同
   * Exception和Error都有一个共同的基类Throwable
      * Error层次结构描述了java运行时系统的内部错误和资源耗尽错误。大多数错误与代码编写者执行的操作无关，而表示代码运行时 JVM（Java 虚拟机）出现的问题。应用程序不应该抛出这种类型的对象。
      * Exceprion这个层次结构又分解为连个分支：
         1. RuntimeException：由程序错误导致的异常属于RuntimeException
         2. 另一个分支包含其他异常：程序本身没有没有问题，但由于像I/O错误这类异常导致的异常属于其他异常。
* 请写出5种常见到的runtime exception
   1. IndexOutOfBoundsException - (下标越界异常) 
   2. NullPointerException - (空指针异常) 
   3. NumberFormatException - （String转换为指定的数字类型异常） 
   4. ArithmeticException -（算术运算异常 如除数为0） 
   5. ArrayStoreException - （向数组中存放与声明类型不兼容对象异常） 
   6. SecurityException -（安全异常） 
* int 和 Integer 有什么区别，Integer的值缓存范围
   * int 是基本数据类型，Integer是其包装类
   * Integer整数类型在-128到127之间的时候有个缓存。
      * 当给一个Integer赋予一个int类型的时候会调用Integer的静态方法valueOf()，这个方法会先判断数值是不是在缓冲区之间，是就直接返回缓冲区对应的引用。否则new Integer。
* 包装类，装箱和拆箱
   * 八种基本数据类型并不支持面向对象编程，基本类型的数据不具备“对象”的特性——不携带属性、没有方法可调用。 沿用它们只是为了迎合人类根深蒂固的习惯，并的确能简单、有效地进行常规数据处理。这种借助于非面向对象技术的做法有时也会带来不便，比如引用类型数据均继承了 Object 类的特性，要转换为 String 类型（经常有这种需要）时只要简单调用 Object 类中定义的toString()即可，而基本数据类型转换为 String 类型则要麻烦得多。为解决此类问题 ，Java为每种基本数据类型分别设计了对应的类，称之为包装类(Wrapper Classes)
   * 由基本类型向对应的包装类转换称为装箱，例如把 int 包装成 Integer 类的对象
   * 包装类向对应的基本类型转换称为拆箱，例如把 Integer 类的对象重新简化为 int
* String、StringBuilder、StringBuffer
   * String
      1. String类是final类，也即意味着String类不能被继承，并且它的成员方法都默认为final方法.
      2. String类是通过char数组来保存字符串的。对String对象的任何改变都不影响到原对象，相关的任何change操作都会生成新的对象
      3. String.intern() String的intern()方法会查找在常量池中是否存在一份equal相等的字符串,如果有则返回该字符串的引用,如果没有则添加自己的字符串进入常量池， 到jdk1.7后会把对象的引用存进常量池。
      4. new String("str")会在编译期间存进常量池中一份对象，在运行期间存进堆中一份对象
      5. new String("str") + new String("str") 会在编译期间存进常量池1份str对象，运行期间存进3分对象，包括2份str和相加后的值
   * StringBuilder
      1. 是对String拼装的优化类，事实上，string+="hello"的操作事实上会自动被JVM优化成：
          ```
          StringBuilder str = new StringBuilder(string);
          str.append("hello");
          str.toString();
          ```   
      2. String 每次拼装都会生成新的对象，StringBuilder.append则只有一个对象。
   *  StringBuffer
      * 线程安全的StringBuilder
      
* 重载和重写的区别
   * 重载 Overloading
      * 方法重载是指同一个类中的多个方法具有相同的名字,但这些方法具有不同的参数列表,即参数的数量或参数类型不能完全相同。
         1. 返回值类型可以相同也可以不相同。不能以返回型别作为重载函数的区分标准
   * 重写 Overriding
      * 方法重写是存在子父类之间的,子类定义的方法与父类中的方法具有相同的方法名字,相同的参数表和相同的返回类型
         1. 子类中不能重写父类中的final方法；
         2. 子类中必须重写父类中的abstract方法。
         
* 抽象类和接口有什么区别
   * 抽象类 abstract class
      1. 抽象类是用来捕捉子类的通用特性的 。它不能被实例化，只能被用作子类的超类。抽象类是被用来创建继承层级里子类的模板。
   * 接口 interface
      1. 接口是抽象方法的集合，如果实现了这个接口，那么就必须确保使用这些方法。接口只是一种形式，接口自身不能做任何事情。
   * 对比  
      name | 价格 |  数量  
      
      
* 说说反射的用途及实现
* 说说自定义注解的场景及实现
* HTTP请求的GET与POST方式的区别
* Session与Cookie区别
* 列出自己常用的JDK包
* MVC设计思想
* equals与==的区别
* hashCode和equals方法的区别与联系
* 什么是Java序列化和反序列化，如何实现Java序列化？或者请解释Serializable 接口的作用
* Object类中常见的方法，为什么wait  notify会放在Object里边？
* Java的平台无关性如何体现出来的
* JDK和JRE的区别
* Java 8有哪些新特性
