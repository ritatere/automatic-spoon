package com.ait.beauty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class BeautyDAO {

	public List<BeautyTreatment> findAll() {
		List<BeautyTreatment> list = new ArrayList<BeautyTreatment>();
		Connection c = null;
		String sql = "SELECT * FROM beauty";
		try {
			c = DBConnectionHelper.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				list.add(processRow(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnectionHelper.close(c);
		}
		return list;
	}

	public BeautyTreatment findById(int id) {
		String sql = "SELECT * FROM beauty WHERE id = ?";
		BeautyTreatment treatment = null;
		Connection c = null;
		try {
			c = DBConnectionHelper.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				treatment = processRow(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnectionHelper.close(c);
		}
		return treatment;
	}
	
	public List<BeautyTreatment> findByLocation(String location){
		List<BeautyTreatment> list = new ArrayList<>();
		Connection c = null;
		String sql = "SELECT * FROM beauty as e WHERE UPPER(location) LIKE ?";
		try {
			c = DBConnectionHelper.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, "%" + location.toUpperCase() + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(processRow(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnectionHelper.close(c);
		}
		return list;
	}
	
	public BeautyTreatment create(BeautyTreatment treatment) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c=DBConnectionHelper.getConnection();
			ps = c.prepareStatement("INSERT INTO beauty VALUES (NULL, ?, ?, ?, ? ,?, ?, ?)",
					new String[] { "ID" }); //AUTO INCREMENT
			ps.setString(1, treatment.getService());
			ps.setInt(2, treatment.getDuration());
			ps.setString(3, treatment.getVenue());
			ps.setString(4, treatment.getDescription());
			ps.setString(5, treatment.getLocation());
			ps.setDouble(6, treatment.getPrice());
			ps.setString(7, treatment.getPicture());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnectionHelper.close(c);
		}
		return treatment;
	}

	public BeautyTreatment update(BeautyTreatment treatment) {
		Connection c = null;
		try {
			c = DBConnectionHelper.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE beauty SET Services=?, duration=?, venue=?, description=?, location=?, price=?, picture=? WHERE ID=?");
			ps.setString(1, treatment.getService());
			ps.setInt(2, treatment.getDuration());
			ps.setString(3, treatment.getVenue());
			ps.setString(4, treatment.getDescription());
			ps.setString(5, treatment.getLocation());
			ps.setDouble(6, treatment.getPrice());
			ps.setString(7, treatment.getPicture());
			ps.setInt(8, treatment.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally { 
			DBConnectionHelper.close(c);
		}
		return treatment;
	}
	
	public boolean remove(int id) {
		Connection c = null;
		try {
			c = DBConnectionHelper.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM beauty WHERE id=?");
			ps.setInt(1, id);
			int count = ps.executeUpdate();
			return count == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally { 
			DBConnectionHelper.close(c);
		}
	}
	
	protected BeautyTreatment processRow(ResultSet rs) throws SQLException {
		BeautyTreatment treatment = new BeautyTreatment();
		treatment.setId(rs.getInt("id"));
		treatment.setService(rs.getString("Services"));
		treatment.setDuration(rs.getInt("duration"));
		treatment.setVenue(rs.getString("venue"));
		treatment.setDescription(rs.getString("description"));
		treatment.setLocation(rs.getString("location"));
		treatment.setPrice(rs.getDouble("price"));
		treatment.setPicture(rs.getString("picture"));
		return treatment;
	}
	
}
