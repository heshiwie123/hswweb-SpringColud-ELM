package com.he.elm.mq;

import com.alibaba.fastjson.JSON;
import com.he.elm.common.constant.Constant;
import com.he.elm.common.pojo.Orders;
import com.he.elm.common.pojo.User;
import com.he.elm.service.BusinessService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MQOrderListener {
    @Resource
    public BusinessService businessService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = Constant.ORDERS),
            exchange = @Exchange(name = Constant.TOPIC_EXCHANGE,type = ExchangeTypes.TOPIC),
            key = "topic.orders"
    ))
    public void listenOrdersTopicExchange(Orders msg)  throws InterruptedException{
        Orders orders = msg;

        log.info("消费了消息: {}", msg);
        log.info("解析后的订单: {}", orders);

        businessService.listenOrdersTopicExchange(orders.getOrderId());
    }
}
