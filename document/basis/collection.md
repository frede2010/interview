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
    * http://www.importnew.com/28263.html
    
* **多线程情况下HashMap死循环的问题**
   * https://www.cnblogs.com/dongguacai/p/5599100.html
   
* **HashMap出现Hash DOS攻击的问题**
* **ConcurrentHashMap 的工作原理及代码实现，如何统计所有的元素个数**
* **手写简单的HashMap**
* **看过那些Java集合类的源码**
 