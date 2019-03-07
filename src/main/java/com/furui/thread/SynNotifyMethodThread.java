package com.furui.thread;

/**
 * @author furui
 * @date 2019/3/5 0005
 */
public class SynNotifyMethodThread extends Thread {
    private Object lock;

    public SynNotifyMethodThread(Object lock) {
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        Service service = new Service();
        service.synNotifyMethod(lock);
    }
}
