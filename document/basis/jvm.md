# JVM
* **JVM运行时内存区域划分**
   * 5大区域：
      1. 虚拟机栈‘本地方法栈、程序计数器（线程隔离）
      2. 堆、方法区（线程共享）
   * 细节见com.furui.jvm
   
* **内存溢出OOM和堆栈溢出SOF的示例及原因、如何排查与解决**
   * 示例：com.furui.jvm.overflow
   * 内存溢出原因：
      1. 是否应用中的类中和引用变量过多使用了Static修饰 如public staitc Student s；在类中的属性中使用 static修饰的最好只用基本类型或字符串。如public static int i = 0; //public static String str;
      2. 是否 应用 中使用了大量的递归或无限递归（递归中用到了大量的建新的对象）
      3. 是否App中使用了大量循环或死循环（循环中用到了大量的新建的对象）
      4. 检查 应用 中是否使用了向数据库查询所有记录的方法。即一次性全部查询的方法，如果数据量超过10万多条了，就可能会造成内存溢出。所以在查询时应采用“分页查询”。
      5. 检查是否有数组，List，Map中存放的是对象的引用而不是对象，因为这些引用会让对应的对象不能被释放。会大量存储在内存中。
      6. 检查是否使用了“非字面量字符串进行+”的操作。因为String类的内容是不可变的，每次运行"+"就会产生新的对象，如果过多会造成新String对象过多，从而导致JVM没有及时回收而出现内存溢出。
   * 栈溢出的原因
      1. 是否有递归调用
      2. 是否有大量循环或死循环
      3. 全局变量是否过多
      4. 数组、List、map数据是否过大
      5. 使用DDMS工具进行查找大概出现栈溢出的位置
   * 方法区溢出原因
      * 基本上都是运行时产生大量的类填满了整个方法区，直到溢出
      * JDK1.7 java.lang.OutOfMemoryError: PermGen space
      * JDK1.8 java.lang.OutOfMemoryError: Metaspace
      
* **如何判断对象是否可以回收或存活**
   1. 引用计数算法
      * 给对象中添加一个引用计数器，每当有一个地方引用它时，计数器值就加1；当引用失效时，计数器值就减1；任何时刻计数器为0的对象就是不可能再被使用的。
      * JVM里面并没有选用引用计数算法来管理内存，主要原因是它很难解决对象之间相互循环引用的问题
   2. 可达性分析算法
      * 通过一系列的称为“GC Roots”的对象作为起始点，从这些节点开始向下搜索，搜索所走过的路径称为引用链(Reference Chain)，当一个对象到GC Roots没有任何引用链相连时，则证明此对象是不可用的
      * 可作为GC Roots的对象包括下面几种
         1. 虚拟机栈（栈帧中的本地变量表）中引用的对象
         2. 方法区中类静态属性引用的对象
         3. 方法区中常量引用的对象
         4. 本地方法栈中JNI（即一般说的Native方法）引用的对象
         
