
package showroom.view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import showroom.DAO.UserDAO;
import showroom.model.User;


public class LoginView extends javax.swing.JFrame {
    
    private UserDAO userDAO;
    private java.awt.Image backgroundImage; 

  public LoginView() {
        // --- BƯỚC 1: KHỞI TẠO COMPONENT TỪ NETBEANS ---
        initComponents();

        // --- BƯỚC 2: TẠO VÀ THÊM LỚP ẢNH NỀN (KỸ THUẬT MỚI) ---
        try {
            // Tải ảnh nền
            java.net.URL imageUrl = getClass().getResource("/images/background.png");
            if (imageUrl == null) {
                throw new java.io.FileNotFoundException("Không tìm thấy file /images/background.png trong project.");
            }
            ImageIcon icon = new ImageIcon(imageUrl);

            // Tạo một JLabel để giữ ảnh nền
            JLabel backgroundLabel = new JLabel();
            backgroundLabel.setIcon(icon);
            
            // Lấy JLayeredPane và thêm JLabel vào lớp dưới cùng
            getLayeredPane().add(backgroundLabel, Integer.valueOf(Integer.MIN_VALUE));
            
            // Lấy content pane và làm nó trong suốt
            // Dòng này rất quan trọng
            ((javax.swing.JPanel)getContentPane()).setOpaque(false);

            // Thêm một ComponentListener để thay đổi kích thước ảnh nền khi cửa sổ thay đổi
            this.addComponentListener(new java.awt.event.ComponentAdapter() {
                @Override
                public void componentResized(java.awt.event.ComponentEvent evt) {
                    // Căn giữa và thay đổi kích thước ảnh để vừa với cửa sổ
                    Image scaledImage = icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                    backgroundLabel.setIcon(new ImageIcon(scaledImage));
                    backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
                }
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Không thể tải ảnh nền!\n" + e.getMessage() + "\n\nVui lòng kiểm tra lại cấu trúc thư mục.",
                "Lỗi Tải Ảnh Nền",
                JOptionPane.ERROR_MESSAGE);
        }

        // --- BƯỚC 3: TÙY CHỈNH CÁC COMPONENT CÓ SẴN ---
        this.setTitle("Đăng Nhập");
        this.setLocationRelativeTo(null);
        this.userDAO = new UserDAO();

        // Đổi màu chữ của các JLabel thành màu trắng để dễ đọc
     
        lblRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    
    
    @Override
    public void paint(Graphics g) {
        // Vẽ ảnh nền trước
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        // Sau đó mới vẽ các component khác (nút bấm, ô chữ...) đè lên trên
        super.paint(g);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        lblRegister = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("ĐĂNG NHẬP HỆ THỐNG");

        jLabel2.setBackground(new java.awt.Color(255, 51, 51));
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("QUẢN LÝ  SHOWROOM");

        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setText("Tên đăng nhập :");

        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setText("    Mật khẩu :");

        btnLogin.setBackground(new java.awt.Color(0, 255, 102));
        btnLogin.setForeground(new java.awt.Color(255, 51, 51));
        btnLogin.setText("Đăng nhập");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblRegister.setForeground(new java.awt.Color(255, 51, 51));
        lblRegister.setText("    Chưa có tài khoản ? Đăng ký.");
        lblRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRegisterMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 235, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(306, 306, 306))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(354, 354, 354))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogin)
                .addGap(377, 377, 377))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(lblRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String username = txtUsername.getText();
    String password = new String(txtPassword.getPassword());

    if (username.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    User user = userDAO.checkLogin(username, password);

    if (user != null) {
        JOptionPane.showMessageDialog(this, "Đăng nhập thành công! Chào mừng " + user.getFullName(), "Thành công", JOptionPane.INFORMATION_MESSAGE);
        
        // --- PHẦN QUAN TRỌNG NHẤT: KIỂM TRA VAI TRÒ ---
        String role = user.getRole();
        
        // Dùng switch-case để quyết định mở cửa sổ nào
        switch (role) {
            case "admin":
               AdminDashboardView adminView = new AdminDashboardView();
               adminView.setVisible(true);
               break;

            case "staff":
                // Mở giao diện cho Nhân viên (chính là MainDashboardView hiện tại)
                MainDashboardView mainView = new MainDashboardView();
                mainView.setVisible(true);
                break;
                
            case "customer":
                // Mở giao diện cho Khách hàng
                // CustomerView customerView = new CustomerView(user);
                // customerView.setVisible(true);
                CustomerView customerView = new CustomerView();
                customerView.setVisible(true);
                break;
                
            default:
                JOptionPane.showMessageDialog(this, "Vai trò không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; // Không làm gì cả nếu vai trò không xác định
        }
        
        // Đóng cửa sổ đăng nhập sau khi đã mở cửa sổ tương ứng
        this.dispose();
        
    } else {
        JOptionPane.showMessageDialog(this, "Sai tên đăng nhập hoặc mật khẩu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void lblRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegisterMouseClicked
         // Mở cửa sổ đăng ký
        RegisterView registerView = new RegisterView();
        registerView.setVisible(true);
        // Không đóng cửa sổ đăng nhập vội, để người dùng có thể quay lại
    }//GEN-LAST:event_lblRegisterMouseClicked

  

   
    
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblRegister;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
