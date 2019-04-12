# 进程和线程
* https://blog.csdn.net/u010870518/column/info/17790
* **线程和进程的概念、并行和并发的概念**
   * 进程（Process）是计算机中的程序关于某数据集合上的一次运行活动，是系统进行资源分配和调度的基本单位
   * 线程，有时被称为轻量级进程(Lightweight Process，LWP），是程序执行流的最小单元。线程是程序中一个单一的顺序控制流程。
   * 并行：多个线程可以同时执行，每一个时间段，可以有多个线程同时执行。
   * 并发：多个线程同时竞争一个位置，竞争到的才可以执行，每一个时间段只有一个线程在执行。
   
* **创建线程的方式及实现**
   1. 继承Thread，重写run()方法
   2. 实现Runable接口
   
* **进程间通信的方式**
   * 管道(pipe)、有名管道(named pipe)、信号量(semophore)、消息队列(message queue)、信号(signal)、共享内存(shared memory)、套接字(socket)；
   * Java的一种实现线程间通信的机制是：wait/notify线程间通信
      * wait方法
         1. 方法wait()的作用是使当前执行代码的线程进行等待，该方法会将该线程放入”预执行队列“中，并且在wait()所在的代码处停止执行，直到接到通知或被中断为止；
         2. 在调用wait()之前，线程必须获得该对象级别锁，这是一个很重要的地方，很多时候我们可能会忘记这一点，即只能在同步方法或同步块中调用wait()方法；
         3. 还需要注意的是wait()是释放锁的，即在执行到wait()方法之后，当前线程会释放锁，当从wait()方法返回前，线程与其他线程竞争重新获得锁。
      * notify方法
         1. 和wait()方法一样,notify()方法也要在同步块或同步方法中调用，即在调用前，线程也必须获得该对象的对象级别锁；
         2. 该方法是用来通知那些可能等待该对象的对象锁的其他线程，如果有多个线程等待，则由线程规划器随机挑选出其中一个呈wait状态的线程，对其发出通知notify，并使它等待获取该对象的对象锁；
         3. 这里需要注意的是，执行notify方法之后，当前线程不会立即释放其拥有的该对象锁，而是执行完之后才会释放该对象锁，被通知的线程也不会立即获得对象锁，而是等待notify方法执行完之后，释放了该对象锁，才可以获得该对象锁；
         4. notifyAll()通知所有等待同一共享资源的全部线程从等待状态退出，进入可运行状态，重新竞争获得对象锁。
      * 总结
         1. wait()/notify()要集合synchronized关键字一起使用，因为他们都需要首先获取该对象的对象锁；
         2. wait方法是释放锁，notify方法是不释放锁的；
   * 线程另一种是Lock
      * Lock lock = new ReentrantLock();  
        Condition condition = lock.newCondition();
      1. Object的wait()方法相当于Condition类中的await()方法； 
      2. Object的notify()方法相当于Condition类中的signal()方法； 
      3. Object的notifyAll()方法相当于Condition类中的signalAll()方法
      * Condition按字面意思理解就是条件，当然，我们也可以将其认为是条件进行使用，这样的话我们可以通过上述的代码创建多个Condition条件，我们就可以根据不同的条件来控制现成的等待和通知。而我们还知道，在使用关键字synchronized与wait()方法和notify()方式结合实现线程间通信的时候，notify/notifyAll的通知等待的线程时是随机的，显然使用Condition相对灵活很多，可以实现”选择性通知”
      
* **说说 CountDownLatch、CyclicBarrier 原理和区别**
   * CountDownLatch 倒计时
      * CountDownLatch是一个非常实用的多线程控制工具类，称之为“倒计时器”，它允许一个或多个线程一直等待，直到其他线程的操作执行完后再执行。
      * CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。每当一个线程完成了自己的任务后，计数器的值就会减1。当计数器值到达0时，它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。
      * public CountDownLatch(int count) { ... }  
         1. 构造器中的计数值（count）实际上就是闭锁需要等待的线程数量。这个值只能被设置一次，而且CountDownLatch没有提供任何机制去重新设置这个计数值。
         2. 与CountDownLatch的第一次交互是主线程等待其他线程。主线程必须在启动其他线程后立即调用CountDownLatch.await()方法。这样主线程的操作就会在这个方法上阻塞，直到其他线程完成各自的任务。
         3. 其他N个线程必须引用闭锁对象，因为他们需要通知CountDownLatch对象，他们已经完成了各自的任务。这种通知机制是通过 CountDownLatch.countDown()方法来完成的；每调用一次这个方法，在构造函数中初始化的count值就减1（CAS）。所以当N个线程都调用了这个方法，count的值等于0，然后主线程就能通过await()方法，恢复执行自己的任务。
         4. demo : com.furui.thread.count.CountDownLatchSummonDragonDemo
      * 应用
         1. 实现最大的并行性：有时我们想同时启动多个线程，实现最大程度的并行性。例如，我们想测试一个单例类。如果我们创建一个初始计数为1的CountDownLatch，并让所有线程都在这个锁上等待，那么我们可以很轻松地完成测试。我们只需调用 一次countDown()方法就可以让所有的等待线程同时恢复执行；
         2. 开始执行前等待n个线程完成各自任务：例如应用程序启动类要确保在处理用户请求前，所有N个外部系统已经启动和运行了；
         3. 死锁检测：一个非常方便的使用场景是，你可以使用n个线程访问共享资源，在每次测试阶段的线程数目是不同的，并尝试产生死锁。
   * CyclicBarrier 循环屏障
      * CyclicBarrier 是另一种多线程并发控制使用工具，和CountDownLatch非常类似，他也可以实现线程间的计数等待，但他的功能要比CountDownLatch更加强大一些；
      * CyclicBarrier 的字面意思是可循环使用（Cyclic）的屏障（Barrier）。它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活；
      * CyclicBarrier默认的构造方法是CyclicBarrier(int parties)，其参数表示屏障拦截的线程数量，每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞。
      * CyclicBarrier强调的是n个线程，大家相互等待，只要有一个没完成，所有人都得等着;
      * CyclicBarrier.reset() 可以使CyclicBarrier回到最初始的状态
      * demo : com.furui.thread.count.CyclicBarrierSummonDragonDemo
   * 区别
      1. CountDownLatch的计数器只能使用一次。而CyclicBarrier的计数器可以使用reset() 方法重置。所以CyclicBarrier能处理更为复杂的业务场景，比如如果计算发生错误，可以重置计数器，并让线程们重新执行一次；
      2. CyclicBarrier还提供其他有用的方法，比如getNumberWaiting方法可以获得CyclicBarrier阻塞的线程数量。isBroken方法用来知道阻塞的线程是否被中断。
      3. CountDownLatch会阻塞主线程，CyclicBarrier不会阻塞主线程，只会阻塞子线程。
      
* **说说 Semaphore 原理**
* **说说 Exchanger 原理**
* **ThreadLocal 原理分析，ThreadLocal为什么会出现OOM，出现的深层次原理**
   * 原理
      1. ThreadLocal并不是一个Thread，而是Thread的一个局部变量，当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
      2. 实现思路：在ThreadLocal类中有一个static声明的Map（ThreadLocalMap），用于存储每一个线程的变量副本，Map中元素的键为线程对象，而值对应线程的变量副本
      3. demo: com.furui.thread.threadlocal.SimpleThreadLocal
      4. 一个Thread中只有一个ThreadLocalMap，一个ThreadLocalMap中可以有多个ThreadLocal对象，其中一个ThreadLocal对象对应一个ThreadLocalMap中的一个Entry（也就是说：一个Thread可以依附有多个ThreadLocal对象）
   * OOM
      1. ThreadLocal变量是维护在Thread内部的，这样的话只要我们的线程不退出，对象的引用就会一直存在。当线程退出时，Thread类会进行一些清理工作，其中就包含ThreadLocalMap.
      2. 但是，当我们使用线程池的时候，就意味着当前线程未必会退出（比如固定大小的线程池，线程总是存在的）。如果这样的话，将一些很大的对象设置到ThreadLocal中（这个很大的对象实际保存在Thread的threadLocals属性中），这样的话就可能会出现内存溢出的情况。
   * 总结
      1. ThreadLocal只是操作Thread中的ThreadLocalMap对象的集合；
      2. ThreadLocalMap变量属于线程的内部属性，不同的线程拥有完全不同的ThreadLocalMap变量；
      3. 线程中的ThreadLocalMap变量的值是在ThreadLocal对象进行set或者get操作时创建的；
      4. 使用当前线程的ThreadLocalMap的关键在于使用当前的ThreadLocal的实例作为key来存储value值；
      5. ThreadLocal模式至少从两个方面完成了数据访问隔离，即纵向隔离(线程与线程之间的ThreadLocalMap不同)和横向隔离(不同的ThreadLocal实例之间的互相隔离)；
      6. 一个线程中的所有的局部变量其实存储在该线程自己的同一个map属性中；
      7. 线程死亡时，线程局部变量会自动回收内存；
      8. 线程局部变量时通过一个 Entry 保存在map中，该Entry 的key是一个 WeakReference包装的ThreadLocal, value为线程局部变量，key 到 value 的映射是通过：ThreadLocal.threadLocalHashCode & (INITIAL_CAPACITY - 1) 来完成的；
      9. 当线程拥有的局部变量超过了容量的2/3(没有扩大容量时是10个)，会涉及到ThreadLocalMap中Entry的回收；
      
* **讲讲线程池的实现原理**
   * 细节： com.furui.thread.pool
   * 工作流程：
      1. 判断线程池里的核心线程是否都在执行任务，如果不是（核心线程空闲或者还有核心线程没有被创建）则创建一个新的工作线程来执行任务。如果核心线程都在执行任务，则进入下个流程；
      2. 线程池判断工作队列是否已满，如果工作队列没有满，则将新提交的任务存储在这个工作队列里。如果工作队列满了，则进入下个流程；
      3. 判断线程池里的线程是否都处于工作状态，如果没有，则创建一个新的工作线程来执行任务。如果已经满了，则交给饱和策略来处理这个任务。
   * JDK提供了Executors类来创建几种线程池，核心是封装了ThreadPoolExecutor的不同参数的方法
      1. corePoolSize 核心线程池大小；
      2. maximumPoolSize 线程池最大容量大小；
      3. keepAliveTime 线程池空闲时，线程存活的时间；
      4. TimeUnit 时间单位；
      5. ThreadFactory 线程工厂；
      6. BlockingQueue任务队列；
      7. RejectedExecutionHandler 线程拒绝策略；
   * execute和submit的区别
      1. execute()方法用于提交不需要返回值的任务，所以无法判断任务是否被线程池执行成功。通过以下代码可知execute()方法输入的任务是一个Runnable类的实例。
      2. submit()方法用于提交需要返回值的任务。线程池会返回一个future类型的对象，通过这个future对象可以判断任务是否执行成功，并且可以通过future的get()方法来获取返回值，get()方法会阻塞当前线程直到任务完成，而使用get（long timeout，TimeUnit unit）方法则会阻塞当前线程一段时间后立即返回，这时候有可能任务没有执行完
      
* **线程的生命周期，状态是如何转移的**
   * （1）新建状态——（2）就绪状态—（（4）阻塞状态）—（3）运行状态——（5）死亡状态 
   * 状态详解
      * New：创建线程对象后，该线程处于新建状态，此时它不能运行，和其他Java对象一样，仅仅有Java虚拟机为其分配了内存，没有表现出任何线程的动态特征；
      * Runnable：线程对象调用了start（）方法后，该线程就进入了就绪状态（也称可运行状态）。处于就绪状态的线程位于可运行池中，此时它只是具备了运行的条件，能否获得CPU的使用权开始运行，还需要等待系统的调度；
      * Runing：处于就绪状态的线程获得了CPU使用权，开始执行run（）方法中的线程执行体，则线程处于运行状态。当一个线程启动后，它不能一直处于运行状态（除非它的线程执行体足够短，瞬间结束），当使用完系统分配的时间后，系统就会剥脱该线程占用的CPU资源，让其他线程获得执行的机会。只有处于就绪状态的线程才可能转换到运行状态；
      * Blocked：一个正在执行的线程在某些特殊情况下，如执行耗时的输入/输出操作时，会放弃CPU的使用权，进入阻塞状态。线程进入阻塞状态后，就不能进入排队队列。只有当引用阻塞的原因被消除后，线程才可以进入就绪状态；
         1. 线程试图获取某个对象的同步锁时，如果该锁被其他线程所持有，则当前线程进入阻塞状态，如果想从阻塞状态进入就绪状态必须得获取到其他线程所持有的锁。 
         2. 当线程调用了一个阻塞式的IO方法时，该线程就会进入阻塞状态，如果想进入就绪状态就必须要等到这个阻塞的IO方法返回。 
         3. 当线程调用了某个对象的wait（）方法时，也会使线程进入阻塞状态，notify（）方法唤醒。 
         4. 调用了Thread的sleep（long millis）。线程睡眠时间到了会自动进入阻塞状态。 
         5. 一个线程调用了另一个线程的join（）方法时，当前线程进入阻塞状态。等新加入的线程运行结束后会结束阻塞状态，进入就绪状态。 
      * Terminated：线程的run（）方法正常执行完毕或者线程抛出一个未捕获的异常（Exception）、错误（Error），线程就进入死亡状态。一旦进入死亡状态，线程将不再拥有运行的资格，也不能转换为其他状态。