package showroom.view;

import showroom.DAO.InvoiceDAO;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;

public class ThongKe extends JDialog {

    private JLabel lblTotalRevenue;
    private JLabel lblTotalInvoices;
    private JTable tblMonthlyRevenue;
    private DefaultTableModel tableModel;

    public ThongKe(Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Thống kê doanh thu");
        initComponents();
        loadStatistics();
    }

    private void initComponents() {
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // Tổng quan doanh thu
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        lblTotalRevenue = new JLabel("Tổng doanh thu: 0 VNĐ", JLabel.CENTER);
        lblTotalInvoices = new JLabel("Tổng số hóa đơn: 0", JLabel.CENTER);
        lblTotalRevenue.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotalInvoices.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(lblTotalRevenue);
        topPanel.add(lblTotalInvoices);

        // Bảng doanh thu theo tháng
        String[] columns = {"Tháng", "Năm", "Doanh thu (VNĐ)"};
        tableModel = new DefaultTableModel(columns, 0);
        tblMonthlyRevenue = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblMonthlyRevenue);

        // Nút đóng
        JButton btnClose = new JButton("Đóng");
        btnClose.addActionListener(e -> dispose());
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnClose);

        // Thêm vào panel chính
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(panel);  // Đảm bảo hiển thị
    }

    private void loadStatistics() {
        try {
            System.out.println("Đang load thống kê...");

            InvoiceDAO dao = new InvoiceDAO();

            double totalRevenue = dao.getTotalRevenue();
            int totalInvoices = dao.getTotalInvoices();

            lblTotalRevenue.setText("Tổng doanh thu: " + String.format("%,.0f", totalRevenue) + " VNĐ");
            lblTotalInvoices.setText("Tổng số hóa đơn: " + totalInvoices);

            for (Object[] row : dao.getRevenueByMonth()) {
                tableModel.addRow(new Object[]{
                    row[0], row[1], String.format("%,.0f", (double) row[2]) + " VNĐ"
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi load thống kê: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
