<h2>ThreadPoolExecutor</h2>
```
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          RejectedExecutionHandler handler)
```                          
* corePoolSize
线程池中的核心线程数，当提交一个任务时，线程池创建一个新线程执行任务，直到当前线程数等于corePoolSize；    
如果当前线程数为corePoolSize，继续提交的任务被保存到阻塞队列中，等待被执行；  
如果执行了线程池的prestartAllCoreThreads()方法，线程池会提前创建并启动所有核心线程。

* maximumPoolSize
线程池中允许的最大线程数。如果当前阻塞队列满了，且继续提交任务，则创建新的线程执行任务，前提是当前线程数小于maximumPoolSize；

* keepAliveTime
线程空闲时的存活时间，即当线程没有任务执行时，继续存活的时间；默认情况下，该参数只在线程数大于corePoolSize时才有用；

* unit
keepAliveTime的单位；

* workQueue
用来保存等待被执行的任务的阻塞队列，且任务必须实现Runable接口，在JDK中提供了如下阻塞队列：  
1、ArrayBlockingQueue：基于数组结构的有界阻塞队列，按FIFO排序任务；  
2、LinkedBlockingQuene：基于链表结构的阻塞队列，按FIFO排序任务，吞吐量通常要高于ArrayBlockingQuene；  
3、SynchronousQuene：一个不存储元素的阻塞队列，每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQuene；  
4、priorityBlockingQuene：具有优先级的无界阻塞队列  

* threadFactory
创建线程的工厂，通过自定义的线程工厂可以给每个新建的线程设置一个具有识别度的线程名。

* handler
线程池的饱和策略，当阻塞队列满了，且没有空闲的工作线程，如果继续提交任务，必须采取一种策略处理该任务，线程池提供了4种策略：  
1、AbortPolicy：直接抛出异常，默认策略；  
2、CallerRunsPolicy：用调用者所在的线程来执行任务  
3、DiscardOldestPolicy：丢弃阻塞队列中靠最前的任务，并执行当前任务；  
4、DiscardPolicy：直接丢弃任务；
当然也可以根据应用场景实现RejectedExecutionHandler接口，自定义饱和策略，如记录日志或持久化存储不能处理的任务

---
<h2>Executors</h2>
Executors是java线程池的工厂类，通过它可以快速初始化一个符合业务需求的线程池，如Executors.newFixedThreadPool方法可以生成一个拥有固定线程数的线程池。  
其本质是通过不同的参数初始化一个ThreadPoolExecutor对象。
1. CachedThreadPool:可缓存的线程池，该线程池中没有核心线程，非核心线程的数量为Integer.max_value，就是无限大，当有需要时创建线程来执行任务，没有需要时回收线程，适用于耗时少，任务量大的情况。  
2. SecudleThreadPool:周期性执行任务的线程池，按照某种特定的计划执行线程中的任务，有核心线程，但也有非核心线程，非核心线程的大小也为无限大。适用于执行周期性的任务。  
3. SingleThreadPool:只有一条线程来执行任务，适用于有顺序的任务的应用场景。  
4. FixedThreadPool:定长的线程池，有核心线程，核心线程的即为最大的线程数量，没有非核心线程  
> 线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。   
说明：Executors各个方法的弊端：  
1）newFixedThreadPool和newSingleThreadExecutor:  
  主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至OOM。  
2）newCachedThreadPool和newScheduledThreadPool:  
  主要问题是线程数最大数是Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至OOM。  