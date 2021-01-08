package com.dao;

import java.sql.Timestamp;
import java.util.List;

import com.model.Reimbursement;
import com.model.User;

public interface ReimbursementDao {
	
	public void submitRequest(Reimbursement reimb);
	public List<Reimbursement> getAllRequests();
	public List<Reimbursement> getEmployeeRequests(int employeeId);
	public void updateReimbursement(Timestamp resolved, int resolver, int reimbId, int statusId);
	public Reimbursement getReimbursementById(int id);
}
