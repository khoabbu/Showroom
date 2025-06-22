/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package showroom.view;


import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import showroom.DAO.CarDAO;
import showroom.model.Car;


public class CarManagementPanel extends javax.swing.JPanel {
     private CarDAO carDAO;
    private DefaultTableModel tableModel;
    
    public CarManagementPanel() {
       initComponents(); // Dòng này NetBeans tự tạo
        
        // --- KHỞI TẠO CÁC ĐỐI TƯỢNG VÀ TẢI DỮ LIỆU ---
        carDAO = new CarDAO();
        tableModel = (DefaultTableModel) tblCars.getModel();

        // Đặt tên cho các cột của bảng
        tableModel.setColumnIdentifiers(new Object[]{
            "ID", "Tên Xe", "Hãng Sản Xuất", "Năm SX", "Màu Sắc", "Kiểu Dáng", "Giá Bán", "Số Lượng Tồn", "Mô Tả",
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

        btnAddCar = new javax.swing.JButton();
        btnEditCar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPanel1 = new javax.swing.JScrollPane();
        tblCars = new javax.swing.JTable();
        btnDeleteCar = new javax.swing.JButton();

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

        jButton1.setText("Quản lí khách hàng");

        jButton2.setText("Thống kê");

        jLabel2.setText("CHỨC NĂNG");

        jLabel3.setText("                 KHU VỰC HIỂN THỊ");

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

        btnDeleteCar.setText("Xoá Xe");
        btnDeleteCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jScrollPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton1)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(4, 4, 4)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(btnDeleteCar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnEditCar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnAddCar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))))))
                    .addGap(235, 235, 235)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(176, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(jScrollPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(64, 64, 64)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(64, 64, 64)
                    .addComponent(btnAddCar)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnEditCar)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnDeleteCar)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton2)
                    .addContainerGap(122, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents
    
   
    
    
    private void btnAddCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCarActionPerformed
        AddCarDialog addCarDialog = new AddCarDialog(null);
        addCarDialog.setVisible(true);
        loadDataToTable();
    }//GEN-LAST:event_btnAddCarActionPerformed

    private void btnEditCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCarActionPerformed
       int selectedRow = tblCars.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một chiếc xe để sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int carIdToEdit = Integer.parseInt(tblCars.getValueAt(selectedRow, 0).toString());
            Car carToEdit = carDAO.getCarById(carIdToEdit); 

            if (carToEdit != null) {
                EditCarDialog editDialog = new EditCarDialog(null, true);
                editDialog.loadCarData(carToEdit);
                editDialog.setVisible(true);
                loadDataToTable();
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin xe để sửa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi chuẩn bị sửa xe: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEditCarActionPerformed

    private void btnDeleteCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCarActionPerformed
       int selectedRow = tblCars.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một chiếc xe để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return; 
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "Bạn có chắc chắn muốn xóa chiếc xe này không?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            int carIdToDelete = (int) tblCars.getValueAt(selectedRow, 0);
            carDAO.deleteCar(carIdToDelete); 
            JOptionPane.showMessageDialog(this, "Đã xóa xe thành công!");
            loadDataToTable(); 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xóa xe: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDeleteCarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCar;
    private javax.swing.JButton btnDeleteCar;
    private javax.swing.JButton btnEditCar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPanel1;
    private javax.swing.JTable tblCars;
    // End of variables declaration//GEN-END:variables
}
