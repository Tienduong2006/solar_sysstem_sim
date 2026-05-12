package com.planet.database;

import com.planet.Model.Satellite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SatelliteDAO {

    // Hàm 1: Lưu vệ tinh mới vào Database
    public void insertSatellite(Satellite s) {
        String sql = "INSERT INTO satellites(name, longitude, latitude, altitude, planet_id) VALUES(?,?,?,?,?)";

        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, s.getName());
            pstmt.setDouble(2, s.getLongitude());
            pstmt.setDouble(3, s.getLatitude());
            pstmt.setDouble(4, s.getAltitude());
            pstmt.setInt(5, s.getPlanetId());
            
            pstmt.executeUpdate();
            System.out.println("✅ Đã lưu vệ tinh " + s.getName() + " vào CSDL!");
            
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi lưu vệ tinh: " + e.getMessage());
        }
    }

    // Hàm 2: Lấy toàn bộ vệ tinh của một hành tinh để vẽ lên 3D
    public List<Satellite> getSatellitesByPlanet(int planetId) {
        String sql = "SELECT * FROM satellites WHERE planet_id = ?";
        List<Satellite> list = new ArrayList<>();

        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, planetId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Satellite s = new Satellite(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("longitude"),
                    rs.getDouble("latitude"),
                    rs.getDouble("altitude"),
                    rs.getInt("planet_id")
                );
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi tải danh sách vệ tinh: " + e.getMessage());
        }
        return list;
    }
}