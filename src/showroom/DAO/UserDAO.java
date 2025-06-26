package showroom.DAO;

import showroom.model.User; //
import showroom.util.DatabaseConnection; //

import java.sql.Connection; //
import java.sql.PreparedStatement; //
import java.sql.ResultSet; //
import java.sql.SQLException; //
import java.sql.Statement; //
import java.util.ArrayList; //
import java.util.List; //

public class UserDAO {

    // Đã đổi tên insertUser thành registerUser để rõ ràng hơn với RegisterView
    public boolean registerUser(User user) {
        // Đã thêm full_name vào câu lệnh INSERT
        String sql = "INSERT INTO Users (username, password, full_name, role) VALUES (?, ?, ?, ?)"; //
        try (Connection conn = DatabaseConnection.getConnection(); //
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { //
            pstmt.setString(1, user.getUsername()); //
            pstmt.setString(2, user.getPassword()); //
            pstmt.setString(3, user.getFullName()); // Đã thêm full_name
            pstmt.setString(4, user.getRole()); //

            int affectedRows = pstmt.executeUpdate(); //

            if (affectedRows > 0) { //
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) { //
                    if (generatedKeys.next()) { //
                        user.setId(generatedKeys.getInt(1)); // Đã đổi setUserId thành setId
                    }
                }
                return true; //
            }
        } catch (SQLException e) { //
            System.err.println("Lỗi khi đăng ký người dùng: " + e.getMessage());
            e.printStackTrace(); //
        }
        return false; //
    }

    public User checkLogin(String username, String password) {
        String sql = "SELECT user_id, username, password, full_name, role FROM Users WHERE username = ? AND password = ?"; // Đã thêm full_name
        User user = null; //
        try (Connection conn = DatabaseConnection.getConnection(); //
             PreparedStatement pstmt = conn.prepareStatement(sql)) { //
            pstmt.setString(1, username); //
            pstmt.setString(2, password); //
            try (ResultSet rs = pstmt.executeQuery()) { //
                if (rs.next()) { //
                    user = new User(); //
                    user.setId(rs.getInt("user_id")); // Đã đổi setUserId thành setId
                    user.setUsername(rs.getString("username")); //
                    user.setPassword(rs.getString("password")); //
                    user.setFullName(rs.getString("full_name")); // Đã thêm full_name
                    user.setRole(rs.getString("role")); //
                }
            }
        } catch (SQLException e) { //
            System.err.println("Lỗi khi kiểm tra đăng nhập: " + e.getMessage());
            e.printStackTrace(); //
        }
        return user; //
    }

    public boolean isUsernameExists(String username) {
        String sql = "SELECT 1 FROM Users WHERE username = ?"; //
        try (Connection conn = DatabaseConnection.getConnection(); //
             PreparedStatement pstmt = conn.prepareStatement(sql)) { //
            pstmt.setString(1, username); //
            try (ResultSet rs = pstmt.executeQuery()) { //
                return rs.next(); // Nếu một hàng được trả về, tên người dùng tồn tại
            }
        } catch (SQLException e) { //
            System.err.println("Lỗi khi kiểm tra tên người dùng tồn tại: " + e.getMessage());
            e.printStackTrace(); //
        }
        return false; //
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>(); //
        // Đã thêm full_name vào câu lệnh SELECT
        String sql = "SELECT user_id, username, password, full_name, role, created_at FROM Users"; //
        try (Connection conn = DatabaseConnection.getConnection(); //
             Statement stmt = conn.createStatement(); //
             ResultSet rs = stmt.executeQuery(sql)) { //
            while (rs.next()) { //
                User user = new User(); //
                user.setId(rs.getInt("user_id")); // Đã đổi setUserId thành setId
                user.setUsername(rs.getString("username")); //
                user.setPassword(rs.getString("password")); //
                user.setFullName(rs.getString("full_name")); // Đã thêm full_name
                user.setRole(rs.getString("role")); //
                // SQLite lưu trữ TIMESTAMP dưới dạng TEXT, vì vậy getTimestamp có thể hoạt động nếu định dạng tương thích.
                // Nếu không, bạn có thể đọc dưới dạng String và phân tích cú pháp.
                user.setCreatedAt(rs.getTimestamp("created_at")); //
                users.add(user); //
            }
        } catch (SQLException e) { //
            System.err.println("Lỗi khi lấy tất cả người dùng: " + e.getMessage());
            e.printStackTrace(); //
        }
        return users; //
    }

    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM Users WHERE user_id = ?"; //
        try (Connection conn = DatabaseConnection.getConnection(); //
             PreparedStatement pstmt = conn.prepareStatement(sql)) { //
            pstmt.setInt(1, userId); //
            return pstmt.executeUpdate() > 0; //
        } catch (SQLException e) { //
            System.err.println("Lỗi khi xóa người dùng: " + e.getMessage());
            e.printStackTrace(); //
        }
        return false; //
    }

    public boolean updateUserRole(int userId, String newRole) {
        String sql = "UPDATE Users SET role = ? WHERE user_id = ?"; //
        try (Connection conn = DatabaseConnection.getConnection(); //
             PreparedStatement pstmt = conn.prepareStatement(sql)) { //
            pstmt.setString(1, newRole); //
            pstmt.setInt(2, userId); //
            return pstmt.executeUpdate() > 0; //
        } catch (SQLException e) { //
            System.err.println("Lỗi khi cập nhật vai trò người dùng: " + e.getMessage());
            e.printStackTrace(); //
        }
        return false; //
    }
}