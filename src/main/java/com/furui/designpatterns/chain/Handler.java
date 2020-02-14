package com.furui.designpatterns.chain;

/**
 * 抽象的类
 * @author furui
 * @date 2020-02-14 15:46
 */
public abstract class Handler {

    public static int DEBUG = 1;
    public static int INFO = 2;
    public static int ERROR = 3;

    /**
     * 责任链中的下一个元素
     */
    private Handler nextHandler;

    /**
     * 设置下一个节点
     * @param nextHandler
     */
    public void setNextHandler(Handler nextHandler){
        this.nextHandler = nextHandler;
    }

    /**
     * 取值方法
     */
    public Handler getNextHandler() {
        return nextHandler;
    }

    /**
     * 处理请求，子类实现
     * @param level
     * @param message
     */
    public abstract void handlerRequest(int level, String message);

}
