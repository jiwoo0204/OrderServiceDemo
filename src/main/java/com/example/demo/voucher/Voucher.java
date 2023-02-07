package com.example.demo.voucher;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface Voucher {

    UUID getVoucherId();

    long discount(long beforeDiscount);
}
