package com.furui.mq.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author furui
 * @date 2018/2/28 0028
 */
public class Producter {

    //ActiveMq 的默认用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //ActiveMq 的默认登录密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //ActiveMQ 的链接地址
    private static final String BROKEN_URL = "tcp://10.200.6.99:61616";

    AtomicInteger count = new AtomicInteger(0);
    //链接工厂
    ConnectionFactory connectionFactory;
    //链接对象
    Connection connection;
    //事务管理
    Session session;
    ThreadLocal<MessageProducer> threadLocal = new ThreadLocal<>();

    public void init(){
        try {
            //创建一个链接工厂
            connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEN_URL);
            //从工厂中创建一个链接
            connection  = connectionFactory.createConnection();
            //开启链接
            connection.start();
            //创建一个事务（这里通过参数可以设置事务的级别）
            session = connection.createSession(true, Session.SESSION_TRANSACTED);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String disname) {
        sendMessage(disname, null);
    }

    public void sendMessage(String disname, String groupId){
        try {
            //创建一个消息队列
            Queue queue = session.createQueue(disname);
//            Topic topic = session.createTopic(disname);
            //消息生产者
            MessageProducer messageProducer = null;
            if(threadLocal.get()!=null){
                messageProducer = threadLocal.get();
            }else{
                messageProducer = session.createProducer(queue);
//                messageProducer = session.createProducer(topic);
                threadLocal.set(messageProducer);
            }
            while(true){
                Thread.sleep(1000);
                int num = count.getAndIncrement();
                //创建一条消息
                TextMessage msg = session.createTextMessage(Thread.currentThread().getName()+
                        "productor:我家小菡菡是大美女，我现在正在生产东西！,count:"+num);
                System.out.println(Thread.currentThread().getName()+
                        "productor:我家小菡菡是大美女，我现在正在生产东西！,count:"+num);
                if (groupId != null) {
                    msg.setStringProperty("JMSXGroupID", groupId);
                }
//                msg.setIntProperty("JMSXGroupSeq", i +1);
                //发送消息
                messageProducer.send(msg);
                //提交事务
                session.commit();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}