package showroom.DAO;

import showroom.model.InvoiceDetail;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import showroom.util.DatabaseConnection;

public class InvoiceDetailDAO {
    private double unitPrice;

public void setUnitPrice(double unitPrice) {
    this.unitPrice = unitPrice;
}

public double getUnitPrice() {
    return unitPrice;
}


    // Thêm một chi tiết hóa đơn vào cơ sở dữ liệu
    public boolean addInvoiceDetail(InvoiceDetail detail) {
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(
             "INSERT INTO invoice_details (invoice_id, car_id, quantity, unit_price) VALUES (?, ?, ?, ?)")) {

        stmt.setInt(1, detail.getInvoiceId());
        stmt.setInt(2, detail.getCarId());
        stmt.setInt(3, detail.getQuantity());
        stmt.setDouble(4, detail.getUnitPrice());

        int affectedRows = stmt.executeUpdate();
        return affectedRows > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


    // Lấy danh sách chi tiết hóa đơn theo invoice_id
    public List<InvoiceDetail> getInvoiceDetailsByInvoiceId(int invoiceId) {
        List<InvoiceDetail> details = new ArrayList<>();
        String sql = "SELECT * FROM invoice_details WHERE invoice_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, invoiceId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                InvoiceDetail detail = new InvoiceDetail();
                detail.setInvoiceDetailId(rs.getInt("invoice_detail_id"));
                detail.setInvoiceId(rs.getInt("invoice_id"));
                detail.setCarId(rs.getInt("car_id"));
                detail.setQuantity(rs.getInt("quantity"));
                detail.setPriceAtSale(rs.getDouble("price_at_sale"));
                details.add(detail);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return details;
    }

    // Xoá toàn bộ chi tiết hóa đơn theo invoice_id
    public boolean deleteByInvoiceId(int invoiceId) {
        String sql = "DELETE FROM invoice_details WHERE invoice_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, invoiceId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Cập nhật số lượng của một chi tiết hóa đơn
    public boolean updateQuantity(int invoiceDetailId, int newQuantity) {
        String sql = "UPDATE invoice_details SET quantity = ? WHERE invoice_detail_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newQuantity);
            stmt.setInt(2, invoiceDetailId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
