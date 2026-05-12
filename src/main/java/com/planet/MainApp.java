package com.planet;

import com.planet.Model.Satellite;
import com.planet.database.SatelliteDAO;
import com.planet.physics.MathPhysicsLogic;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

import java.util.List;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Tạo khối cầu Trái Đất (Bán kính 150)
        double earthRadius = 150.0;
        Sphere earth = new Sphere(earthRadius);
        
        // Tọa độ tâm Trái Đất giữa màn hình
        double centerX = 400.0;
        double centerY = 300.0;
        earth.setTranslateX(centerX);
        earth.setTranslateY(centerY);

        Group root = new Group(earth);

        // --- TÍCH HỢP PAIR B: LẤY DỮ LIỆU VÀ VẼ VỆ TINH ---
        SatelliteDAO dao = new SatelliteDAO();
        // Giả sử lấy toàn bộ vệ tinh của Trái Đất (planet_id = 1)
        List<Satellite> satellites = dao.getSatellitesByPlanet(1); 

        for (Satellite s : satellites) {
            // Đưa vào máy tính toán để lấy tọa độ (x, y, z)
            // Lưu ý: đưa đúng bán kính Trái Đất đã thiết lập trên màn hình
            double[] pos3D = MathPhysicsLogic.convertGeoTo3D(earthRadius, s.getAltitude(), s.getLatitude(), s.getLongitude());
            
            // Tạo vệ tinh là một khối cầu nhỏ
            Sphere satSphere = new Sphere(3);
            
            // Đẩy vệ tinh ra đúng vị trí xoay quanh tâm Trái Đất
            satSphere.setTranslateX(centerX + pos3D[0]);
            satSphere.setTranslateY(centerY - pos3D[1]); // Màn hình máy tính trục Y đi xuống nên thường phải đảo dấu
            satSphere.setTranslateZ(pos3D[2]);
            
            // Thêm vệ tinh vào không gian
            root.getChildren().add(satSphere);
        }

        // 3. Tạo không gian vũ trụ
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        primaryStage.setTitle("Mô phỏng Hệ Mặt Trời - Đã tích hợp DB và Toán học");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}