package showroom.view;

import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import showroom.DAO.CarDAO;
import showroom.model.Car;

public class AdminDashboardView extends javax.swing.JFrame {

    private final CarDAO carDAO;

    //--- BIẾN COMPONENT DO NETBEANS QUẢN LÝ (SẼ ĐƯỢC KHỞI TẠO BÊN DƯỚI) ---
    private javax.swing.JButton btnAddCar;
    private javax.swing.JButton btnBackup;
    private javax.swing.JButton btnDeleteCar;
    private javax.swing.JButton btnEditCar;
    private javax.swing.JButton btnQuanLyNguoiDung;
    private javax.swing.JButton btnQuanLyNhanVien;
    private javax.swing.JButton btnResetSearch;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JTable tblCars;
    private javax.swing.JTextField txtSearch;
    //--- KẾT THÚC KHAI BÁO BIẾN ---

    /**
     * Creates new form AdminDashboardView
     */
    public AdminDashboardView() {
        // 1. Khởi tạo tất cả các thành phần giao diện
        initComponents();
        
        // 2. Khởi tạo các đối tượng và cài đặt cho Frame
        this.carDAO = new CarDAO();
        this.setTitle("Hệ thống Admin - Quản trị toàn diện");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 3. Cài đặt các thuộc tính cho bảng
        setupTable();
        
        // 4. Tải dữ liệu vào bảng lần đầu tiên
        loadDataToTable();
    }

    /**
     * Cài đặt các thuộc tính cho JTable: tên cột, renderer cho ảnh, chiều cao hàng.
     */
    private void setupTable() {
        DefaultTableModel tableModel = (DefaultTableModel) tblCars.getModel();
        tableModel.setColumnIdentifiers(new Object[]{
            "ID", "Tên Xe", "Hãng SX", "Năm SX", "Màu Sắc", "Kiểu Dáng", "Giá Bán", "Số Lượng", "Mô Tả", "Hình Ảnh"
        });
        tblCars.getColumn("Hình Ảnh").setCellRenderer(new ImageTableCellRenderer());
        tblCars.setRowHeight(80);
    }
    
    /**
     * Tải toàn bộ dữ liệu xe từ CSDL và hiển thị lên bảng.
     */
    private void loadDataToTable() {
        List<Car> allCars = carDAO.getAllCars();
        updateTableWithData(allCars);
    }
    
    /**
     * Cập nhật bảng với một danh sách xe cụ thể (dùng cho cả tải tất cả và tìm kiếm).
     * @param carList Danh sách xe cần hiển thị.
     */
    private void updateTableWithData(List<Car> carList) {
        DefaultTableModel tableModel = (DefaultTableModel) tblCars.getModel();
        tableModel.setRowCount(0); // Xóa dữ liệu cũ
        try {
            for (Car car : carList) {
                tableModel.addRow(new Object[]{
                    car.getId(), car.getCarName(), car.getManufacturer(),
                    car.getYearOfManufacture(), car.getColor(), car.getModelType(),
                    car.getSellingPrice(), car.getQuantityInStock(),
                    car.getDescription(), car.getImagePath()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật bảng: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Lớp nội để render đường dẫn ảnh thành hình ảnh hiển thị trong ô của bảng.
     */
    class ImageTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            String imagePath = (value == null) ? "" : value.toString();
            JLabel label = new JLabel();
            if (!imagePath.isEmpty() && new File(imagePath).exists()) {
                ImageIcon imageIcon = new ImageIcon(imagePath);
                Image image = imageIcon.getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(image));
            } else {
                label.setText("No Image");
            }
            label.setHorizontalAlignment(JLabel.CENTER);
            return label;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCars = new javax.swing.JTable();
        btnAddCar = new javax.swing.JButton();
        btnEditCar = new javax.swing.JButton();
        btnDeleteCar = new javax.swing.JButton();
        btnQuanLyNguoiDung = new javax.swing.JButton();
        btnQuanLyNhanVien = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        btnBackup = new javax.swing.JButton();
        searchPanel = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnResetSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HỆ THỐNG QUẢN TRỊ ADMIN - TOÀN QUYỀN");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("CHỨC NĂNG ADMIN");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("QUẢN LÝ KHO XE");

        jLabel4.setText("Admin có toàn quyền quản trị hệ thống");

        tblCars.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblCars);

        btnAddCar.setText("Thêm xe");
        btnAddCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCarActionPerformed(evt);
            }
        });

