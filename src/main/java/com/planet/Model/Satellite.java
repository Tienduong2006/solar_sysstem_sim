package com.planet.Model;

public class Satellite {
    private int id;
    private String name;
    private double longitude;
    private double latitude;
    private double altitude;
    private int planetId;

    // Constructor đầy đủ
    public Satellite(int id, String name, double longitude, double latitude, double altitude, int planetId) {
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.planetId = planetId;
    }

    // Constructor không có ID (dùng khi insert mới vì ID tự tăng)
    public Satellite(String name, double longitude, double latitude, double altitude, int planetId) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.planetId = planetId;
    }

    // --- GETTER & SETTER ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public double getAltitude() { return altitude; }
    public void setAltitude(double altitude) { this.altitude = altitude; }
    public int getPlanetId() { return planetId; }
    public void setPlanetId(int planetId) { this.planetId = planetId; }
}