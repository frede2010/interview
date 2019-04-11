package com.furui.jvm.overflow;

/**
 * 如果线程请求的栈深度大于虚拟机栈允许的最大深度，将抛出StackOverflowError异常。
 * 我们知道，每当Java程序启动一个新的线程时，Java虚拟机会为它分配一个栈，并且Java虚拟机栈以栈帧为单位保持线程运行状态。
 * 每当线程调用一个方法时，JVM就压入一个新的栈帧到这个线程的栈中，只要这个方法还没返回，这个栈帧就存在。
 * 那么可以想象，如果方法的嵌套调用层次太多，比如递归调用，
 * 随着Java虚拟机栈中的栈帧的不断增多，最终很可能会导致这个线程的栈中的所有栈帧的大小的总和大于-Xss设置的值，从而产生StackOverflowError溢出异常
 * java.lang.StackOverflowError
 * @author furui
 * @date 2019/4/11 0011
 */
public class StackSOF {

    /**
     * 在单个线程下，无论是由于栈帧太大还是虚拟机栈容量太小，当内存无法分配的时候，虚拟机抛出的都是StackOverflowError异常
     * @param args
     */
    public static void main(String[] args) {
        method();
    }

    /**
     * 递归调用导致 StackOverflowError
     */
    public static void method(){
        method();
    }
}
