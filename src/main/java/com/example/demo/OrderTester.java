package com.example.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

import java.text.Annotation;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.UUID;

public class OrderTester {
    public static void main(String[] args) {

        var applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        var customerId = UUID.randomUUID();
        var orderService = applicationContext.getBean(OrderService.class);

//        var orderContext = new AppConfiguration();
//        var orderService = orderContext.orderService();

        var orderItems = new ArrayList<OrderItem>() {{
            add(new OrderItem(UUID.randomUUID(), 100L, 1));
        }}; // 100원짜리 1개를 주문

        var order = orderService.createOrder(customerId, orderItems);

        var fixedAmountVoucher = new FixedAmountVoucher(UUID.randomUUID(), 10L);

        Assert.isTrue(order.totalAmount() == 100L, MessageFormat.format("totalAmount {0} is not 100L", order.totalAmount()));
    }
}
