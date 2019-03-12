package com.lajigaga.MQ.JMS;

import javax.jms.*;

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

    }
}
