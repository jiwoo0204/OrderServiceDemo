package com.example.demo;

import com.example.demo.voucher.FixedAmountVoucher;
import com.example.demo.voucher.VoucherRepository;
import com.example.demo.order.OrderItem;
import com.example.demo.order.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.UUID;

public class OrderTester {
    public static void main(String[] args) {

        var applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        var customerId = UUID.randomUUID();
        var voucherRepository = applicationContext.getBean(VoucherRepository.class);
        var voucher = voucherRepository.insert(new FixedAmountVoucher(UUID.randomUUID(), 10L));

//        var orderContext = new AppConfiguration();
        var orderService = applicationContext.getBean(OrderService.class);

        var orderItems = new ArrayList<OrderItem>() {{
            add(new OrderItem(UUID.randomUUID(), 100L, 1));
        }}; // 100원짜리 1개를 주문

        var order = orderService.createOrder(customerId, orderItems, voucher.getVoucherId());

//        var fixedAmountVoucher = new FixedAmountVoucher(UUID.randomUUID(), 10L);

        Assert.isTrue(order.totalAmount() == 90L, MessageFormat.format("totalAmount {0} is not 90L", order.totalAmount()));
    }
}
