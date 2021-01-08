package com.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class Reimbursement {
	
	Date date = new Date();  
	//state
	
	private int reimbId;
	private int amount;
	private Timestamp submitted;
	private Timestamp resolved;
	private String description;
	private byte[] receipt;
	private int author;
	private int resolver;
	private int statusId;
	private int typeId;
	private String status;
	private String reimbType;
	
	//Constructor
	public Reimbursement() {
	}

	
	
	//All-args Constructor

	public Reimbursement(int reimbId, int amount, String submitted, String resolved, String description,
			int author, int resolver, int statusId, int typeId, String status, String reimbType) {
		super();
		this.reimbId = reimbId;
		this.amount = amount;
		this.submitted = new Timestamp(date.getTime());
		this.resolved = null;
		this.description = description;
	
		this.author = author;
		this.resolver = resolver;
		this.statusId = statusId;
		this.typeId = typeId;
		this.status = status;
		this.reimbType = reimbType;
	}



	public Reimbursement(int amount, String description, int author, int statusId, int typeId) {
		this.amount = amount;
		this.description = description;
		this.author = author;
		this.statusId = statusId;
		this.typeId = typeId;
	}
	




		//Getters and Setters
	
	
	public String getReimbType() {
		return reimbType;
	}



	public void setReimbType(String reimbType) {
		this.reimbType = reimbType;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}
	
	
		public int getAmount() {
		return amount;
	}



	public int getReimbId() {
		return reimbId;
	}



	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}



	public int getStatusId() {
		return statusId;
	}



	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}



	public int getTypeId() {
		return typeId;
	}



	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}



	public void setAmount(int amount) {
		this.amount = amount;
	}



	public Timestamp getSubmitted() {
		return submitted;
	}



	public void setSubmitted(Timestamp timestamp) {
		this.submitted = timestamp;
	}



	public Timestamp getResolved() {
		return resolved;
	}



	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public byte[] getReceipt() {
		return receipt;
	}



	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}



	public int getAuthor() {
		return author;
	}



	public void setAuthor(int author) {
		this.author = author;
	}



	public int getResolver() {
		return resolver;
	}



	public void setResolver(int resolver) {
		this.resolver = resolver;
	}



	//toString
	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", amount=" + amount + ", submitted=" + submitted + ", resolved="
				+ resolved + ", description=" + description + ", receipt=" + receipt + ", author=" + author
				+ ", resolver=" + resolver + ", statusId=" + statusId + ", typeId=" + typeId + "Status = " + status;
	}

	
}
