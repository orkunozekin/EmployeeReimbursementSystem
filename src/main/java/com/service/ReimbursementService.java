package com.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.dao.ReimbursementDaoImpl;
import com.model.Reimbursement;

public class ReimbursementService {
	ReimbursementDaoImpl reimbDao = new ReimbursementDaoImpl();	
	
	public List<Reimbursement> getAllRequests() {
		return reimbDao.getAllRequests();
	}
	
	public Reimbursement getReimbursementById(int id) {
		return reimbDao.getReimbursementById(id);
	}
	
	public void submitRequest(Reimbursement reimb) {
		reimb.setResolved(null);
		reimbDao.submitRequest(reimb);
	}
	
	public List<Reimbursement> getEmployeeRequests(int employeeId) {
		return reimbDao.getEmployeeRequests(employeeId);
	}
	
	public void updateReimbursement(Reimbursement reimbToDao) {
		
		int resolver = reimbToDao.getResolver();
		int reimbId = reimbToDao.getReimbId();
		int statusId = reimbToDao.getStatusId();
		
		//Convert Date to Timestamp as the DB expects it in Timestamp format.
		Date utilDate = new java.util.Date();
		Timestamp sq = new java.sql.Timestamp(utilDate.getTime());  
		
		//To convert the Timestamp above into String.
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		System.out.println(sdf.format(sq));
		
		//Timestamp resolved, int resolver, int reimbId, int statusId
		reimbDao.updateReimbursement(sq, resolver, reimbId, statusId);
		
		
	
	}
}
