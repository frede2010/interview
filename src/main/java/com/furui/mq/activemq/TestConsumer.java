package com.furui.mq.activemq;

/**
 * @author furui
 * @date 2018/2/28 0028
 */
public class TestConsumer {
    public static void main(String[] args){
        Consumer consumer = new Consumer();
        consumer.init();
        TestConsumer testConsumer = new TestConsumer();
        new Thread(() -> {
            while(true){
                consumer.getMessage("Jaycekon-MQ");
            }
        }, "cps_thread").start();
        new Thread(testConsumer.new ConsumerMq(consumer)).start();
        new Thread(testConsumer.new ConsumerMq(consumer)).start();
        new Thread(testConsumer.new ConsumerMq(consumer)).start();
    }

    private class ConsumerMq implements Runnable{
        Consumer consumer;
        public ConsumerMq(Consumer consumer){
            this.consumer = consumer;
        }

        @Override
        public void run() {
            while(true){
//                try {
                    consumer.getMessage("Jaycekon-MQ");
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }
}
