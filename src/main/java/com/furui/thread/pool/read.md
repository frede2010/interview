<h1>ThreadPoolExecutor</h1>

Executors是java线程池的工厂类，通过它可以快速初始化一个符合业务需求的线程池，如Executors.newFixedThreadPool方法可以生成一个拥有固定线程数的线程池。  
其本质是通过不同的参数初始化一个ThreadPoolExecutor对象。
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