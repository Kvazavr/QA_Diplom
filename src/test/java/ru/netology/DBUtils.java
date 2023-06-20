package ru.netology;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {
    @SneakyThrows
    public static PaymentEntity paymentEntity() {
        var sql = "SELECT * FROM payment_entity order by created desc limit 1;";
        var runner = new QueryRunner();

        try (
                var conn = connection();
        )
        {
            return runner.query(conn, sql, new BeanHandler<>(PaymentEntity.class));
        }

    }
    @SneakyThrows
    private static Connection connection(){
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass"
        );
    }
    @SneakyThrows
    public static void preperedDB() {
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
}