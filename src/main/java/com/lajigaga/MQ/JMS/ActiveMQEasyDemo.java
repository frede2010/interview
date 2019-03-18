package com.lajigaga.MQ.JMS;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by lajigaga on 2019/3/18 0018.
 * ActiveMQ是Apache软件基金下的一个开源软件，
 * 它遵循JMS1.1规范（Java Message Service），是消息驱动中间件软件（MOM）。
 * 它为企业消息传递提供高可用，出色性能，可扩展，稳定和安全保障。
 *
 * P2P （点对点）消息域使用 queue 作为 Destination，消息可以被同步或异步的发送和接收，
 * 每个消息只会给一个 Consumer 传送一次。
 *
 * Pub/Sub（发布/订阅，Publish/Subscribe）消息域使用 topic 作为 Destination，
 * 发布者向 topic 发送消息，订阅者注册接收来自 topic 的消息。
 * 发送到 topic 的任何消息都将自动传递给所有订阅者。
 * 接收方式（同步和异步）与 P2P 域相同。
 */
public class ActiveMQEasyDemo {

    /**
     * 消息生产者
     */
    public static void ActiveMQProducer(){
        try {
            //创建session会话
            ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
            Connection connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

            //创建一个消息队列 session.createQueue("jms.test.topic")--P2P模式
            Destination destination = session.createTopic("jms.test.topic");

            //创建消息生产者
            MessageProducer producer = session.createProducer(destination);

            //消息持久化
//            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            for (int i = 0; i < 5; i++) {
                producer.send(session.createTextMessage("Message Producer:" + i));
            }

            //提交会话
            session.commit();
            connection.close();
        }catch (Exception e){

        }
    }

    public static void ActiveMQConsumer() {
        try {
            //创建session会话
            ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
            Connection connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

            //创建一个消息队列 session.createQueue("jms.test.topic")--P2P模式
            Destination destination = session.createTopic("jms.test.topic");

            //创建消息消费者
            MessageConsumer consumer = session.createConsumer(destination);

            while (true) {
                TextMessage message = (TextMessage) consumer.receive();
                if (message != null) {
                    System.out.println("Message Consumer:" + message.getText());
                } else {
                    break;
                }
            }
            session.commit();

        } catch (Exception e) {

        }

    }

    public static void main(String[] args) {
        //TODO 本地安装ActiveMQ可以发消息，但在消费端接收消息时有问题
        ActiveMQEasyDemo.ActiveMQProducer();
        ActiveMQEasyDemo.ActiveMQConsumer();
    }
}
