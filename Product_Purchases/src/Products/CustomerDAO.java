package Products;

import java.util.ArrayList;

public interface CustomerDAO {

	// 모든 고객 정보 가져오기
	public abstract ArrayList<CustomerVO> getAllCustomers();
	
}//end Customer
