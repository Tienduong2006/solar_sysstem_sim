package com.planet;

import com.planet.database.DBConnection;

public class TestPairB {
    public static void main(String[] args) {
        // --- 1. TEST DATABASE ---
        System.out.println("--- TEST DATABASE ---");
        // Gọi hàm để tạo file .db và tạo bảng
        DBConnection.initializeDatabase();

        // --- 2. TEST TOÁN HỌC KHÔNG GIAN ---
        System.out.println("\n--- TEST CHUYỂN ĐỔI TỌA ĐỘ ---");
        
        // Giả lập dữ liệu đầu vào (Ví dụ: Vệ tinh bay trên bầu trời Đà Nẵng)
        double radius = 6371.0;     // Bán kính Trái Đất (R) tính bằng km
        double altitude = 400.0;    // Độ cao của vệ tinh (h) tính bằng km
        double lat = 16.0544;       // Vĩ độ Đà Nẵng
        double lon = 108.2022;      // Kinh độ Đà Nẵng

        // BẮT BUỘC: Đổi từ độ (Degree) sang Radian vì Math.cos và Math.sin trong Java dùng Radian
        double latRad = Math.toRadians(lat);
        double lonRad = Math.toRadians(lon);

        // Tính khoảng cách r từ tâm hành tinh tới vệ tinh
        double r = radius + altitude;

        // Áp dụng công thức chuyển đổi
        double x = r * Math.cos(latRad) * Math.cos(lonRad);
        double y = r * Math.cos(latRad) * Math.sin(lonRad);
        double z = r * Math.sin(latRad);

        System.out.printf("Tọa độ đầu vào: Vĩ độ %.4f°, Kinh độ %.4f°, Độ cao %.1f km\n", lat, lon, altitude);
        System.out.printf("=> Tọa độ 3D (x, y, z): (%.2f, %.2f, %.2f)\n", x, y, z);
    }
}