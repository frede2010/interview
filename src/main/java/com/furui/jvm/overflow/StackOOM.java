package com.furui.jvm.overflow;

/**
 * 通过不断地建立线程的方式倒是可以产生内存溢出异常（不要随便运行，会造成系统假死）
 * 但是这样产生的内存溢出异常与栈空间是否足够大并不存在任何联系，或者准确地说，在这种情况下，为每个线程的栈分配的内存越大，反而越容易产生内存溢出异常。
 * @author furui
 * @date 2019/4/11 0011
 */
public class StackOOM {

    public static void main(String[]args)throws Throwable{
        StackOOM oom = new StackOOM();
        oom.stackLeakByThread();
    }
    
    private void dontStop(){
        while(true){
        }
    }
    public void stackLeakByThread(){
        while(true){
            Thread thread = new Thread(() -> dontStop());
            thread.start();
        }
    }
}
