package com.student.satellite;

public class Satellite {
    private final int id;
    private final String name;
    private final String country;
    private final String orbitType;
    private final String launchDate;
    private final String status;

    public Satellite(int id, String name, String country, String orbitType,
                     String launchDate, String status) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.orbitType = orbitType;
        this.launchDate = launchDate;
        this.status = status;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCountry() { return country; }
    public String getOrbitType() { return orbitType; }
    public String getLaunchDate() { return launchDate; }
    public String getStatus() { return status; }
}
