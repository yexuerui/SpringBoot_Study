package com.newBuilder;

import com.rabbitmq.client.AMQP;

public class MQBuilder {
    public static void main(String[] args) {
        // 一个是replyTo设置回调队列，另一是correlationId(相关性Id)为每个队列设置唯一值
        AMQP.BasicProperties props = new AMQP.BasicProperties().builder().correlationId("XP123")
                .replyTo("replyQueueName").build();
    }
}
