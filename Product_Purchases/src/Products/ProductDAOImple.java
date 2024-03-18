package Products;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import oracle.jdbc.OracleDriver;


public class ProductDAOImple implements ProductDAO,OracleProductQuery{

	// 싱글톤 디자인 패턴 적용
	   // 1. private static 자기 자신 타입의 멤버 변수
	   private static ProductDAOImple instance = null;
	   
	   // 2. private 생성자
	   private ProductDAOImple() {
	      
	   }
	   
	   // 3. public static 메소드 - 인스턴스를 리턴하는 메소드 구현
	   public static ProductDAOImple getInstance() {
	      if(instance == null) {
	         instance = new ProductDAOImple();
	      }
	      return instance;
	   }
	  
	   
	  private ArrayList<ProductVO> list = new ArrayList<>(); // 연락처 정보를 저장할 배열
		
	  public int getSize() {
			return list.size();
		}
	
	@Override
	public int insert(ProductVO pr) { // 상품 등록
		System.out.println("insert(ProductVO pr)");
		
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		// PreparedStatement : 매개변수를 갖고 있는 SQL 문장을 활용하기 위한 클래스 
	    //						Statement 와 상속 관계
		
		try {
			// 3. Oracle JDBC 드라이버를 메모리에 로드 
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");
			
			// 4. DB 와 Connection(연결)을 맺음 
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			
			// 5. Connection 객체를 사용하여 PreparedStatement 객체를 생성
			pstmt = conn.prepareStatement(PROD_INSERT);	
			
			// 6. SQL 문장 완성 - SQL_INSERT 쿼리의 ?를 채워주는 코드
			pstmt.setInt(1, pr.getProductId());
			pstmt.setString(2, pr.getProductName());
			pstmt.setDouble(3, pr.getPrice());
			pstmt.setInt(4, pr.getStock());
			
			// SQL 쿼리의 ? 순서와 parameterIndex의 값을 동일하게 지정
			// 예시) ? 가 첫 번째이면 parameterIndex = 1 
			
			// setInt() : DB의 Number 타입
			// setString() : DB의 varchar, varchar2 타입 
			// setFloat() : DB의 Float 타입
			// setDate() : DB의 Date 타입
			
			// 7. SQL 문장 실행(DB 서버로 SQL 전송)
			result = pstmt.executeUpdate();
			
			// 8. DB 서버가 보낸 결과 확인/처리
			System.out.println(result + "행이 삽입됐습니다.");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	
	@Override
	public ProductVO select(int prId) {
		System.out.println("ProductVO select(int prId)");
		
		ProductVO vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 3. Oracle JDBC 드라이버를 메모리에 로드 
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");
			
			// 4. DB 와 Connection(연결)을 맺음 
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");

			// 5. Connection 객체를 사용하여 PreparedStatement 객체를 생성
			pstmt = conn.prepareStatement(PROD_SELECT_BY_PROD_ID);
			
			// 6. SQL 문장 완성 - SQL_INSERT 쿼리의 ?를 채워주는 코드
			
			pstmt.setInt(1,prId); 
			//메소드는 매개 변수로 전달된 값(prId)을 PreparedStatement 객체에 설정한다 
			//첫 번째 매개 변수의 인덱스는 1. 따라서 이 코드는 첫 번째 매개 변수의 값을 prId로 설정한다

			// 7. SQL 문장 실행(DB 서버로 SQL 전송)
			rs = pstmt.executeQuery();
	
	
			if(rs.next()) { // 레코드가 존재할 때까지
				int productId = rs.getInt(1); // PRODUCT_ID 컬럼
				String productName = rs.getString(2); // NAME 컬럼
				double price = rs.getDouble(3); // PRICE 컬럼
				int stock = rs.getInt(4);	// STOCK 컬럼
				
				vo = new ProductVO(productId, productName, price, stock);

				// 8. DB 서버가 보낸 결과 확인/처리
				System.out.println("부분 선택이 완료되었습니다.");
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vo;
	}



	@Override
	public int update(int prId, ProductVO vo) {
		System.out.println("update(int prId, ProductVO vo)");
		
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		// PreparedStatement : 매개변수를 갖고 있는 SQL 문장을 활용하기 위한 클래스
		//						Statement와 상속관계
		
		try {
			// 2. Oracle JDBC 드라이버를 메모리에 로드
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");
			
			// 3. DB와 Connection(연결)을 맺음
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			
			// 4. Connection 객체를 사용하여 PreparedStatement 객체를 생성
			pstmt = conn.prepareStatement(PROD_UPDATE);
			
			
			// 5. SQL 문장 완성 - SQL_INSERT 쿼리의 ?를 채워주는 코드
			pstmt.setString(1, vo.getProductName());
			pstmt.setDouble(2, vo.getPrice());
			pstmt.setInt(3, vo.getStock());
			pstmt.setString(4, String.valueOf(vo.getProductId())); 
			// SQL 쿼리의 ? 순서와 parameterIndex의 값은 동일하게 지정
			// 예시) ?가 첫 번째이면 parameterIndex = 1
			
			/*
			 productId를 int 타입으로 받아서 생기는 문제
			 String.valueOf() 메서드를 사용해 문자열로 변환
			 */
			
			// setInt() : DB의 Number 타입
			// setString() : DB의 varchar, varchar2 타입
			// setFloat() : DB의 Float 타입
			// setDate() : DB의 Date 타입
			
			// 6. SQL 문장 실행(DB 서버로 SQL 전송)
			result = pstmt.executeUpdate();
			
			// 7. DB 서버가 보낸 결과 확인/처리
			System.out.println(result + "행이 수정됐습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result; // 0 : 실패, 1 : 성공
	}

	@Override
	public int delete(int prId) {
		System.out.println("delete(int prId)");
		
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		// PreparedStatement : 매개변수를 갖고 있는 SQL 문장을 활용하기 위한 클래스
		//						Statement와 상속관계
		
		try {
			// 2. Oracle JDBC 드라이버를 메모리에 로드
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");
			
			// 3. DB와 Connection(연결)을 맺음
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			
			// 4. Connection 객체를 사용하여 PreparedStatement 객체를 생성
			pstmt = conn.prepareStatement(PROD_DELETE);
			System.out.println("PROD_DELETE 객체 생성");
			
			// 5. SQL 문장 완성 - SQL_INSERT 쿼리의 ?를 채워주는 코드
			pstmt.setInt(1, prId);
			System.out.println("SQL 쿼리의 ?를 채워줌");
			
			// 6. SQL 문장 실행(DB 서버로 SQL 전송)
			result = pstmt.executeUpdate();
			System.out.println("DB 서버로 SQL 전송");
			
			// 7. DB 서버가 보낸 결과 확인/처리
			System.out.println(result + "행이 삭제됐습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result; // 0 : 실패, 1 : 성공
	}

	@Override
	public ArrayList<ProductVO> select() { // 전체 출력
		System.out.println("ArrayList<ProductVO> select()");
		
		ArrayList<ProductVO> list = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");
			

			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");

			pstmt = conn.prepareStatement(PROD_SELECT);
			

			rs = pstmt.executeQuery();
	
			list = new ArrayList<>();
			while(rs.next()) { // 레코드가 존재할 때까지
				int productId = rs.getInt(1); // PRODUCT_ID 컬럼
				String productName = rs.getString(2); // PRODUCTNAME 컬럼
				double price = rs.getDouble(3); // PRICE 컬럼
				int stock = rs.getInt(4);	// STOCK 컬럼
				
				ProductVO vo = new ProductVO(productId, productName, price, stock);
				list.add(vo);
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	
	
	
	
}//end ProductDAOImple
