package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class JdbcCustomerRepository {
    private static final Logger logger = LoggerFactory.getLogger(JdbcCustomerRepository.class);

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/order_mgmt", "root", "lkj3591300!");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from customers");
            while (resultSet.next()) {
                var name = resultSet.getString("name");
                logger.info("customer name -> {}", name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Got error while closing connection", throwables);
        } finally {
            try {
                if (connection != null) connection.close();
                if (statement != null) statement.close();
                if (resultSet != null) resultSet.close();
            } catch (SQLException exception) {
                logger.error("Got error while closing connection", exception);
            }
        }
    }
}
