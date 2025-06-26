package showroom.view;

import showroom.DAO.CarDAO;
import showroom.model.Car;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class AddCarDialog extends JDialog {

    private JTextField txtName, txtManufacturer, txtYear, txtColor, txtModel, txtPrice, txtQuantity;
    private JTextArea txtDescription;
    private JLabel lblImagePath;
    private String selectedImagePath = "";

    private final CarDAO carDAO = new CarDAO();

    public AddCarDialog(Frame parent) {
        super(parent, "Thêm Xe Mới", true);
        initComponents();
        this.setLocationRelativeTo(parent);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(10, 2, 10, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtName = new JTextField();
        txtManufacturer = new JTextField();
        txtYear = new JTextField();
        txtColor = new JTextField();
        txtModel = new JTextField();
        txtPrice = new JTextField();
        txtQuantity = new JTextField();
        txtDescription = new JTextArea(3, 20);
        lblImagePath = new JLabel("Chưa chọn ảnh");

        JButton btnChooseImage = new JButton("Chọn Ảnh");
        btnChooseImage.addActionListener(e -> chooseImage());

        JButton btnSave = new JButton("Lưu");
        btnSave.addActionListener(e -> saveCar());

        JButton btnCancel = new JButton("Hủy", null);
        btnCancel.addActionListener(e -> dispose());

        panel.add(new JLabel("Tên xe:"));
        panel.add(txtName);
        panel.add(new JLabel("Hãng SX:"));
        panel.add(txtManufacturer);
        panel.add(new JLabel("Năm SX:"));
        panel.add(txtYear);
        panel.add(new JLabel("Màu sắc:"));
        panel.add(txtColor);
        panel.add(new JLabel("Kiểu dáng:"));
        panel.add(txtModel);
        panel.add(new JLabel("Giá bán:"));
        panel.add(txtPrice);
        panel.add(new JLabel("Số lượng:"));
        panel.add(txtQuantity);
        panel.add(new JLabel("Mô tả:"));
        panel.add(new JScrollPane(txtDescription));
        panel.add(new JLabel("Ảnh xe:"));
        panel.add(lblImagePath);
        panel.add(btnChooseImage);
        panel.add(btnSave);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.add(btnCancel);

        this.add(panel, BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);
        this.pack();
    }

    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn ảnh xe");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            selectedImagePath = file.getAbsolutePath();
            lblImagePath.setText(file.getName());
        }
    }

    private void saveCar() {
        try {
            Car car = new Car();
            car.setCarName(txtName.getText().trim());
            car.setManufacturer(txtManufacturer.getText().trim());
            car.setYearOfManufacture(Integer.parseInt(txtYear.getText().trim()));
            car.setColor(txtColor.getText().trim());
            car.setModelType(txtModel.getText().trim());
            car.setSellingPrice(Double.parseDouble(txtPrice.getText().trim()));
            car.setQuantityInStock(Integer.parseInt(txtQuantity.getText().trim()));
            car.setDescription(txtDescription.getText().trim());
            car.setImagePath(selectedImagePath); // ảnh được set ở đây

            boolean success = carDAO.addCar(car);
            if (success) {
                JOptionPane.showMessageDialog(this, "Thêm xe thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm xe thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddCarDialog(null).setVisible(true));
    }
}
