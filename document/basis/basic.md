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
   
      参数 | 抽象类 | 接口 
      :-: | :-: | :-: | 
      默认的方法实现 | 它可以有默认的方法实现 | 接口完全是抽象的。它根本不存在方法的实现 | 
      实现 | 子类使用extends关键字来继承抽象类。如果子类不是抽象类的话，它需要提供抽象类中所有声明的方法的实现。| 子类使用关键字implements来实现接口。它需要提供接口中所有声明的方法的实现 | 
      构造器 | 抽象类可以有构造器 | 接口不能有构造器 | 
      与正常Java类的区别 | 除了你不能实例化抽象类之外，它和普通Java类没有任何区别 | 接口是完全不同的类型 | 
      访问修饰符 | 可以有public、protected和default | 默认修饰符是public。你不可以使用其它修饰符 | 
      main方法 | 抽象方法可以有main方法并且我们可以运行它 | 接口没有main方法，因此我们不能运行它 | 
      多继承 | 抽象方法可以继承一个类和实现多个接口 | 接口只可以继承一个或多个其它接口 | 
      速度 | 它比接口速度要快 | 接口是稍微有点慢的，因为它需要时间去寻找在类中实现的方法 | 
      添加新方法 | 如果你往抽象类中添加新的方法，你可以给它提供默认的实现。因此你不需要改变你现在的代码 | 如果你往接口中添加方法，那么你必须改变实现该接口的类 | 
      
      
* 说说反射的用途及实现
   * 程序中一般的对象类型都是在编译期就确定下来的，而反射可以动态的创建对象并调用其属性，这样对象的类型在编译期是未知的。所以我们可以通过反射机制直接创建对象即使这个对象在编译期是未知的。
   * 用途
      1. 当我们在使用 IDE（如 Eclipse\IDEA）时，当我们一按 (“.”)点号，编译器就会自动列出它的属性或方法，这里就会用到反射。
      2. 很多框架（比如 Spring）都是配置化的（比如通过 XML文件配置 JavaBean，Action之类的），为了保证框架的通用性，他们可能根据配置文件加载不同的对象或类，调用不同的方法，这个时候就必须用到反射——运行时动态加载需要加载的对象。
      3. JDBC 的 classForName()
      4. 判断是否为某个类的实例
   * 实现
      1. getClass
      2. forName
      3. .class
* 说说自定义注解的场景及实现
   * 基本概念
      1. Java文件叫做Annotation，用@interface表示
      2. 元注解：@interface上面按需要注解上一些东西，JDK自带的注解，注解到我们自定义的注解上
         * @Retention  
         表示需要在什么级别保存该注释信息，用于描述注解的生命周期,也是一个枚举RetentionPoicy来决定
         * @Target  
         用于描述注解的使用范围,有一个枚举ElementType来指定
         * @Document  
         如果用javadoc生成文档时，想把注解也生成文档，就带这个
         * @Inherited  
         如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类
      3. 注解的保留策略：
         * @Retention(RetentionPolicy.SOURCE)   // 注解仅存在于源码中，在class字节码文件中不包含
         * @Retention(RetentionPolicy.CLASS)    // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得
         * @Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
      4. 注解的作用目标：
         * @Target(ElementType.TYPE)             // 接口、类、枚举、注解
         * @Target(ElementType.FIELD)            // 字段、枚举的常量
         * @Target(ElementType.METHOD)           // 方法
         * @Target(ElementType.PARAMETER)        // 方法参数
         * @Target(ElementType.CONSTRUCTOR)      // 构造函数
         * @Target(ElementType.LOCAL_VARIABLE)   // 局部变量
         * @Target(ElementType.ANNOTATION_TYPE)  // 注解
         * @Target(ElementType.PACKAGE)          // 包
      5. 注解包含在javadoc中：  
         @Documented
      6. 注解可以被继承：  
         @Inherited
      7. 使用场景
         * spring中的各种注解，@Controller @Service
         
