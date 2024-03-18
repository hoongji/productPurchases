package Products;

public interface OracleProductQuery {

	// Oracle DB 정보 인터페이스. DB 연결을 위한 상수. 사용자 정보
//		테이블 및 컬럼 정보. 쿼리 작성

//	사용자 정보	
		public static final String URL = 
				"jdbc:oracle:thin:@192.168.0.143:1521:xe"; //  접속할 오라클 DB 경로 
		public static final String USER = "scott";
		public static final String PASSWORD = "tiger";

// DB 연결을 위한 상수	
		public static final String TABLE_NAME = "PRODUCTS";
		public static final String COL_PRODUCT_ID = "PRODUCT_ID";
		public static final String COL_PRODUCTNAME = "PRODUCTNAME";
		public static final String COL_PRICE = "PRICE";
		public static final String COL_STOCK = "STOCK";
		
// 테이블 및 컬럼 정보. 쿼리 작성
		
		//INSERT
		public static final String PROD_INSERT = 
				"INSERT INTO " + TABLE_NAME
				+ " VALUES (?, ?, ?, ?)";

		//SELECT_ALL
		public static final String PROD_SELECT =	
				"SELECT * FROM " + TABLE_NAME 
				+ " ORDER BY " + COL_PRODUCT_ID;
		
		//SELECT_BY_CONTACT_Id 
		public static final String PROD_SELECT_BY_PROD_ID =
					"SELECT * FROM " + TABLE_NAME 
					+ " WHERE " + COL_PRODUCT_ID + " = ?";
		
		
		//UPDATE
		public static final String PROD_UPDATE = 
							"UPDATE " + TABLE_NAME + " SET " +
							COL_PRODUCTNAME + " = ?, " +
							COL_PRICE + " = ?, " +
							COL_STOCK + " = ? " +
							"WHERE " + COL_PRODUCT_ID  + " = ?";
		
		// DELETE 
		public static final String PROD_DELETE = 
							"DELETE " + TABLE_NAME + " WHERE "
							+ COL_PRODUCT_ID + " = ?";
		
		// STOCK_UPDATE
		public static final String STOCK_UPDATE = 
				"UPDATE " + TABLE_NAME + " SET " +
					COL_STOCK  + " = ?" +
				"WHERE " + COL_PRODUCT_ID  + " = ?";
		
	
}
