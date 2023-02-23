package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.UUID;

public class JdbcCustomerRepository {
    private static final Logger logger = LoggerFactory.getLogger(JdbcCustomerRepository.class);

    public static void main(String[] args) {
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
        try (
            var connection = DriverManager.getConnection("jdbc:mysql://localhost/order_mgmt", "root", "lkj3591300!");
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery("select * from customers");

        ) {
            while (resultSet.next()) {
                var name = resultSet.getString("name");
                var customerId = UUID.nameUUIDFromBytes(resultSet.getBytes("customer_id"));
                logger.info("customer id -> {}, name -> {}", customerId, name);
            }
        }
        catch (SQLException throwables) {
            logger.error("Got error while closing connection", throwables);
        }
    }
}