        btnEditCar.setText("Sửa Xe");
        btnEditCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCarActionPerformed(evt);
            }
        });

        btnDeleteCar.setText("Xoá Xe");
        btnDeleteCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCarActionPerformed(evt);
            }
        });

        btnQuanLyNguoiDung.setText("Quản lý người dùng");
        btnQuanLyNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanLyNguoiDungActionPerformed(evt);
            }
        });

        btnQuanLyNhanVien.setText("Quản lý nhân viên");
        btnQuanLyNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanLyNhanVienActionPerformed(evt);
            }
        });

        btnThongKe.setText("Thống kê & Báo cáo");
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        btnBackup.setText("Sao lưu hệ thống");
        btnBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackupActionPerformed(evt);
            }
        });

        searchPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 2));

        txtSearch.setColumns(25);
        searchPanel.add(txtSearch);

        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        searchPanel.add(btnSearch);

        btnResetSearch.setText("Hiện tất cả");
        btnResetSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetSearchActionPerformed(evt);
            }
        });
        searchPanel.add(btnResetSearch);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnQuanLyNguoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQuanLyNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBackup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteCar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditCar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddCar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
                    .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAddCar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditCar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteCar)
                        .addGap(18, 18, 18)
                        .addComponent(btnQuanLyNguoiDung)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnQuanLyNhanVien)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThongKe)
                        .addGap(18, 18, 18)
                        .addComponent(btnBackup)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCarActionPerformed
        AddCarDialog dialog = new AddCarDialog(this);
        dialog.setVisible(true);
        loadDataToTable();
    }//GEN-LAST:event_btnAddCarActionPerformed

    private void btnEditCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCarActionPerformed
        int selectedRow = tblCars.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một chiếc xe để sửa!", "Chưa chọn xe", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int carId = (int) tblCars.getValueAt(selectedRow, 0);
            Car carToEdit = carDAO.getCarById(carId);

            if (carToEdit == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin xe trong CSDL.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            EditCarDialog editDialog = new EditCarDialog(this, true);
            editDialog.loadCarData(carToEdit);
            editDialog.setVisible(true);
            loadDataToTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi lấy dữ liệu xe: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEditCarActionPerformed

    private void btnDeleteCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCarActionPerformed
        int selectedRow = tblCars.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một xe để xoá.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá xe này?", "Xác nhận xoá", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            int carId = (int) tblCars.getValueAt(selectedRow, 0);
            boolean deleted = carDAO.deleteCar(carId);
            if (deleted) {
                JOptionPane.showMessageDialog(this, "Xoá xe thành công!");
                loadDataToTable();
            } else {
                JOptionPane.showMessageDialog(this, "Không thể xoá xe.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteCarActionPerformed

    private void btnQuanLyNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanLyNguoiDungActionPerformed
        UserManagementPanel userPanel = new UserManagementPanel();
        JFrame frame = new JFrame("Quản lý người dùng");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(userPanel);
        frame.pack();
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
    }//GEN-LAST:event_btnQuanLyNguoiDungActionPerformed

    private void btnQuanLyNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanLyNhanVienActionPerformed
        CreateStaffDialog dialog = new CreateStaffDialog(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnQuanLyNhanVienActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        ThongKe thongKeDialog = new ThongKe(this, true);
        thongKeDialog.setVisible(true);
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackupActionPerformed
        String projectDir = System.getProperty("user.dir");
        String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        String backupFilePath = projectDir + File.separator + "backup_showroom_db_" + timestamp + ".db";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + projectDir + File.separator + "showroom_db.db");
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("BACKUP TO '" + backupFilePath + "'");
            JOptionPane.showMessageDialog(this,
                "Sao lưu thành công!\n\nTệp đã được lưu tại:\n" + backupFilePath,
                "Thành công",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Lỗi khi sao lưu cơ sở dữ liệu:\n" + e.getMessage(),
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBackupActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String searchTerm = txtSearch.getText().trim();
        if (searchTerm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa để tìm kiếm.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        List<Car> searchResult = carDAO.searchCars(searchTerm);
        updateTableWithData(searchResult);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnResetSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetSearchActionPerformed
        txtSearch.setText("");
        loadDataToTable();
    }//GEN-LAST:event_btnResetSearchActionPerformed

}