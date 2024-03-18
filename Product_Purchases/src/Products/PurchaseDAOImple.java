package Products;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleDriver;

public class PurchaseDAOImple implements PurchaseDAO , OraclePurchaseQuery {

	
	// 싱글톤 디자인 패턴 적용
	   // 1. private static 자기 자신 타입의 멤버 변수
	   private static PurchaseDAOImple instance = null;
	   
	   // 2. private 생성자
	   private PurchaseDAOImple() {}
	   
	   // 3. public static 메소드 - 인스턴스를 리턴하는 메소드 구현
	   public static PurchaseDAOImple getInstance() {
	      if(instance == null) {
	         instance = new PurchaseDAOImple();
	      }
	      return instance;
	   }
	
	   private ArrayList<PurchaseVO> list = new ArrayList<>(); // 구매 내역를 저장할 배열
		
	
	@Override
	public int insert(PurchaseVO vo) {
		System.out.println("insert(PurchaseVO vo)");
		System.out.println(vo);
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
			pstmt = conn.prepareStatement(PURC_INSERT);	
			
			// 6. SQL 문장 완성 - SQL_INSERT 쿼리의 ?를 채워주는 코드
			
			pstmt.setString(1, vo.getCustomerId());
			System.out.println("SQL - CustomerId 넣기");
			pstmt.setInt(2, vo.getProductId());
			System.out.println("SQL - ProductId 넣기");
			pstmt.setInt(3, vo.getStock());
			System.out.println("SQL - getStock 까지 채워주기 ");

			
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

	
}//end PurchaseDAOImple
