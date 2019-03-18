package com.lajigaga.MQ.JMS;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Created by lajigaga on 2019/3/12 0012.
 * JMS(Java Message Service)
 * JMS即Java消息服务应用程序接口，是一个Java平台关于面向消息中间件（MOM）的API，
 * 用于在两个应用程序之间，活分布式系统中发送消息，进行异步通信。Java消息服务是
 * 一个与具体平台无关的API，绝大多数MOM提供商都对JMS提供支持
 *
 * 此DEMO旨在简要说明JMS消息传送对象通用步骤
 */
public class JMSDemo {

    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageProducer messageProducer;
    private MessageConsumer messageConsumer;
    private Message message;

    public  void JMSGeneralProcess(){

        try {
            Context context = new InitialContext();
            //1、获取连接工厂
            connectionFactory = (ConnectionFactory)context.lookup("ConnectionFactoryName");
            //2、使用连接工厂进行连接
            connection = connectionFactory.createConnection();
            //3、启动连接
            connection.start();
            //4、创建会话，第一个参数是是否使用事务，第二个参数是自动确认消息
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //5、(Pub/Sub广播订阅模式)创建一个消息队列session.createQueue("jms.test.queue");(P2P模式)
            destination = session.createTopic("jms.test.topic");
            //6、生产者发送消息
            messageProducer = session.createProducer(destination);
            messageProducer.send(session.createTextMessage("test message"));
            //7、消费者同步接收
            messageConsumer = session.createConsumer(destination);
            TextMessage textMessage = (TextMessage)messageConsumer.receive();
            if(textMessage != null){
                textMessage.getText();
            }
            //8、消费者异步接收
            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if(message != null){
                        //do something;
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
