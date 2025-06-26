package showroom.view;

import showroom.DAO.CarDAO;
import showroom.model.Car;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

public class CustomerView extends JFrame {

    private CarDAO carDAO;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;

    private JTable tblCars;
    private JTextField txtSearch;
    private JButton btnViewDetails;
    private JPanel jPanel1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JScrollPane jScrollPane1;

    public CustomerView() {
        initComponents();
        this.setTitle("Giao diện khách hàng");
        this.setLocationRelativeTo(null);

        carDAO = new CarDAO();
        tableModel = (DefaultTableModel) tblCars.getModel();

        tableModel.setColumnIdentifiers(new Object[]{
                "ID", "Tên Xe", "Hãng SX", "Năm SX", "Màu Sắc",
                "Kiểu Dáng", "Giá Bán", "Số Lượng Tồn", "Mô Tả", "Ảnh"
        });

        sorter = new TableRowSorter<>(tableModel);
        tblCars.setRowSorter(sorter);
        tblCars.setRowHeight(70); // Chiều cao đủ để hiển thị ảnh

        // Cài renderer cho cột cuối cùng là ảnh
        int imageColumnIndex = tblCars.getColumnCount() - 1;
        tblCars.getColumnModel().getColumn(imageColumnIndex).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public void setValue(Object value) {
                if (value instanceof ImageIcon) {
                    setIcon((ImageIcon) value);
                    setText("");
                } else {
                    setIcon(null);
                    setText("Không ảnh");
                }
            }
        });

        loadDataToTable();
        addSearchFunctionality();
    }

    private void loadDataToTable() {
        try {
            tableModel.setRowCount(0);
            List<Car> carList = carDAO.getAllCars();

            for (Car car : carList) {
                if (car.getQuantityInStock() > 0) {
                    ImageIcon icon = null;
                    if (car.getImagePath() != null && !car.getImagePath().isEmpty()) {
                        try {
                            Image img = new ImageIcon(car.getImagePath()).getImage()
                                    .getScaledInstance(80, 60, Image.SCALE_SMOOTH);
                            icon = new ImageIcon(img);
                        } catch (Exception e) {
                            System.err.println("Không thể tải ảnh từ: " + car.getImagePath());
                        }
                    }

                    tableModel.addRow(new Object[]{
                            car.getId(),
                            car.getCarName(),
                            car.getManufacturer(),
                            car.getYearOfManufacture(),
                            car.getColor(),
                            car.getModelType(),
                            car.getSellingPrice(),
                            car.getQuantityInStock(),
                            car.getDescription(),
                            icon
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Lỗi khi tải dữ liệu xe: " + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addSearchFunctionality() {
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { search(txtSearch.getText()); }
            public void removeUpdate(DocumentEvent e) { search(txtSearch.getText()); }
            public void changedUpdate(DocumentEvent e) { search(txtSearch.getText()); }

            private void search(String str) {
                if (str.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                }
            }
        });
    }

    private void btnViewDetailsActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = tblCars.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một chiếc xe để xem chi tiết.",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int modelRow = tblCars.convertRowIndexToModel(selectedRow);
        int carId = (int) tableModel.getValueAt(modelRow, 0);
        Car car = carDAO.getCarById(carId);

        if (car != null) {
            String details = "Tên xe: " + car.getCarName() + "\n"
                    + "Hãng SX: " + car.getManufacturer() + "\n"
                    + "Năm SX: " + car.getYearOfManufacture() + "\n"
                    + "Màu Sắc: " + car.getColor() + "\n"
                    + "Kiểu Dáng: " + car.getModelType() + "\n"
                    + "Giá Bán: " + String.format("%.0f", car.getSellingPrice()) + " VND\n"
                    + "Số Lượng Tồn: " + car.getQuantityInStock() + "\n"
                    + "Mô Tả: " + car.getDescription();
            JOptionPane.showMessageDialog(this, details,
                    "Chi tiết xe: " + car.getCarName(), JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Không tìm thấy thông tin chi tiết xe.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        jPanel1 = new JPanel();
        jLabel1 = new JLabel("DANH SÁCH XE CÓ SẴN", SwingConstants.CENTER);
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel2 = new JLabel("Tìm kiếm:");
        txtSearch = new JTextField(20);
        btnViewDetails = new JButton("Xem chi tiết");
        btnViewDetails.addActionListener(this::btnViewDetailsActionPerformed);

        tblCars = new JTable();
        jScrollPane1 = new JScrollPane(tblCars);

        GroupLayout layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 400, Short.MAX_VALUE)
                    .addComponent(btnViewDetails))
                .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnViewDetails))
                .addGap(10)
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );

        this.setContentPane(jPanel1);
        this.pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CustomerView().setVisible(true));
    }
}