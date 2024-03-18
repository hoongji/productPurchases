package Products;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerDAOImple implements CustomerDAO , OracleCustomerOuery{


	// 싱글톤 디자인 패턴 적용
	   // 1. private static 자기 자신 타입의 멤버 변수
	   private static CustomerDAOImple instance = null;
	   
	   // 2. private 생성자
	   private CustomerDAOImple() {}
	   
	   // 3. public static 메소드 - 인스턴스를 리턴하는 메소드 구현
	   public static CustomerDAOImple getInstance() {
	      if(instance == null) {
	         instance = new CustomerDAOImple();
	      }
	      return instance;
	   }
	
	   private ArrayList<CustomerVO> list = new ArrayList<>(); // 연락처 정보를 저장할 배열
		
	   
	// DB에서 모든 고객 정보를 가져오는 메서드   
	@Override
	public ArrayList<CustomerVO> getAllCustomers() {
		System.out.println("<CustomerVO> getAllCustomers()");
		
		 ArrayList<CustomerVO> list = new ArrayList<>();

	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement pstmt = conn.prepareStatement(CUST_SELECT);
	             ResultSet rs = pstmt.executeQuery()) {

	            while (rs.next()) {
	                CustomerVO customer = new CustomerVO();
	                customer.setCustomerId(rs.getString(COL_CUSTOMER_ID));
	                customer.setPassword(rs.getString(COL_PASSWORD));
	                customer.setCustomerName(rs.getString(COL_CUSTOMER_NAME));
	                customer.setContact(rs.getString(COL_CONTACT));
	                list.add(customer);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	}//end getAllCustomers

}//end CustomerDAOImple
