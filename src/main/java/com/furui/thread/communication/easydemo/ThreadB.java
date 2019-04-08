package com.furui.thread.communication.easydemo;

/**
 * @author furui
 * @date 2019/4/8 0008
 */
public class ThreadB extends Thread {

    private MyList lock;

    ThreadB(MyList lock) {
        this.lock = lock;
    }
    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     * @see #Thread(ThreadGroup, Runnable, String)
     */
    @Override
    public void run() {
        try {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    lock.add();
                    if (lock.size() == 5) {
                        lock.notify();
                        System.out.println("notify!");
                    }
                    System.out.println("add " + (i + 1) + "elements");
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
