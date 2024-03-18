package Products;

public interface OraclePurchaseQuery {

//	사용자 정보	
		public static final String URL = 
				"jdbc:oracle:thin:@192.168.0.143:1521:xe"; //  접속할 오라클 DB 경로 
		public static final String USER = "scott";
		public static final String PASSWORD = "tiger";

// DB 연결을 위한 상수	
		public static final String TABLE_NAME = "PURCHASES";
		public static final String COL_PURCHASE_ID = "PURCHASE_ID";
		public static final String COL_CUSTOMER_ID = "CUSTOMER_ID";
		public static final String COL_PRODUCT_ID = "PRODUCT_ID";
		public static final String COL_STOCK = "STOCK";
		public static final String COL_PURCHASE_DATE = "PURCHASE_DATE";
		
// 테이블 및 컬럼 정보. 쿼리 작성
		
		// INSERT : 구매한 상품의 가격 및 수량 정보를 DB에 기록하는 기능
		
		public static final String PURC_INSERT = 
			    "INSERT INTO " + TABLE_NAME
			    + " VALUES (PURCHASES_SEQ.NEXTVAL, ?, ?, ?, sysdate)";

	
}
