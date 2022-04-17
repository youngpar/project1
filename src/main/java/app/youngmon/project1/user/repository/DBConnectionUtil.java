package app.youngmon.project1.user.repository;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static app.youngmon.project1.user.repository.ConnectionConst.*;

@Slf4j
public class DBConnectionUtil {
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.info("get Connection = {}, class = {}", conn, conn.getClass());
            return conn;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
