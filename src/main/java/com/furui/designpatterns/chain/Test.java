package com.furui.designpatterns.chain;


/**
 * @author furui
 * @date 2020-02-14 16:09
 */
public class Test {

    /**
     * 责任链模式测试
     * @param args
     */
    public static void main(String[] args) {
        build(3, "Test high level");
    }

    /**
     * 组装责任链
     * @param level
     * @param message
     */
    public static void build(int level, String message) {
        Handler debugLogger = new DebugLogger();
        Handler infoLogger = new InfoLogger();
        Handler errorLogger = new ErrorLogger();
        debugLogger.setNextHandler(infoLogger);
        infoLogger.setNextHandler(errorLogger);
        debugLogger.handlerRequest(level, message);
    }
}
