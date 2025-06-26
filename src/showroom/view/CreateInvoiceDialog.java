package showroom.view;

import showroom.DAO.InvoiceDAO;
import showroom.DAO.InvoiceDetailDAO;
import showroom.DAO.CustomerDAO;
import showroom.DAO.CarDAO;
import showroom.model.Car;
import showroom.model.Customers;
import showroom.model.InvoiceDetail;
import showroom.model.Invoices;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class CreateInvoiceDialog extends JDialog {

    private JComboBox<Customers> cbCustomers;
    private JComboBox<Car> cbCars;
    private JTextField txtQuantity;
    private JButton btnCreateInvoice;

    private InvoiceDAO invoiceDAO;
    private InvoiceDetailDAO invoiceDetailDAO;
    private CustomerDAO customerDAO;
    private CarDAO carDAO;

    public CreateInvoiceDialog(JFrame parent) {
        super(parent, "Tạo Hóa Đơn", true);
        setLayout(new GridLayout(4, 2, 10, 10));

        invoiceDAO = new InvoiceDAO();
        invoiceDetailDAO = new InvoiceDetailDAO();
        customerDAO = new CustomerDAO();
        carDAO = new CarDAO();

        cbCustomers = new JComboBox<>(new Vector<>(customerDAO.getAllCustomers()));
        cbCars = new JComboBox<>(new Vector<>(carDAO.getAllCars()));
        txtQuantity = new JTextField();
        btnCreateInvoice = new JButton("Xác nhận");

        add(new JLabel("Khách hàng:"));
        add(cbCustomers);
        add(new JLabel("Xe bán:"));
        add(cbCars);
        add(new JLabel("Số lượng:"));
        add(txtQuantity);
        add(new JLabel());
        add(btnCreateInvoice);

        btnCreateInvoice.addActionListener(e -> handleCreateInvoice());

        pack();
        setLocationRelativeTo(parent);
    }

    private void handleCreateInvoice() {
    try {
        Customers selectedCustomer = (Customers) cbCustomers.getSelectedItem();
        Car selectedCar = (Car) cbCars.getSelectedItem();
        String quantityText = txtQuantity.getText().trim();

        if (selectedCustomer == null || selectedCar == null || quantityText.isEmpty()) {
            throw new IllegalArgumentException("Vui lòng nhập đầy đủ thông tin.");
        }

        int quantity = Integer.parseInt(quantityText);
        if (quantity <= 0) {
            throw new IllegalArgumentException("Số lượng phải lớn hơn 0.");
        }

        if (quantity > selectedCar.getQuantityInStock()) {
            throw new IllegalArgumentException("Số lượng vượt quá tồn kho.");
        }

        // --- Tạo hóa đơn ---
        Invoices invoice = new Invoices();
        invoice.setCustomerId(selectedCustomer.getCustomerId());
        invoice.setInvoiceDate(new java.sql.Date(System.currentTimeMillis()));
        int invoiceId = invoiceDAO.insertInvoice(invoice);

        // --- Tạo chi tiết hóa đơn ---
        InvoiceDetail detail = new InvoiceDetail();
        detail.setInvoiceId(invoiceId);
        detail.setCarId(selectedCar.getId());
        detail.setQuantity(quantity);
        detail.setUnitPrice(selectedCar.getSellingPrice());

        boolean success = invoiceDetailDAO.addInvoiceDetail(detail);

        if (success) {
            // --- Trừ tồn kho ---
            boolean updated = carDAO.reduceCarStock(selectedCar.getId(), quantity);

            if (updated) {
                JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công và đã cập nhật tồn kho!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công, nhưng không cập nhật được tồn kho!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            throw new Exception("Không thể thêm chi tiết hóa đơn.");
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.WARNING_MESSAGE);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Lỗi hệ thống: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
}

}
