import static org.junit.Assert.assertEquals;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dao.ReimbursementDaoImpl;
import com.dao.UserDaoImpl;
import com.model.Reimbursement;
import com.model.User;
import com.utility.ConnectionUtility;

public class RepositoryTests {
	
	private static String url = "jdbc:h2:./folder1/ERS;MODE=PostgreSQL";
	private static String username = "sa";
	private static String password = "sa";
	
	
	private static ConnectionUtility connectionUtility = new ConnectionUtility(url, username, password);
	
	private static ReimbursementDaoImpl reimbDao = new ReimbursementDaoImpl(connectionUtility);
	private static UserDaoImpl userDao = new UserDaoImpl(connectionUtility);
	//run this only on the first time, then delete it. 
	
	
	@Before public void resetData() {
		String sql = "DELETE FROM reimbursement;"
				+ "DELETE FROM users;"
				+ "INSERT INTO users (user_id, username, PASSWORD, firstname, lastname, email, user_role_id) \r\n" + 
				"values(481516, 'orkun', '1234', 'Orkun', 'Ozekin', 'orkunozekin@gmail.com', 1);\r\n" + 
				"\r\n" + 
				"INSERT INTO users (user_id, username, PASSWORD, firstname, lastname, email, user_role_id) \r\n" + 
				"values(10, 'javier', '1234', 'Javier', 'Burrows', 'jburrows@gmail.com', 2);"
				+ "INSERT INTO reimbursement (reimb_id, amount, resolved, description, author, resolver, status_id, type_id)\r\n" + 
				"VALUES (94212, 100, '2020-05-01 20:20:20', 'first reimbursement request from dbeaver'\r\n" + 
				", 10, null, 1, 2);"
				+ "INSERT INTO users (user_id, username, PASSWORD, firstname, lastname, email, user_role_id) \r\n" + 
				"values(15, 'tara', '1234', 'Tara', 'Ozekin', 'tara@gmail.com', 2);";
		try {
			PreparedStatement ps = connectionUtility.getConn().prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	public void checkLogin() {
		User user = userDao.checkLogin("orkun", "1234");
		
		assertEquals("Orkun", user.getFirstname());
		assertEquals(1, user.getUser_role_id());
		assertEquals("Ozekin", user.getLastname());
	}
	
	

	
	@Test
	public void submitRequestTest() {
		//need to fix this
	
		Reimbursement reimb = new Reimbursement(3, 2400, "04-03-2020 00:00:00", "", "hello", 15, 481516, 1, 3, "pending", "Lodging");
		reimbDao.submitRequest(reimb);
		
//		Reimbursement reimbFromDao = reimbDao.getReimbursementById(3);
//		System.out.println(reimbFromDao);
//		assertEquals(reimbFromDao.getDescription(), "hello");
	}
	
	
	@Test
	public void getAllRequestsTest() {
		Reimbursement reimb = new Reimbursement(100, "test data description", 10, 1, 2);
		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
		reimbList.add(reimb);
		
		List<Reimbursement> list = reimbDao.getAllRequests();
		int realAmount = list.get(0).getAmount();
		int expectedAmount = reimb.getAmount();
		
		
		assertEquals(expectedAmount, realAmount);
	}
	
	
	@Test 
	public void getReimbursementById() {
		
		Reimbursement reimb = new Reimbursement(100, "first reimbursement request from dbeaver", 10, 1, 2);
		
		Reimbursement realReimb = reimbDao.getReimbursementById(94212);
		int realAmount = realReimb.getAmount();
		String realDesc = realReimb.getDescription();
		
		assertEquals(reimb.getAmount(), realAmount);
		assertEquals(reimb.getDescription(), realDesc);
		
	}
	
	
	@Test 
	public void getEmployeeRequests() {
		List<Reimbursement> reimbList = reimbDao.getEmployeeRequests(10);
		Reimbursement myReimb = reimbList.get(0);
		assertEquals(10, myReimb.getAuthor());
		assertEquals(94212, myReimb.getReimbId());
	}
	

//	
	@Test
	public void updateReimbursement() {
		Date date = new Date();
		long time = date.getTime();
		
		//update a reimbursement
		reimbDao.updateReimbursement(new Timestamp(time), 15, 94212, 2);
		//get that reimbursement by its id
		Reimbursement reimbAfterUpdated = reimbDao.getReimbursementById(94212);
		//fields to check:
		int resolver = reimbAfterUpdated.getResolver();
		int statusId = reimbAfterUpdated.getStatusId();
		
		assertEquals(15, resolver);
		assertEquals(2, statusId);
	}
	
	
	
}





























//static { 
//	String sql = "CREATE TABLE user_roles (\r\n" + 
//			"	user_role_id serial PRIMARY KEY \r\n" + 
//			"	, user_role varchar(40) NOT NULL \r\n" + 
//			");\r\n" + 
//			"\r\n" + 
//			"\r\n" + 
//			"CREATE TABLE reimbursement_type (\r\n" + 
//			"	reimb_type_id serial PRIMARY KEY \r\n" + 
//			"	, reimb_type varchar(10) NOT NULL \r\n" + 
//			");\r\n" + 
//			"\r\n" + 
//			"CREATE TABLE reimbursement_status (\r\n" + 
//			"	reimb_status_id serial PRIMARY KEY \r\n" + 
//			"	, reimb_status varchar(10) NOT NULL \r\n" + 
//			");\r\n" + 
//			"\r\n" + 
//			"CREATE TABLE users (\r\n" + 
//			"	user_id serial PRIMARY KEY \r\n" + 
//			"	, username varchar(50) UNIQUE NOT NULL \r\n" + 
//			"	, PASSWORD varchar(50) NOT NULL \r\n" + 
//			"	, firstname varchar(100) NOT NULL \r\n" + 
//			"	, lastname varchar(100) NOT NULL \r\n" + 
//			"	, email varchar(150) UNIQUE NOT NULL \r\n" + 
//			"	, user_role_id integer NOT NULL \r\n" + 
//			"	, FOREIGN KEY(user_role_id) REFERENCES user_roles(user_role_id) \r\n" + 
//			");\r\n" + 
//			"\r\n" + 
//			"\r\n" + 
//			"CREATE TABLE reimbursement (\r\n" + 
//			"	reimb_id serial PRIMARY KEY\r\n" + 
//			"	, amount integer NOT NULL \r\n" + 
//			"	, submitted TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP\r\n" + 
//			"	, resolved timestamp\r\n" + 
//			"	, description varchar(250)\r\n" + 
//			"	, receipt bytea \r\n" + 
//			"	, author integer NOT NULL\r\n" + 
//			"	, resolver integer \r\n" + 
//			"	, status_id integer NOT NULL \r\n" + 
//			"	, type_id integer NOT NULL\r\n" + 
//			"	, FOREIGN KEY(author) REFERENCES users(user_id)\r\n" + 
//			"	, FOREIGN KEY(resolver) REFERENCES users(user_id) \r\n" + 
//			"	, FOREIGN key(status_id) REFERENCES reimbursement_status(reimb_status_id)\r\n" + 
//			"	, FOREIGN key(type_id) REFERENCES reimbursement_type(reimb_type_id)\r\n" + 
//			");"
//			+ "INSERT INTO user_roles (user_role_id, user_role) values(1, 'finance_manager');\r\n" + 
//			"INSERT INTO user_roles (user_role_id, user_role) values(2, 'employee');"
//			+ "INSERT INTO reimbursement_type (reimb_type_id, reimb_type) values(1, 'lodging');\r\n" + 
//			"INSERT INTO reimbursement_type (reimb_type_id, reimb_type) values(2, 'travel');\r\n" + 
//			"INSERT INTO reimbursement_type (reimb_type_id, reimb_type) values(3, 'food');\r\n" + 
//			"INSERT INTO reimbursement_type (reimb_type_id, reimb_type) values(4, 'other');"
//			+ "INSERT INTO reimbursement_status (reimb_status_id, reimb_status) values(1, 'pending');\r\n" + 
//			"INSERT INTO reimbursement_status (reimb_status_id, reimb_status) values(2, 'approved');\r\n" + 
//			"INSERT INTO reimbursement_status (reimb_status_id, reimb_status) values(3, 'denied');";
//	
//	
//	try {
//		PreparedStatement ps = connectionUtility.getConn().prepareStatement(sql);
//	
//		ps.executeUpdate();
//
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
//}
//