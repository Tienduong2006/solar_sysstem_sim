package com.planet.physics;

public class MathPhysicsLogic {

    // Hằng số hấp dẫn (Giả lập một số để test, trong thực tế là 6.67430e-11)
    private static final double G = 6.67430e-11; 

    /**
     * 1. Tính vận tốc vũ trụ (Orbital Velocity)
     * Công thức: v = sqrt(G * M / r)
     */
    public static double calculateOrbitalVelocity(double mass, double radius, double altitude) {
        // r = khoảng cách từ tâm hành tinh (bán kính + độ cao)
        double r = radius + altitude; 
        
        // Tránh chia cho 0 hoặc căn bậc 2 của số âm
        if (r <= 0 || mass <= 0) return 0.0;
        
        return Math.sqrt((G * mass) / r);
    }

    /**
     * 2. Chuyển đổi tọa độ Địa lý -> Tọa độ Không gian 3D (x, y, z)
     */
    public static double[] convertGeoTo3D(double radius, double altitude, double latitude, double longitude) {
        // Đổi từ độ sang Radian để dùng hàm lượng giác trong Java
        double latRad = Math.toRadians(latitude);
        double lonRad = Math.toRadians(longitude);
        
        // Khoảng cách từ tâm
        double r = radius + altitude;

        // Công thức chuyển đổi:
        // x = r * cos(lat) * cos(lon)
        // y = r * cos(lat) * sin(lon)
        // z = r * sin(lat)
        double x = r * Math.cos(latRad) * Math.cos(lonRad);
        double y = r * Math.cos(latRad) * Math.sin(lonRad);
        double z = r * Math.sin(latRad);

        return new double[]{x, y, z};
    }
}