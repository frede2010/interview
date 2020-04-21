1.  ThreadLocal是什么

ThreadLocal提供线程局部变量。这些变量与普通的变量不同之处在于，每个访问这种变量的线程（通过它的get或set方法）都有自己的、独立初始化的变量副本。

ThreadLocal实例通常是希望将状态关联到一个线程的类的私有静态字段（比如，user ID 或者 Transaction ID 等等）。

（

画外音：这段话表达了三个意思

ThreadLocal是一种变量类型，我们称之为“线程局部变量”
每个线程访问这种变量的时候都会创建该变量的副本，这个变量副本为线程私有
ThreadLocal类型的变量一般用private static加以修饰
）

例如，下面的例子中这个类为每个线程生成唯一标识。一个线程的id是它第一次调用ThreadId.get()方法指定的。

复制代码
package com.cjs.example;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadId {

    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return nextId.getAndIncrement();
        }
    };

    public static int get() {
        return threadId.get();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getId());
                }
            }).start();
        }
    }
}
复制代码
2.  ThreadLocal主要操作

复制代码
/**
 * 返回当前线程对ThreadLocal变量的“初始值”
 * 这个方法将在线程第一次访问变量（通过调用get方法）时被调用，如果之前已经调用过了就不会再调了
 *
 * @return the initial value for this thread-local
 */
protected T initialValue() {
    return null;
}

/**
 * 设置当前线程的ThreadLocal变量的副本为指定的值
 *
 * @param value the value to be stored in the current thread's copy of this thread-local.
 */
public void set(T value) {
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    if (map != null)
        map.set(this, value);
    else
        createMap(t, value);
}

/**
 * 返回当前线程的ThreadLocal变量副本的值
 *
 * @return the current thread's value of this thread-local
 */
public T get() {
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    if (map != null) {
        ThreadLocalMap.Entry e = map.getEntry(this);
        if (e != null) {
            @SuppressWarnings("unchecked")
            T result = (T)e.value;
            return result;
        }
    }
    return setInitialValue();
}

/**
 * 删除当前线程的ThreadLocal变量副本的值
 */
public void remove() {
    ThreadLocalMap m = getMap(Thread.currentThread());
    if (m != null)
        m.remove(this);
}
复制代码
3.  阅读源码



3.1.  set方法







可以看到，ThreadLocalMap底层是一个数组，数组中元素类型是Entry类型

set操作是向当前线程的ThreadLocal.ThreadLocalMap类型的成员变量threadLocals中设置值，key是this，value是我们指定的值

注意，这里传的this代表的是那个ThreadLocal类型的变量（或者说叫对象）

也就是说，每个线程都维护了一个ThreadLocal.ThreadLocalMap类型的对象，而set操作其实就是以ThreadLocal变量为key，以我们指定的值为value，最后将这个键值对封装成Entry对象放到该线程的ThreadLocal.ThreadLocalMap对象中。每个ThreadLocal变量在该线程中都是ThreadLocal.ThreadLocalMap对象中的一个Entry。既然每个ThreadLocal变量都对应ThreadLocal.ThreadLocalMap中的一个元素，那么就可以对这些元素进行读写删除操作。

3.2.  get方法



get()方法就是从当前线程的ThreadLocal.ThreadLocalMap对象中取出对应的ThreadLocal变量所对应的值

同理，remove()方法就是清除这个值

用图形表示的话，大概是这样的：



或者是这样的：



4.  ThreadLocal使用场景

在线程生命周期内传值
最后，一切都归功于ThreadLocalMap