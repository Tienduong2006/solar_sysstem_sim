package com.planet.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    // Đường dẫn tới file SQLite cục bộ (sẽ tự động đẻ ra file database.db trong thư mục gốc)
    private static final String URL = "jdbc:sqlite:database.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("✅ Đã kết nối thành công tới SQLite");
        } catch (SQLException e) {
            System.out.println("❌ Lỗi kết nối CSDL: " + e.getMessage());
        }
        return conn;
    }

    // Hàm khởi tạo cấu trúc bảng (chạy 1 lần lúc bật app)
    public static void initializeDatabase() {
        String sqlPlanets = "CREATE TABLE IF NOT EXISTS planets ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " name TEXT NOT NULL,"
                + " mass REAL,"
                + " radius REAL"
                + ");";

        String sqlSatellites = "CREATE TABLE IF NOT EXISTS satellites ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " name TEXT NOT NULL,"
                + " longitude REAL,"
                + " latitude REAL,"
                + " altitude REAL,"
                + " planet_id INTEGER,"
                + " FOREIGN KEY(planet_id) REFERENCES planets(id)"
                + ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlPlanets);
            stmt.execute(sqlSatellites);
            System.out.println("✅ Đã khởi tạo cấu trúc bảng thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Lỗi tạo bảng: " + e.getMessage());
        }
    }
}