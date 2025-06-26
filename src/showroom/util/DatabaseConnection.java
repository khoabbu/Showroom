package showroom.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    // Đường dẫn đến tệp cơ sở dữ liệu SQLite của bạn
    private static final String URL = "jdbc:sqlite:showroom_db.db";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC"); // Trình điều khiển cho SQLite
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("SQLite JDBC Driver not found.");
        }
        return DriverManager.getConnection(URL); // SQLite không cần tên người dùng/mật khẩu cho tệp cơ sở dữ liệu
    }

    public static void initializeDatabase() {
        String[] sqlStatements = {
            "CREATE TABLE IF NOT EXISTS Users (" +
            "user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username TEXT NOT NULL UNIQUE," +
            "password TEXT NOT NULL," +
            "full_name TEXT," +
            "role TEXT NOT NULL," +
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            ");",

            "CREATE TABLE IF NOT EXISTS Cars (" +
            "car_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "car_name TEXT NOT NULL," +
            "manufacturer TEXT," +
            "year_of_manufacture INTEGER," +
            "color TEXT," +
            "model_type TEXT," +
            "selling_price REAL," +
            "quantity_in_stock INTEGER," +
            "description TEXT" +
            ");",

            "CREATE TABLE IF NOT EXISTS Customers (" +
            "customer_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "full_name TEXT NOT NULL," +
            "address TEXT," +
            "phone_number TEXT NOT NULL," +
            "email TEXT" +
            ");",

            "CREATE TABLE IF NOT EXISTS Invoices (" +
            "invoice_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "customer_id INTEGER NOT NULL," +
            "user_id INTEGER," +
            "invoice_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
            "total_amount REAL," +
            "FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)," +
            "FOREIGN KEY (user_id) REFERENCES Users(user_id)" +
            ");",

            "CREATE TABLE IF NOT EXISTS InvoiceDetail (" +
            "invoice_detail_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "invoice_id INTEGER NOT NULL," +
            "car_id INTEGER NOT NULL," +
            "quantity INTEGER NOT NULL," +
            "price_at_sale REAL NOT NULL," +
            "FOREIGN KEY (invoice_id) REFERENCES Invoices(invoice_id)," +
            "FOREIGN KEY (car_id) REFERENCES Cars(car_id)" +
            ");",

            // Tùy chọn: Chèn người dùng admin ban đầu nếu chưa tồn tại
            "INSERT OR IGNORE INTO Users (username, password, full_name, role) VALUES ('admin', 'admin', 'Administrator', 'admin');"
        };

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            for (String sql : sqlStatements) {
                stmt.execute(sql);
            }
            System.out.println("Cơ sở dữ liệu đã được khởi tạo thành công hoặc các bảng đã tồn tại.");
        } catch (SQLException e) {
            System.err.println("Lỗi khi khởi tạo cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}