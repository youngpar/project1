package app.youngmon.project1.user.repository;

import app.youngmon.project1.user.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

import static app.youngmon.project1.user.repository.ConnectionConst.*;

/*
 *  JDBC - DriverManager 사용
 */
@Slf4j
public class UserRepositoryV0 implements UserRepository{
    @Override
    public Long save(User user) throws SQLException {
        String query = "INSERT INTO USER(userId, userPw) values(?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getUserPw());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Connection Error", e);
            throw e;
        } finally {
            close(conn, pstmt, null);
        }

        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }

    private void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                log.info("error", e);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
                log.info("error", e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                log.info("error", e);
            }
        }
    }
}
