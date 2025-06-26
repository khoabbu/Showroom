package showroom.DAO;

import showroom.model.Invoices; //
import showroom.model.InvoiceDetail; //
import showroom.model.Car; //
import showroom.model.Customers; //
import showroom.model.User; // Import User model
import showroom.util.DatabaseConnection; //

import java.sql.Connection; //
import java.sql.PreparedStatement; //
import java.sql.ResultSet; //
import java.sql.SQLException; //
import java.sql.Statement; //
import java.util.ArrayList; //
import java.util.List; //
import java.util.Date; //

public class InvoiceDAO {
    public boolean insertInvoiceDetail(InvoiceDetail detail) {
    try (Connection conn = DatabaseConnection.getConnection()) {
        String sql = "INSERT INTO invoice_details (invoice_id, car_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, detail.getInvoiceId());
        stmt.setInt(2, detail.getCarId());
        stmt.setInt(3, detail.getQuantity());
        stmt.setDouble(4, detail.getUnitPrice());

        return stmt.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


    public double getTotalRevenue() {
        String sql = "SELECT SUM(total_amount) FROM Invoices";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy tổng doanh thu: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public int getTotalInvoices() {
        String sql = "SELECT COUNT(*) FROM Invoices";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy tổng số hóa đơn: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public List<Object[]> getRevenueByMonth() {
        List<Object[]> data = new ArrayList<>();
        String sql = "SELECT strftime('%m', invoice_date) AS month, strftime('%Y', invoice_date) AS year, SUM(total_amount) " +
                     "FROM Invoices GROUP BY year, month ORDER BY year, month";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String month = rs.getString(1);
                String year = rs.getString(2);
                double revenue = rs.getDouble(3);
                data.add(new Object[]{month, year, revenue});
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi thống kê doanh thu theo tháng: " + e.getMessage());
            e.printStackTrace();
        }
        return data;
    }

   public int insertInvoice(Invoices invoice) {
    int generatedId = -1;

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(
             "INSERT INTO invoices (customer_id, invoice_date) VALUES (?, ?)",
             Statement.RETURN_GENERATED_KEYS)) {

        stmt.setInt(1, invoice.getCustomerId());
        stmt.setDate(2, new java.sql.Date(invoice.getInvoiceDate().getTime())); // ✅ ép kiểu đúng
        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            generatedId = rs.getInt(1);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return generatedId;
}

}