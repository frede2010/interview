package com.furui.mq.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author furui
 * @date 2018/2/28 0028
 */
public class Consumer {

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;

    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

    private static final String BROKEN_URL = "tcp://10.200.6.99:61616";

    ConnectionFactory connectionFactory;

    Connection connection;

    Session session;

    ThreadLocal<MessageConsumer> threadLocal = new ThreadLocal<>();
    AtomicInteger count = new AtomicInteger();

    public void init(){
        try {
            connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEN_URL);
            connection  = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void getMessage(String disname){
        try {
            Queue queue = session.createQueue(disname);
//            Topic topic = session.createTopic(disname);
            MessageConsumer consumer = null;

            if(threadLocal.get() != null){
                consumer = threadLocal.get();
            }else{
                consumer = session.createConsumer(queue);
//                consumer = session.createConsumer(topic);
                threadLocal.set(consumer);
            }
            while(true){
//                Thread.sleep(1000);
                TextMessage msg = (TextMessage) consumer.receive();
                if(msg!=null) {
                    msg.acknowledge();
                    System.out.println(Thread.currentThread().getName()+": Consumer:我是消费者，我正在消费Msg"+msg.getText()+"--->"+count.getAndIncrement());
                }else {
                    break;
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } /*catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