* **常见的GC回收算法及其含义**
  1. 标记-清除收集器 Mark-Sweep
     >    　　基于tracing算法的垃圾收集也称为标记和清除(mark-and-sweep)垃圾收集器.  
          　　这是最基础的垃圾回收算法，之所以说它是最基础的是因为它最容易实现，思想也是最简单的。标记-清除算法分为两个阶段：标记阶段和清除阶段。标记阶段的任务是标记出所有需要被回收的对象，清除阶段就是回收被标记的对象所占用的空间。  
          　　但是有一个比较严重的问题就是容易产生内存碎片，碎片太多可能会导致后续过程中需要为大对象分配空间时无法找到足够的空间而提前触发新的一次垃圾收集动作。
  2. 复制收集器 Copying　　
      >   　　为了解决Mark-Sweep算法的缺陷，Copying算法就被提了出来。它将可用内存按容量划分为大小相等的两块，每次只使用其中的一块。当这一块的内存用完了，就将还存活着的对象复制到另外一块上面，然后再把已使用的内存空间一次清理掉，这样一来就不容易出现内存碎片的问题。  
          　　这种算法虽然实现简单，运行高效且不容易产生内存碎片，但是却对内存空间的使用做出了高昂的代价，因为能够使用的内存缩减到原来的一半。很显然，Copying算法的效率跟存活对象的数目多少有很大的关系，如果存活对象很多，那么Copying算法的效率将会大大降低。
  3. 标记-压缩收集器 Mark-Compact
      >   为了解决Copying算法的缺陷，充分利用内存空间，提出了Mark-Compact算法。该算法标记阶段和Mark-Sweep一样，但是在完成标记之后，它不是直接清理可回收对象，而是将存活对象都向一端移动，然后清理掉端边界以外的内存。  
  4. 分代收集器 Generational
      >   　　分代收集算法是目前大部分JVM的垃圾收集器采用的算法。  
          　　它的核心思想是根据对象存活的生命周期将内存划分为若干个不同的区域，一般情况下将堆区划分为老年代（Tenured Generation）和新生代（Young Generation）。  
          　　老年代的特点是每次垃圾收集时只有少量对象需要被回收，而新生代的特点是每次垃圾回收时都有大量的对象需要被回收，那么就可以根据不同代的特点采取最适合的收集算法。  
          　　目前大部分垃圾收集器对于新生代都采取Copying算法，因为新生代中每次垃圾回收都要回收大部分对象，也就是说需要复制的操作次数较少，但是实际中并不是按照1：1的比例来划分新生代的空间的，一般来说是将新生代划分为一块较大的Eden空间和两块较小的Survivor空间，每次使用Eden空间和其中的一块Survivor空间，当进行回收时，将Eden和Survivor中还存活的对象复制到另一块Survivor空间中，然后清理掉Eden和刚才使用过的Survivor空间。  
          而由于老年代的特点是每次回收都只回收少量对象，一般使用的是Mark-Compact算法。  
          　　Eden区、Survivor from区、Survivor to区，实际上分为三个区的原因是为了方便采用复制-清除（详情请参考深入理解JVM中内存回收策略）策略而采用的策略，复制策略就是将原来存在的内存分为两个相等的区，使用一块进行新生代的内存分配，当要GC时，则将存活的对象复制进入另一块空闲的内存，然后将使用的内存进行清除，从而又有一个空闲区和一个使用区，并且不会有碎片问题。
          实际上并不需要两个1：1的分区比例，因为一般存活的对象很少，所以JVM聪明的讲新生代占据的总内存分为Eden：Survivor from：Survivor to = 8:1:1三部分，其中Eden就用来分配新的对象内存，Survivor from则用于GC时的复制，那为什么需要两个Survivor区呢，因为复制后Survivor from区虽然现在很整齐，没有碎片，当下一次进行回收时，Eden区和Survivor from区里都存在需要回收的对象，则Survivor from区也会出现碎片。
          
* **常见的JVM性能监控和故障处理工具类：jps、jstat、jmap、jinfo、jconsole等**

* **JVM如何设置参数**
* **JVM性能调优**
* **类加载器、双亲委派模型、一个类的生命周期、类是如何加载到JVM中的**
   * 类加载器 用来装载.class文件
      1. Bootstrap ClassLoader （启动类加载器）
      2. Extension ClassLoader （扩展类加载器）
      3. App ClassLoader （应用程序类加载器）
      4. Custom ClassLoader  （自定义加载器）
      
   * 双亲委派模型
      * 如果一个类加载器收到类加载的请求，它首先不会自己去尝试加载这个类，而是把这个请求委派给父类加载器完成。每个类加载器都是如此，只有当父加载器在自己的搜索范围内找不到指定的类时（即ClassNotFoundException），子加载器才会尝试自己去加载
* **类加载的过程：加载、验证、准备、解析、初始化**
   1. 装载
   2. 链接
   3. 初始化
   
* **强引用、软引用、弱引用、虚引用**
   * 强引用
      1. Object o = new Object();
      2. 强引用可以直接访问目标对象
      3. 引用所指向的对象在任何时候都不会被系统回收。JVM宁愿抛出OOM异常，也不会回收强引用所指向的对象
      4. 强引用可能导致内存泄漏，为了避免内存泄漏，在使用完成之后我们可以把字符串对象设置为null,如果是集合的话可以使用clear
   * 软引用
      1. 软引用的强度是仅次于强引用的，如果一个对象只具有软引用，则内存空间足够，垃圾回收器就不会回收它；如果内存空间不足了，就会回收这些对象的内存
      2.
       ```
       String str=new String("abc");                                     // 强引用  
       SoftReference<String> softRef=new SoftReference<String>(str);     // 软引用
       ```
   * 弱引用
      1. 弱引用的强度比软引用更次，也就是说只具有弱引用的对象拥有更短暂的生命周期。在垃圾回收器线程扫描它所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存
      ```
      WeakReference<String> abcWeakRef = new WeakReference<String>(str);  
      ```
   * 虚引用
      1. 一个对象是都有虚引用的存在都不会对生存时间都构成影响，也无法通过虚引用来获取对一个对象的真实引用。唯一的用处：能在对象被GC时收到系统通知
      2. JAVA中用PhantomReference来实现虚引用
   * 对比
      引用类型 | GC回收时间 | 用途 | 生存时间 
      :-: | :-: | :-: | :-: | 
      强引用 | never | 对象的一般状态 | JVM停止运行时 | 
      软引用 | 内存不足时 | 对象缓存 |  内存不足时终止 | 
      弱引用 | GC时 | 对象缓存 | GC后终止
      虚引用 | unknown | unknown | unknown
      
* **Java内存模型JMM**
   1. 类加载器
   2. 执行引擎
   3. 运行时数据区
