package com.model;

public enum UserRole {
	EMPLOYEE, FINANCEMANAGER;

	@Override
	public String toString() {
		switch (ordinal()) {
		case 0:
			return "Finance Manager";
		case 1:
			return "Employee";
		}
		return "";
	}

}
