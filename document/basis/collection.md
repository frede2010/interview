# Java常见集合
* **List 和 Set 区别**
   * 概念：Collection集合主要分为三大类
      1. 第一类是实现了List接口的类，如LinkedList、ArrayList、Vector；
      2. 另一类就是实现了Set接口的类，如LinkedHashSet、HashSet、TreeSet。这些类都是继承了AbstractCollection集合的类
      3. 最后一个则是实现了queue接口的类
   * 区别
      1. 实现了List接口的类和实现了Set接口的类的不同点在于，前者集合中可以出现重复的元素，而后者集合之中不允许出现重复的元素
      2. 实现List接口的类都可以添加的元素都是有序的，而Set集合并不保证添加元素的有序性
      3. List中提供索引的方式来添加元素和获取元素，而Set并不提供

* **Set和hashCode以及equals方法的联系**
   * Set集合不允许重复，所以在加入元素的时候会去做比较，这时候就涉及到hashCode和equals
   
* **List 和 Map 区别**
   1. List接口继承collection接口，Map是个顶级接口
   2. List是存储单列数据的集合，存储的数据是有序并且是可以重复的；Map存储双列数据的集合，通过键值对存储数据，存储的数据是无序的，Key值不能重复，value值可以重复。
   
* **Arraylist 与 LinkedList 区别**
   1. ArrayList是实现了基于动态数组的数据结构，而LinkedList是基于链表的数据结构
   2. 对于随机访问get和set，ArrayList要优于LinkedList，因为LinkedList要移动指针
   3. 对于添加和删除操作add和remove，一般大家都会说LinkedList要比ArrayList快，因为ArrayList要移动数据
   
* **ArrayList 与 Vector 区别**
  1. Vector的方法都是同步的(Synchronized),是线程安全的(thread-safe)，而ArrayList的方法不是，由于线程的同步必然要影响性能，因此,ArrayList的性能比Vector好
  2. 当Vector或ArrayList中的元素超过它的初始大小时,Vector会将它的容量翻倍,而ArrayList只增加50%的大小，这样,ArrayList就有利于节约内存空间
  
* **HashMap 和 Hashtable 的区别**
   1. Hashtable的方法是同步的,而HashMap的不是
   2. HashTable不允许key和value为nul
   
* **HashSet 和 HashMap 区别**
   1. HashSet实现了Map接口,HashSet实现了Set接口
   2. HashSet底层是HashMap实现的，当HashSet add一个元素的时候，其实是望map里面put了一个key，value则是其内部默认的一个Object。所以set不允许重复的值
   
* **HashMap 和 ConcurrentHashMap 的区别**
   * ConcurrentHashMap是一种线程安全的Map，它不同于HashTable的所有方法均加锁，而是通过“分段锁”或者CAS，线程安全的同时，提升了效率。
   * ConcurrentHashMap的数据结构已经接近HashMap，相对而言，ConcurrentHashMap只是增加了同步的操作来控制并发，从JDK1.7版本的ReentrantLock+Segment+HashEntry，到JDK1.8版本中synchronized+CAS+HashEntry+红黑树
   
* **HashMap 的工作原理及代码实现，什么时候用到红黑树**
   * HashMap 里面是一个数组，然后数组中每个元素是一个单向链表Entry，Entry 包含四个属性：key, value, hash 值和用于单向链表的 next。
   * 一些参数 
      1. capacity：当前数组容量，始终保持 2^n，可以扩容，扩容后数组大小为当前的 2 倍。
      2. loadFactor：负载因子，默认为 0.75。
      3. threshold：扩容的阈值，等于 capacity * loadFactor
   * put操作
      1. 当插入第一个元素的时候，需要先初始化数组大小
      2. 如果 key 为 null，感兴趣的可以往里看，最终会将这个 entry 放到 table[0] 中
      3. 求 key 的 hash 值，找到对应的数组下标
      4. 遍历一下对应下标处的链表，看是否有重复的 key 已经存在，如果有，直接覆盖，put 方法返回旧值就结束了
      5. 不存在重复的 key，将此 entry 添加到链表中（会判断是否需要扩容，需要的话先扩容）
   * 扩容 resize()
      * JDK1.7
         1. 当向容器添加元素的时候，会判断当前容器的元素个数，如果大于等于阈值---即当前数组的长度乘以加载因子的值的时候，就要自动扩容
         2. 使用一个容量更大的数组(旧值的2倍)来代替已有的容量小的数组，transfer()方法将原有Entry数组的元素拷贝到新的Entry数组里。
         ```
         void transfer(Entry[] newTable, boolean rehash) {
             //新table的容量
             int newCapacity = newTable.length;
             //遍历原table
             for (Entry<K,V> e : table) {
                 while(null != e) {
                     //保存下一次循环的 Entry<K,V>
                     Entry<K,V> next = e.next;
                     if (rehash) {
                         //通过e的key值计算e的hash值
                         e.hash = null == e.key ? 0 : hash(e.key);
                     }
                     //得到e在新table中的插入位置
                     int i = indexFor(e.hash, newCapacity);
                     //采用链头插入法将e插入i位置，最后得到的链表相对于原table正好是头尾相反的
                     e.next = newTable[i];
                     newTable[i] = e;
                     //下一次循环
                     e = next;
                 }
             }
         }
         ```
         3. 扩容操作在高并发下可能会造成死循环
      * JDK1.8
         1. 是普通节点，直接和1.7一样放置
         2. 红黑树，调用 split 修剪方法进行拆分放置
         3. 链表
            * 对链表节点进行遍历，判断是留下还是去新的链表
            * 利用了尾指针Tail，完成了尾部插入，不会造成逆序，所以也不会产生并发死锁的问题
            * 不需要重新再计算hash
    
* **多线程情况下HashMap死循环的问题**
   1. 多线程put操作后，get操作导致死循环
   2. 多线程put非null元素后，get操作得到null值
   3. 多线程put操作，导致元素丢失
   
* **HashMap出现Hash DOS攻击的问题**
   * 主要是构造大量hashcode相同的key的数据，然后请求服务器。服务器会在解析这个数据上花费大量时间（hash冲突，寻址），进而占满cpu
   
* **ConcurrentHashMap 的工作原理及代码实现，如何统计所有的元素个数**
   * JDK1.7 ReentrantLock + Segment + HashEntry
      * ConcurrentHashMap 由一个个 Segment(分段锁) 组成。换句话说，ConcurrentHashMap 是一个 Segment 数组，Segment 通过继承 ReentrantLock 来进行加锁，所以每次需要加锁的操作锁住的是一个 segment，这样只要保证每个 Segment 是线程安全的，也就实现了全局的线程安全
      * concurrencyLevel：并行级别（Segment 数根据此参数计算），默认是 16，也就是说 ConcurrentHashMap 有 16 个 Segments，所以理论上，这个时候，最多可以同时支持 16 个线程并发写，只要它们的操作分别分布在不同的 Segment 上。这个值可以在初始化的时候设置为其他值，但是一旦初始化以后，它是不可以扩容的。
      * Segment类似于HashMap，维护entry链表的数组
      * 初始化
         1. initialCapacity：初始容量，这个值指的是整个 ConcurrentHashMap 的初始容量，实际操作的时候需要平均分给每个 Segment；
         2. loadFactor：负载因子，Segment 数组不可以扩容，所以这个负载因子是给每个 Segment 内部使用的
      * 
   * JDK1.8 synchronized + CAS + HashEntry
   
* **看过那些Java集合类的源码**
 