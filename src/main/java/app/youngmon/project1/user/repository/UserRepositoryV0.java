package app.youngmon.project1.user.repository;

import app.youngmon.project1.user.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.NoSuchElementException;

/*
 *  JDBC - DriverManager 사용
 */
@Slf4j
public class UserRepositoryV0 implements UserRepository{
    @Override
    public Long save(User user) {
        String query = "INSERT INTO USER(id, userId, userPw) values(?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);

            pstmt.setLong(1, 1L);
            pstmt.setString(2, user.getUserId());
            pstmt.setString(3, user.getUserPw());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Connection Error", e);
        } finally {
            close(conn, pstmt, null);
        }

        return 1L;
    }

    @Override
    public User findById(Long id) {
        String query = "select * from user where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUserId(rs.getString("userId"));
                user.setUserPw(rs.getString("userPw"));
                return user;
            } else {
                throw new NoSuchElementException("User not found By user id : " + id);
            }
        } catch (Exception e) {
            log.error("Connection Error", e);
        } finally {
            close(conn, pstmt, rs);
        }
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
