package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.model.Reimbursement;
import com.utility.ConnectionUtility;

public class ReimbursementDaoImpl implements ReimbursementDao {

	
	
	
	Connection connection;

	Logger loggy = Logger.getLogger(ReimbursementDaoImpl.class);

	public ReimbursementDaoImpl() {
		ConnectionUtility connectionUtil = new ConnectionUtility();
		connection = connectionUtil.getConn();
	}
	
	public ReimbursementDaoImpl(ConnectionUtility connectionUtil) {
		connection = connectionUtil.getConn();
	}

	@Override
	public void submitRequest(Reimbursement reimb) {
		String sql = "INSERT INTO reimbursement(amount, description, author, status_id, type_id) values(?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, reimb.getAmount());
			ps.setString(2, reimb.getDescription());
//			ps.setBytes(3, reimb.getReceipt());
			ps.setInt(3, reimb.getAuthor());
			ps.setInt(4, 1);
			ps.setInt(5, reimb.getTypeId());

			ps.executeUpdate();
			if(loggy.isInfoEnabled()) {
				loggy.info(" ---Reimbursement has been submitted.--- ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	//Get all reimbursement requests for Finance Managers to see. 
	@Override
	public List<Reimbursement> getAllRequests() {
		// Possibly join users and reimbursement tables to display author's name and so
		// on...
		List<Reimbursement> listOfReimb = new ArrayList<>();
		String sql = "SELECT * FROM reimbursement r JOIN reimbursement_status rs ON rs.reimb_status_id = r.status_id JOIN reimbursement_type rt ON rt.reimb_type_id = r.type_id";
	
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Reimbursement reimb = new Reimbursement();
				reimb.setReimbId(rs.getInt("reimb_id"));
				reimb.setAmount(rs.getInt("amount"));
				reimb.setSubmitted(rs.getTimestamp("submitted"));
				reimb.setResolved(rs.getTimestamp("resolved"));
				reimb.setDescription(rs.getString("description"));
				reimb.setReceipt(rs.getBytes("receipt"));
				reimb.setAuthor(rs.getInt("author"));
				reimb.setResolver(rs.getInt("resolver"));
				reimb.setStatusId(rs.getInt("status_id"));
				reimb.setTypeId(rs.getInt("type_id"));
				reimb.setStatus(rs.getString("reimb_status"));
				reimb.setReimbType(rs.getString("reimb_type"));
				listOfReimb.add(reimb);
			}
			if(loggy.isInfoEnabled()) {
				loggy.info("---All Reimbursement Requests of all Employees have been fetched.---");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfReimb;
	}
	
	
	
	@Override
	public Reimbursement getReimbursementById(int id) {
		String sql = "SELECT * FROM reimbursement WHERE reimb_id = ?";
		Reimbursement reimb = null;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				reimb = new Reimbursement();
				reimb.setReimbId(rs.getInt("reimb_id"));
				reimb.setAmount(rs.getInt("amount"));
				reimb.setSubmitted(rs.getTimestamp("submitted"));
				reimb.setResolved(rs.getTimestamp("resolved"));
				reimb.setDescription(rs.getString("description"));
				reimb.setReceipt(rs.getBytes("receipt"));
				reimb.setAuthor(rs.getInt("author"));
				reimb.setResolver(rs.getInt("resolver"));
				reimb.setStatusId(rs.getInt("status_id"));
				reimb.setTypeId(rs.getInt("type_id"));
			}
			if(loggy.isInfoEnabled()) {
				loggy.info(reimb + " ---has been fetched by its id.---");
			}
		} catch(SQLException e)	{
			e.printStackTrace();
		}
		return reimb;
	}

	
	
	//Get all requests that belong to an employee.
	@Override
	public List<Reimbursement> getEmployeeRequests(int employeeId) {
		List<Reimbursement> listOfReimb = new ArrayList<>();
		String sql = "SELECT * FROM reimbursement r JOIN reimbursement_status rs ON rs.reimb_status_id = r.status_id JOIN reimbursement_type rt ON rt.reimb_type_id = r.type_id WHERE author = ?";
	
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, employeeId);

			ResultSet rs = ps.executeQuery();
			
			
			while (rs.next()) {
				Reimbursement reimb = new Reimbursement();
				reimb.setReimbId(rs.getInt("reimb_id"));
				reimb.setAmount(rs.getInt("amount"));
				reimb.setSubmitted(rs.getTimestamp("submitted"));
				reimb.setResolved(rs.getTimestamp("resolved"));
				reimb.setDescription(rs.getString("description"));
				reimb.setReceipt(rs.getBytes("receipt"));
				reimb.setAuthor(rs.getInt("author"));
				reimb.setResolver(rs.getInt("resolver"));
				reimb.setStatusId(rs.getInt("status_id"));
				reimb.setStatus(rs.getString("reimb_status"));
				reimb.setTypeId(rs.getInt("type_id"));
				reimb.setReimbType(rs.getString("reimb_type"));
				listOfReimb.add(reimb);
			}
			
			if(loggy.isInfoEnabled()) {
				loggy.info(" ---Employee's requests have been fetched.--- ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listOfReimb;
	}
	
	
	public void updateReimbursement(Timestamp resolved, int resolver, int reimbId, int statusId) {
		String sql = "UPDATE reimbursement SET resolved = ?, resolver = ?, status_id = ? WHERE reimb_id = ?";
		

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setTimestamp(1, resolved);
			ps.setInt(2, resolver);
			ps.setInt(3, statusId);
			ps.setInt(4, reimbId);
			
			ps.executeUpdate();
			if(loggy.isInfoEnabled()) {
				loggy.info(" ---Reimbursement has been resolved.---");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
}
