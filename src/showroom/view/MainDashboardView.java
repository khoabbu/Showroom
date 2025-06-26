package showroom.view;

import showroom.model.Car;
import showroom.DAO.CarDAO;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Giao diện dành cho STAFF - Quyền hạn bị hạn chế
 * Staff chỉ có thể: Xem xe, Tư vấn khách hàng (không thể thêm/sửa/xóa xe)
 */
public class MainDashboardView extends javax.swing.JFrame {

    private CarDAO carDAO;
    private DefaultTableModel tableModel;
    
    public MainDashboardView() {
        initComponents();
        this.setTitle("Hệ thống Staff - Tư vấn bán hàng");
        this.setLocationRelativeTo(null); // Căn giữa cửa sổ

        // Khởi tạo các đối tượng cần thiết
        carDAO = new CarDAO();
        tableModel = (DefaultTableModel) tblCars.getModel();

        // Đặt tên cho các cột của bảng
        tableModel.setColumnIdentifiers(new Object[]{
            "ID", "Tên Xe", "Hãng Sản Xuất", "Năm SX", "Màu Sắc","Kiểu Dáng","Giá Bán", "Số Lượng Tồn","Mô Tả",
        });

        // Gọi phương thức để tải dữ liệu lên bảng
        loadDataToTable();
    }
    
    private void loadDataToTable() {
        try {
            // Xóa tất cả các hàng cũ trong bảng
            tableModel.setRowCount(0);

            // Lấy danh sách xe từ CSDL
            List<Car> carList = carDAO.getAllCars();

            // Duyệt qua danh sách và thêm từng xe vào bảng
            for (Car car : carList) {
                tableModel.addRow(new Object[]{
                    car.getId(),
                    car.getCarName(),
                    car.getManufacturer(),
                    car.getYearOfManufacture(),
                    car.getColor(),
                    car.getModelType(),
                    car.getSellingPrice(),
                    car.getQuantityInStock(),
                    car.getDescription()
                });
            }
        } catch (Exception e) {
            // In lỗi ra console nếu có vấn đề
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPanel1 = new javax.swing.JScrollPane();
        tblCars = new javax.swing.JTable();
        btnViewCarDetails = new javax.swing.JButton();
        btnQuanLyKhachHang = new javax.swing.JButton();
        btnTaoHoaDon = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("       HỆ THỐNG TƯ VẤN BÁN HÀNG - STAFF");

        tblCars.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPanel1.setViewportView(tblCars);

        btnViewCarDetails.setText("Xem chi tiết xe");
        btnViewCarDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewCarDetailsActionPerformed(evt);
            }
        });

        btnQuanLyKhachHang.setText("Tư vấn khách hàng");
        btnQuanLyKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanLyKhachHangActionPerformed(evt);
            }
        });

        btnTaoHoaDon.setText("Tạo hóa đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        jLabel2.setText("CHỨC NĂNG STAFF");

        jLabel3.setText("                 DANH SÁCH XE HIỆN CÓ");

        jLabel4.setText("Lưu ý: Staff chỉ có thể xem và tư vấn, không thể chỉnh sửa thông tin xe");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(294, 294, 294)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(303, 303, 303))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(btnViewCarDetails)
                            .addComponent(btnQuanLyKhachHang)
                            .addComponent(btnTaoHoaDon)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
                        .addGap(17, 17, 17))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jScrollPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(btnViewCarDetails)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuanLyKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTaoHoaDon)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)))
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewCarDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewCarDetailsActionPerformed
        int selectedRow = tblCars.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một chiếc xe để xem chi tiết.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            int carId = Integer.parseInt(tblCars.getValueAt(selectedRow, 0).toString());
            Car car = carDAO.getCarById(carId);
            
            if (car != null) {
                // Hiển thị thông tin chi tiết xe trong dialog chỉ đọc
                String carInfo = "=== THÔNG TIN CHI TIẾT XE ===\n\n" +
                               "Tên xe: " + car.getCarName() + "\n" +
                               "Hãng sản xuất: " + car.getManufacturer() + "\n" +
                               "Năm sản xuất: " + car.getYearOfManufacture() + "\n" +
                               "Màu sắc: " + car.getColor() + "\n" +
                               "Kiểu dáng: " + car.getModelType() + "\n" +
                               "Giá bán: " + car.getSellingPrice() + " VNĐ\n" +
                               "Số lượng tồn: " + car.getQuantityInStock() + "\n" +
                               "Mô tả: " + car.getDescription();
                
                JOptionPane.showMessageDialog(this, carInfo, "Chi tiết xe", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xem chi tiết xe: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnViewCarDetailsActionPerformed

    private void btnQuanLyKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanLyKhachHangActionPerformed
        // Staff chỉ có thể tư vấn khách hàng, không quản lý hệ thống
        JFrame customerFrame = new JFrame("Tư vấn Khách Hàng");
        customerFrame.add(new CustomerManagementPanel());
        customerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        customerFrame.pack();
        customerFrame.setLocationRelativeTo(this);
        customerFrame.setVisible(true);
    }//GEN-LAST:event_btnQuanLyKhachHangActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        CreateInvoiceDialog dialog = new CreateInvoiceDialog(this);
dialog.setVisible(true);

    }//GEN-LAST:event_btnTaoHoaDonActionPerformed
    
    public void refreshCarTableData() {
        loadDataToTable();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainDashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainDashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainDashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainDashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainDashboardView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnViewCarDetails;
    private javax.swing.JButton btnQuanLyKhachHang;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPanel1;
    private javax.swing.JTable tblCars;
    // End of variables declaration//GEN-END:variables
}