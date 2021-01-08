package com.model;

public class UpdatedReimb {
	
	private int reimbId;
	private String status;
	private int statusId;
	
	
	public UpdatedReimb() {
		// TODO Auto-generated constructor stub
	}

	public UpdatedReimb(int reimbId, String status, int statusId) {
		super();
		this.reimbId = reimbId;
		this.status = status;
		this.statusId = statusId;
	}
	
	
	
	public int getId() {
		return reimbId;
	}
	public void setId(int id) {
		this.reimbId = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getStatus_id() {
		return statusId;
	}
	public void setStatus_id(int statusId) {
		this.statusId = statusId;
	}



	@Override
	public String toString() {
		return "UpdatedReimb [id=" + reimbId + ", status=" + status + ", status_id=" + statusId + "]";
	}
	
	
	

}
