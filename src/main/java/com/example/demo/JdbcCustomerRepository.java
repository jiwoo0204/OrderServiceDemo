package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JdbcCustomerRepository {
    private static final Logger logger = LoggerFactory.getLogger(JdbcCustomerRepository.class);

    public List<String> findNames(String name) {
        var SELECT_SQL = "select * from customers WHERE name = ?";
        List<String> names = new ArrayList<>();
        try (
                var connection = DriverManager.getConnection("jdbc:mysql://localhost/order_mgmt", "root", "lkj3591300!");
                var statement = connection.prepareStatement(SELECT_SQL)
        ) {
            statement.setString(1, name);
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var customerName = resultSet.getString("name");
                    var customerId = UUID.nameUUIDFromBytes(resultSet.getBytes("customer_id"));
                    var createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                    logger.info("customer id -> {}, name -> {}, createdAt -> {}", customerId, customerName, createdAt);
                    names.add(customerName);
                }
            }
        }
        catch (SQLException throwable) {
            logger.error("Got error while closing connection", throwable);
        }

        return names;
    }

    public static void main(String[] args) {
        var names = new JdbcCustomerRepository().findNames("tester01");
        names.forEach(v -> logger.info("Found name : {}", v));
    }
}
