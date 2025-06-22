package showroom.DAO;

import showroom.model.Car;
import showroom.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    public boolean reduceCarStock(int carId, int quantity) {
        String sql = "UPDATE Cars SET quantity_in_stock = quantity_in_stock - ? WHERE car_id = ? AND quantity_in_stock >= ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quantity);
            stmt.setInt(2, carId);
            stmt.setInt(3, quantity);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addCar(Car car) {
        String sql = "INSERT INTO Cars (car_name, manufacturer, year_of_manufacture, color, model_type, selling_price, quantity_in_stock, description, image_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, car.getCarName());
            pstmt.setString(2, car.getManufacturer());
            pstmt.setInt(3, car.getYearOfManufacture());
            pstmt.setString(4, car.getColor());
            pstmt.setString(5, car.getModelType());
            pstmt.setDouble(6, car.getSellingPrice());
            pstmt.setInt(7, car.getQuantityInStock());
            pstmt.setString(8, car.getDescription());
            pstmt.setString(9, car.getImagePath());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        car.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm xe: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT car_id, car_name, manufacturer, year_of_manufacture, color, model_type, selling_price, quantity_in_stock, description, image_path FROM Cars";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt("car_id"));
                car.setCarName(rs.getString("car_name"));
                car.setManufacturer(rs.getString("manufacturer"));
                car.setYearOfManufacture(rs.getInt("year_of_manufacture"));
                car.setColor(rs.getString("color"));
                car.setModelType(rs.getString("model_type"));
                car.setSellingPrice(rs.getDouble("selling_price"));
                car.setQuantityInStock(rs.getInt("quantity_in_stock"));
                car.setDescription(rs.getString("description"));
                car.setImagePath(rs.getString("image_path")); // << Thêm dòng này

                cars.add(car);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách xe: " + e.getMessage());
            e.printStackTrace();
        }
        return cars;
    }

    public boolean updateCar(Car car) {
        String sql = "UPDATE Cars SET car_name = ?, manufacturer = ?, year_of_manufacture = ?, color = ?, model_type = ?, selling_price = ?, quantity_in_stock = ?, description = ?, image_path = ? WHERE car_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, car.getCarName());
            pstmt.setString(2, car.getManufacturer());
            pstmt.setInt(3, car.getYearOfManufacture());
            pstmt.setString(4, car.getColor());
            pstmt.setString(5, car.getModelType());
            pstmt.setDouble(6, car.getSellingPrice());
            pstmt.setInt(7, car.getQuantityInStock());
            pstmt.setString(8, car.getDescription());
            pstmt.setString(9, car.getImagePath()); // << Thêm dòng này
            pstmt.setInt(10, car.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật xe: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCar(int carId) {
        String sql = "DELETE FROM Cars WHERE car_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, carId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa xe: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public Car getCarById(int carId) {
        String sql = "SELECT car_id, car_name, manufacturer, year_of_manufacture, color, model_type, selling_price, quantity_in_stock, description, image_path FROM Cars WHERE car_id = ?";
        Car car = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, carId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    car = new Car();
                    car.setId(rs.getInt("car_id"));
                    car.setCarName(rs.getString("car_name"));
                    car.setManufacturer(rs.getString("manufacturer"));
                    car.setYearOfManufacture(rs.getInt("year_of_manufacture"));
                    car.setColor(rs.getString("color"));
                    car.setModelType(rs.getString("model_type"));
                    car.setSellingPrice(rs.getDouble("selling_price"));
                    car.setQuantityInStock(rs.getInt("quantity_in_stock"));
                    car.setDescription(rs.getString("description"));
                    car.setImagePath(rs.getString("image_path")); // << Thêm dòng này
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy chi tiết xe: " + e.getMessage());
            e.printStackTrace();
        }
        return car;
    }
}