* HTTP请求的GET与POST方式的区别
   * GET和POST是HTTP请求的两种基本方法，底层均为TCP/IP
   * 区别
      1. GET 请求可被缓存
      2. GET 请求保留在浏览器历史记录中
      3. GET 请求可被收藏为书签
      4. GET 请求不应在处理敏感数据时使用
      5. GET 请求有长度限制
      6. GET 请求只应当用于取回数据
      
* Session与Cookie区别
   * session  
   　　session机制是一种服务器端的机制，服务器使用一种类似于散列表的结构（也可能就是使用散列表）来保存信息。  
   　　程序需要为某个客户端的请求创建一个session时，服务器首先检查这个客户端的请求里是否已包含了一个session标识（称为session id），如果已包含则说明以前已经为此客户端创建过session，
   服务器就按照session id把这个session检索出来使用（检索不到，会新建一个），如果客户端请求不包含session id，则为此客户端创建一个session并且生成一个与此session相关联的session id，
   session id的值应该是一个既不会重复，又不容易被找到规律以仿造的字符串，这个session id将被在本次响应中返回给客户端保存。保存这个session id的方式可以采用cookie，这样在交互过程中浏览器可以自动的按照规则把这个标识发送给服务器。
   一般这个cookie的名字都是类似于SEEESIONID。但cookie可以被人为的禁止，则必须有其他机制以便在cookie被禁止时仍然能够把session id传递回服务器。
   　　经常被使用的一种技术叫做URL重写，就是把session id直接附加在URL路径的后面。还有一种技术叫做表单隐藏字段。就是服务器会自动修改表单，添加一个隐藏字段，以便在表单提交时能够把session id传递回服务器。
   * cookie  
   　　正统的cookie分发是通过扩展HTTP协议来实现的，服务器通过在HTTP的响应头中加上一行特殊的指示以提示浏览器按照指示生成相应的cookie。
   然而纯粹的客户端脚本如JavaScript或者VBScript也可以生成cookie。而cookie的使用是由浏览器按照一定的原则在后台自动发送给服务器的。
   浏览器检查所有存储的cookie，如果某个cookie所声明的作用范围大于等于将要请求的资源所在的位置，则把该cookie附在请求资源的HTTP请求头上发送给服务器。  
   　　cookie的内容主要包括：名字，值，过期时间，路径和域。路径与域一起构成cookie的作用范围。
   若不设置过期时间，则表示这个cookie的生命期为浏览器会话期间，关闭浏览器窗口，cookie就消失。
   这种生命期为浏览器会话期的cookie被称为会话cookie。会话cookie一般不存储在硬盘上而是保存在内存里，当然这种行为并不是规范规定的。
   若设置了过期时间，浏览器就会把cookie保存到硬盘上，关闭后再次打开浏览器，这些cookie仍然有效直到超过设定的过期时间。
   存储在硬盘上的cookie可以在不同的浏览器进程间共享，比如两个IE窗口。而对于保存在内存里的cookie，不同的浏览器有不同的处理方式
   * 区别  
   1. cookie数据存放在客户的浏览器上，session数据放在服务器上。
   2. cookie不是很安全，别人可以分析存放在本地的COOKIE并进行COOKIE欺骗
      考虑到安全应当使用session。
   3. session会在一定时间内保存在服务器上。当访问增多，会比较占用你服务器的性能
      考虑到减轻服务器性能方面，应当使用COOKIE。
   4. 单个cookie保存的数据不能超过4K，很多浏览器都限制一个站点最多保存20个cookie。
   
* 列出自己常用的JDK包
   1. java.lang
   2. java.util
   3. java.io
   4. java.time
   5. java.text
* MVC设计思想
* equals与==的区别
* hashCode和equals方法的区别与联系
* 什么是Java序列化和反序列化，如何实现Java序列化？或者请解释Serializable 接口的作用
* Object类中常见的方法，为什么wait  notify会放在Object里边？
* Java的平台无关性如何体现出来的
* JDK和JRE的区别
* Java 8有哪些新特性
