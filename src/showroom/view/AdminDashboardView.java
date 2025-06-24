package showroom.view;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import showroom.model.Car;
import showroom.DAO.CarDAO;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import showroom.view.ThongKe;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;


public class AdminDashboardView extends javax.swing.JFrame {

    private CarDAO carDAO;
    private DefaultTableModel tableModel;
    private JTable tblCars;

    public AdminDashboardView() {
        initComponents();
        this.setTitle("Hệ thống Admin - Quản trị toàn diện");
        this.setLocationRelativeTo(null);

        carDAO = new CarDAO();
        tableModel = (DefaultTableModel) tblCars.getModel();

        tableModel.setColumnIdentifiers(new Object[]{
            "ID", "Tên Xe", "Hãng Sản Xuất", "Năm SX", "Màu Sắc", "Kiểu Dáng", "Giá Bán", "Số Lượng Tồn", "Mô Tả",
        });

        loadDataToTable();
    }

    private void loadDataToTable() {
        try {
            tableModel.setRowCount(0);
            List<Car> carList = carDAO.getAllCars();

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
            e.printStackTrace();
        }
    }

    private void initComponents() {
        JPanel jPanel1 = new JPanel();
        JLabel jLabel1 = new JLabel("       HỆ THỐNG QUẢN TRỊ ADMIN - TOÀN QUYỀN");
        JLabel jLabel2 = new JLabel("CHỨC NĂNG ADMIN");
        JLabel jLabel3 = new JLabel("                 QUẢN LÝ KHO XE");
        JLabel jLabel4 = new JLabel("Admin có toàn quyền quản trị hệ thống");

        tblCars = new JTable(new DefaultTableModel());
        JScrollPane jScrollPanel1 = new JScrollPane(tblCars);

        JButton btnAddCar = new JButton("Thêm xe");
        JButton btnEditCar = new JButton("Sửa Xe");
        JButton btnDeleteCar = new JButton("Xoá Xe");
        JButton btnQuanLyNguoiDung = new JButton("Quản lí người dùng");
        JButton btnQuanLyNhanVien = new JButton("Quản lý nhân viên");
        JButton btnThongKe = new JButton("Thống kê & Báo cáo");
        JButton btnBackup = new JButton("Sao lưu hệ thống");

        btnAddCar.addActionListener(evt -> {
    // Mở dialog thêm xe
    AddCarDialog dialog = new AddCarDialog(this);
dialog.setVisible(true);


    // Sau khi đóng dialog, tải lại bảng
    loadDataToTable();
});


        btnEditCar.addActionListener(evt -> {
    // 1. Lấy chỉ số của dòng đang được chọn trong bảng
    int selectedRow = tblCars.getSelectedRow();

    // 2. Kiểm tra xem người dùng đã chọn dòng nào chưa
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một xe để sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return; // Dừng lại nếu chưa chọn
    }

    try {
        // 3. Lấy ID của xe từ cột đầu tiên (cột 0) của dòng đã chọn
        int carIdToEdit = (int) tableModel.getValueAt(selectedRow, 0);

        // 4. Dùng DAO để lấy thông tin đầy đủ của xe từ CSDL
        Car carToEdit = carDAO.getCarById(carIdToEdit); 

        // 5. Kiểm tra xem có lấy được thông tin xe không
        if (carToEdit != null) {
            // Tạo dialog sửa xe
            EditCarDialog editDialog = new EditCarDialog(this, true);
            
            // 6. Nạp dữ liệu của xe cần sửa vào dialog (BƯỚC QUAN TRỌNG NHẤT)
            editDialog.loadCarData(carToEdit);
            
            // 7. Hiển thị dialog đã có sẵn thông tin
            editDialog.setVisible(true);
            
            // 8. Sau khi dialog đóng, tải lại dữ liệu mới cho bảng
            loadDataToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin xe để sửa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi chuẩn bị sửa xe: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }});


        btnDeleteCar.addActionListener(evt -> {
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
        int carId = (int) tableModel.getValueAt(selectedRow, 0); // Lấy car_id từ dòng được chọn (cột 0)
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
});


        btnQuanLyNguoiDung.addActionListener(evt -> {
    UserManagementPanel userPanel = new UserManagementPanel(); // giả sử bạn đã có panel này
    JFrame frame = new JFrame("Quản lý người dùng");
    frame.setContentPane(userPanel);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
});

        btnQuanLyNhanVien.addActionListener(evt -> {
    CreateStaffDialog dialog = new CreateStaffDialog(this, true);
    dialog.setVisible(true);
    loadDataToTable(); // nếu cần làm mới bảng
});


        btnThongKe.addActionListener(evt -> {
    ThongKe thongKe = new ThongKe(this, true);
thongKe.setVisible(true);

});



btnBackup.addActionListener(evt -> {
    String projectDir = System.getProperty("user.dir");
    
    // Đường dẫn đến CSDL nguồn
    String sourceDBUrl = "jdbc:sqlite:" + projectDir + File.separator + "showroom_db.db";
    
    // Tạo tên tệp sao lưu duy nhất với ngày giờ để không bị ghi đè
    String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
    String backupFilePath = projectDir + File.separator + "backup_showroom_db_" + timestamp + ".db";

    try (Connection conn = DriverManager.getConnection(sourceDBUrl)) {
        try (Statement stmt = conn.createStatement()) {
            // Sử dụng lệnh BACKUP gốc của SQLite, đây là cách làm đúng
            stmt.executeUpdate("BACKUP TO '" + backupFilePath + "'");
            
            JOptionPane.showMessageDialog(this, 
                "Sao lưu thành công!\n\nTệp đã được lưu tại:\n" + backupFilePath,
                "Thành công",
                JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (SQLException e) {
        // Bắt lỗi và hiển thị thông báo cụ thể
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, 
            "Lỗi khi sao lưu cơ sở dữ liệu:\n" + e.getMessage(), 
            "Lỗi", 
            JOptionPane.ERROR_MESSAGE);
    }
});




        GroupLayout layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(layout);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup()
                    .addComponent(jLabel2)
                    .addComponent(btnAddCar)
                    .addComponent(btnEditCar)
                    .addComponent(btnDeleteCar)
                    .addComponent(btnQuanLyNguoiDung)
                    .addComponent(btnQuanLyNhanVien)
                    .addComponent(btnThongKe)
                    .addComponent(btnBackup)
                    .addComponent(jLabel4))
                .addGap(20)
                .addGroup(layout.createParallelGroup()
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPanel1, 700, 700, 700))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(10)
                .addGroup(layout.createParallelGroup()
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addComponent(btnAddCar)
                        .addComponent(btnEditCar)
                        .addComponent(btnDeleteCar)
                        .addComponent(btnQuanLyNguoiDung)
                        .addComponent(btnQuanLyNhanVien)
                        .addComponent(btnThongKe)
                        .addComponent(btnBackup)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addComponent(jScrollPanel1, 240, 240, 240)))
        );

        add(jPanel1);  
        pack();       
    }
}
