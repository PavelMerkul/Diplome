package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    @SneakyThrows
    private static Connection getCoon() {
        return DriverManager.getConnection(System.getProperty("db.url"), System.getProperty("db.username"), System.getProperty("db.password"));
    }

    @SneakyThrows
    public static String getStatusLastLineCreditRequestEntity() {
        var codeSQL = "SELECT status FROM credit_request_entity order by created DESC limit 1;";
        var conn = getCoon();
        var statusLastLineCreditRequestEntity = runner.query(conn, codeSQL, new ScalarHandler<String>());
        return new String(statusLastLineCreditRequestEntity);
    }

    @SneakyThrows
    public static String getStatusLastLinePaymentRequestEntity() {
        var codeSQL = "SELECT status FROM payment_entity order by created DESC limit 1;";
        var conn = getCoon();
        var statusLastLinePaymentRequestEntity = runner.query(conn, codeSQL, new ScalarHandler<String>());
        return new String(statusLastLinePaymentRequestEntity);
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getCoon();
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
        runner.execute(connection, "DELETE FROM payment_entity");
    }
}