package ru.netology;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Assertions;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {

    @SneakyThrows
    public static PaymentEntity paymentEntity() {
        var sql = "SELECT * FROM payment_entity order by created desc limit 1;";
        var runner = new QueryRunner();

        try (
                var conn = connection();
        ) {
            return runner.query(conn, sql, new BeanHandler<>(PaymentEntity.class));
        }

    }
    @SneakyThrows
    public static CreditRequestEntity creditRequestEntity() {
        var sql = "SELECT * FROM credit_request_entity order by created desc limit 1;";
        var runner = new QueryRunner();

        try (
                var conn = connection();
        ) {
            return runner.query(conn, sql, new BeanHandler<>(CreditRequestEntity.class));
        }

    }

    private static Connection connection() {
        String url = System.getenv("DATASOURCE_URL");
        if (url == null) {
            url = "jdbc:mysql://localhost:3306/app";
        }
        return connection(url);
    }
    @SneakyThrows
    private static Connection connection(String url) {

        return DriverManager.getConnection(
                url, "app", "pass"
        );
    }

    @SneakyThrows
    public static void prepareDb() {
        var sql = "Delete from credit_request_entity;";
        var sql1 = "Delete from order_entity;";
        var sql2 = "Delete from payment_entity;";
        var runner = new QueryRunner();

        try (
                var conn = connection();
        ) {
            runner.execute(conn, sql);
            runner.execute(conn, sql1);
            runner.execute(conn, sql2);
        }
    }

    @SneakyThrows
    public static void assertDbEmpty() {
        var sql = "Select count(*) from credit_request_entity;";
        var sql1 = "Select count(*) from order_entity;";
        var sql2 = "Select count(*) from payment_entity;";
        var runner = new QueryRunner();

        try (
                var conn = connection();
        ) {
            Long count0 = runner.query(conn, sql, new ScalarHandler<>());
            Assertions.assertEquals(0, count0);
            Long count1 = runner.query(conn, sql1, new ScalarHandler<>());
            Assertions.assertEquals(0, count1);
            Long count2 = runner.query(conn, sql2, new ScalarHandler<>());
            Assertions.assertEquals(0, count2);
        }
    }
}