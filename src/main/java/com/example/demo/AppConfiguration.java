package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.UUID;

@Configuration
@ComponentScan(basePackages = {"com.example.demo.order", "com.example.demo.voucher"})
public class AppConfiguration {

//    @Bean
//    public VoucherRepository voucherRepository() {
//        return new VoucherRepository() {
//            @Override
//            public Optional<Voucher> findById(UUID voucherId) {
//                return Optional.empty();
//            }
//        };
//    }
//    @Bean
//    public OrderRepository orderRepository() {
//        return new OrderRepository() {
//
//            @Override
//            public Order insert(Order order) {
//
//                return order;
//            }
//        };
//    }
//    @Bean
//    public VoucherService voucherService() {
//        return new VoucherService(voucherRepository());
//    }
//    @Bean
//    public OrderService orderService() {
//        return new OrderService(voucherService(), orderRepository());
//    }
}
