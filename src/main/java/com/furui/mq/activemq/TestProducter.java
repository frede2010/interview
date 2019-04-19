package com.furui.mq.activemq;

/**
 * @author furui
 * @date 2018/2/28 0028
 */
public class TestProducter {
    public static void main(String[] args){
        Producter producter = new Producter();
        producter.init();
        TestProducter testMq = new TestProducter();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Thread 1
        new Thread(testMq.new ProductorMq(producter)).start();
        //Thread 2
//        new Thread(testMq.new ProductorMq(producter)).start();
//        //Thread 3
//        new Thread(testMq.new ProductorMq(producter)).start();
//        //Thread 4
//        new Thread(testMq.new ProductorMq(producter)).start();
//        //Thread 5
//        new Thread(testMq.new ProductorMq(producter)).start();
    }

    private class ProductorMq implements Runnable{
        Producter producter;
        public ProductorMq(Producter producter){
            this.producter = producter;
        }

        @Override
        public void run() {
            while(true){
                try {
                    producter.sendMessage("Jaycekon-MQ", "cps_order");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}