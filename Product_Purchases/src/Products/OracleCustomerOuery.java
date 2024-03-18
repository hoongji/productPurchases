package Products;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public interface OracleCustomerOuery {
	
//	사용자 정보	
		public static final String URL = 
				"jdbc:oracle:thin:@192.168.0.143:1521:xe"; //  접속할 오라클 DB 경로 
		public static final String USER = "scott";
		public static final String PASSWORD = "tiger";

// DB 연결을 위한 상수	
		public static final String TABLE_NAME = "CUSTOMERS";
		public static final String COL_CUSTOMER_ID = "CUSTOMER_ID";
		public static final String COL_PASSWORD = "PASSWORD";
		public static final String COL_CUSTOMER_NAME = "CUSTOMER_NAME";
		public static final String COL_CONTACT = "CONTACT";
		
// 테이블 및 컬럼 정보. 쿼리 작성
		
		// INSERT : 구매한 상품의 가격 및 수량 정보를 DB에 기록하는 기능
		
		public static final String CUST_INSERT = 
				"INSERT INTO " + TABLE_NAME
				+ " VALUES (?, ?, ?, ?)";

		// SELECT_ALL : 상품 전체 조회

		public static final String CUST_SELECT =	
				"SELECT * FROM " + TABLE_NAME 
				+ " ORDER BY " + COL_CUSTOMER_ID;
		
		// SELECT_BY_PRODUCT_ID
		public static final String CUST_SELECT_BY_CONTACT_ID =
					"SELECT * FROM " + TABLE_NAME 
					+ " WHERE " + COL_CUSTOMER_ID + " = ?";
		
		
		//UPDATE : 구매 시 재고 수량을 감소시키는 기능
		public static final String CUST_UPDATE = 
							"UPDATE " + TABLE_NAME + " SET " +
							COL_CUSTOMER_ID + " = ?, " +
							COL_PASSWORD + " = ?, " +
							COL_CUSTOMER_NAME + " = ?," +
							COL_CONTACT + " = ? " +
							"WHERE " + COL_CUSTOMER_ID  + " = ?";
		
		// DELETE 
		public static final String CUST_DELETE = 
							"DELETE " + TABLE_NAME + " WHERE "
							+ COL_CUSTOMER_ID + " = ?";
		
		// DB에서 모든 고객 정보를 가져오는 메서드
	   /* public static ArrayList<CustomerVO> getAllCustomers() {
	        ArrayList<CustomerVO> customerList = new ArrayList<>();

	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement pstmt = conn.prepareStatement(CUST_SELECT);
	             ResultSet rs = pstmt.executeQuery()) {

	            while (rs.next()) {
	                CustomerVO customer = new CustomerVO();
	                customer.setCustomerId(rs.getString(COL_CUSTOMER_ID));
	                customer.setPassword(rs.getString(COL_PASSWORD));
	                customer.setCustomerName(rs.getString(COL_CUSTOMER_NAME));
	                customer.setContact(rs.getString(COL_CONTACT));
	                customerList.add(customer);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return customerList;
	    }*/
	
		
}
