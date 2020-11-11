package com.downstreams.amqp;

import com.downstreams.model.Order;
import com.downstreams.model.Status;
import com.downstreams.service.OrderBookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class OrderSubscriber {

    private Logger logger = LoggerFactory.getLogger(OrderSubscriber.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private OrderBookService bookService;

    @RabbitListener(queues="${jsa.rabbitmq.queue}")
    public void receiveOrders(String msg) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        Order order = xmlMapper.readValue(msg, Order.class);
        order.setStatus(Status.OPEN);
        order.setVersion(1L);
        logger.info("Received: " + order.toString());
        bookService.save(order);
    }
}
