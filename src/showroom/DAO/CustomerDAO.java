package showroom.DAO;

import showroom.model.Customers; //
import showroom.util.DatabaseConnection; //

import java.sql.Connection; //
import java.sql.PreparedStatement; //
import java.sql.ResultSet; //
import java.sql.SQLException; //
import java.sql.Statement; //
import java.util.ArrayList; //
import java.util.List; //

public class CustomerDAO {

    public boolean addCustomer(Customers customer) { // Sử dụng Customers theo tên file model
        String sql = "INSERT INTO Customers (full_name, address, phone_number, email) VALUES (?, ?, ?, ?)"; // Đã sửa tên cột
        try (Connection conn = DatabaseConnection.getConnection(); //
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { //
            pstmt.setString(1, customer.getFullName()); // Đã thay đổi từ getName()
            pstmt.setString(2, customer.getAddress()); //
            pstmt.setString(3, customer.getPhoneNumber()); // Đã thay đổi từ getPhone()
            pstmt.setString(4, customer.getEmail()); //

            int affectedRows = pstmt.executeUpdate(); //

            if (affectedRows > 0) { //
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) { //
                    if (generatedKeys.next()) { //
                        customer.setCustomerId(generatedKeys.getInt(1)); //
                    }
                }
                return true; //
            }
        } catch (SQLException e) { //
            e.printStackTrace(); //
        }
        return false; //
    }

    public List<Customers> getAllCustomers() {
        List<Customers> customers = new ArrayList<>(); //
        String sql = "SELECT customer_id, full_name, address, phone_number, email FROM Customers"; // Đã sửa tên cột
        try (Connection conn = DatabaseConnection.getConnection(); //
             Statement stmt = conn.createStatement(); //
             ResultSet rs = stmt.executeQuery(sql)) { //
            while (rs.next()) { //
                Customers customer = new Customers(); //
                customer.setCustomerId(rs.getInt("customer_id")); //
                customer.setFullName(rs.getString("full_name")); // Đã thay đổi từ setName()
                customer.setAddress(rs.getString("address")); //
                customer.setPhoneNumber(rs.getString("phone_number")); // Đã thay đổi từ setPhone()
                customer.setEmail(rs.getString("email")); //
                customers.add(customer); //
            }
        } catch (SQLException e) { //
            e.printStackTrace(); //
        }
        return customers; //
    }

    public boolean updateCustomer(Customers customer) {
        String sql = "UPDATE Customers SET full_name = ?, address = ?, phone_number = ?, email = ? WHERE customer_id = ?"; // Đã sửa tên cột
        try (Connection conn = DatabaseConnection.getConnection(); //
             PreparedStatement pstmt = conn.prepareStatement(sql)) { //
            pstmt.setString(1, customer.getFullName()); // Đã thay đổi từ getName()
            pstmt.setString(2, customer.getAddress()); //
            pstmt.setString(3, customer.getPhoneNumber()); // Đã thay đổi từ getPhone()
            pstmt.setString(4, customer.getEmail()); //
            pstmt.setInt(5, customer.getCustomerId()); //
            return pstmt.executeUpdate() > 0; //
        } catch (SQLException e) { //
            e.printStackTrace(); //
        }
        return false; //
    }

    public boolean deleteCustomer(int customerId) {
        String sql = "DELETE FROM Customers WHERE customer_id = ?"; //
        try (Connection conn = DatabaseConnection.getConnection(); //
             PreparedStatement pstmt = conn.prepareStatement(sql)) { //
            pstmt.setInt(1, customerId); //
            return pstmt.executeUpdate() > 0; //
        } catch (SQLException e) { //
            e.printStackTrace(); //
        }
        return false; //
    }

    public Customers getCustomerById(int customerId) {
        String sql = "SELECT customer_id, full_name, address, phone_number, email FROM Customers WHERE customer_id = ?"; // Đã sửa tên cột
        Customers customer = null; //
        try (Connection conn = DatabaseConnection.getConnection(); //
             PreparedStatement pstmt = conn.prepareStatement(sql)) { //
            pstmt.setInt(1, customerId); //
            try (ResultSet rs = pstmt.executeQuery()) { //
                if (rs.next()) { //
                    customer = new Customers(); //
                    customer.setCustomerId(rs.getInt("customer_id")); //
                    customer.setFullName(rs.getString("full_name")); // Đã thay đổi từ setName()
                    customer.setAddress(rs.getString("address")); //
                    customer.setPhoneNumber(rs.getString("phone_number")); // Đã thay đổi từ setPhone()
                    customer.setEmail(rs.getString("email")); //
                }
            }
        } catch (SQLException e) { //
            e.printStackTrace(); //
        }
        return customer; //
    }
}