package com.student.satellite;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SatelliteDAO {

    public void addSatellite(Satellite s) throws SQLException {
        String sql = "INSERT INTO satellites(name, country, orbit_type, launch_date, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getCountry());
            ps.setString(3, s.getOrbitType());
            ps.setDate(4, Date.valueOf(s.getLaunchDate()));
            ps.setString(5, s.getStatus());
            ps.executeUpdate();
        }
    }

    public List<Satellite> getAllSatellites() throws SQLException {
        List<Satellite> list = new ArrayList<>();
        String sql = "SELECT * FROM satellites ORDER BY id";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Satellite(
                    rs.getInt("id"), rs.getString("name"), rs.getString("country"),
                    rs.getString("orbit_type"), rs.getDate("launch_date").toString(),
                    rs.getString("status")));
            }
        }
        return list;
    }

    public boolean updateSatellite(int id, String status) throws SQLException {
        String sql = "UPDATE satellites SET status = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteSatellite(int id) throws SQLException {
        String sql = "DELETE FROM satellites WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public Satellite findSatellite(int id) throws SQLException {
        String sql = "SELECT * FROM satellites WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Satellite(rs.getInt("id"), rs.getString("name"),
                        rs.getString("country"), rs.getString("orbit_type"),
                        rs.getDate("launch_date").toString(), rs.getString("status"));
                }
            }
        }
        return null;
    }
}
