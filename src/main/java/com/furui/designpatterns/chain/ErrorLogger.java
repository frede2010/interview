package com.furui.designpatterns.chain;

/**
 * @author furui
 * @date 2020-02-14 15:53
 */
public class ErrorLogger extends Handler {

    /**
     * 处理请求，子类实现
     *
     * @param level
     * @param message
     */
    @Override
    public void handlerRequest(int level, String message) {
        if(level >= ERROR){
            System.out.println("Error Logger: " + message);
            return;
        }
        if(null != this.getNextHandler()){
            this.getNextHandler().handlerRequest(level, message);
        }else{
            System.out.println("无法处理");
        }
    }

}
