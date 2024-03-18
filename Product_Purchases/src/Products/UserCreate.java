package Products;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import oracle.jdbc.driver.OracleDriver;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class UserCreate extends JFrame implements OracleCustomerOuery {
	// 사용자 신규 등록 페이지
	// 등록하면 새로운 프레임으로 성공, 실패 여부를 알려주기
	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField textFieldPw;
	private JTextField textFieldName;
	private JTextField textFieldContact;

	private static CustomerDAO dao = CustomerDAOImple.getInstance();

	public UserCreate() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 553, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("신규 등록");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 25));
		lblNewLabel.setBounds(202, 10, 125, 56);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID : ");
		lblNewLabel_1.setFont(new Font("나눔고딕", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(88, 92, 42, 35);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PW : ");
		lblNewLabel_2.setFont(new Font("나눔고딕", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(72, 152, 58, 44);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("이름 : ");
		lblNewLabel_3.setFont(new Font("나눔고딕", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(72, 224, 58, 24);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("연락처 :");
		lblNewLabel_4.setFont(new Font("나눔고딕", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(53, 289, 78, 29);
		contentPane.add(lblNewLabel_4);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(140, 92, 299, 35);
		contentPane.add(textFieldId);
		textFieldId.setColumns(10);
		
		textFieldPw = new JTextField();
		textFieldPw.setBounds(140, 160, 299, 35);
		contentPane.add(textFieldPw);
		textFieldPw.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(142, 222, 297, 35);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldContact = new JTextField();
		textFieldContact.setBounds(143, 283, 295, 35);
		contentPane.add(textFieldContact);
		textFieldContact.setColumns(10);
		
		JButton btnNewInsert = new JButton("등록");
		btnNewInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 아이디, 비밀번호 , 이름 , 연락처 받아오기
				String id = textFieldId.getText();
		        String pw = textFieldPw.getText();
		        String name = textFieldName.getText();
		        String contact = textFieldContact.getText();
		        
		        // 빈칸으로 등록을 누른다면 
		        if(id.isBlank() || pw.isBlank() ||  name.isBlank() ||  contact.isBlank()) {
					JOptionPane.showMessageDialog( btnNewInsert,"입력란을 모두 작성해주세요");
					return;
				}
		        
		     // 등록한 Id,Pw가 다르다면
			    if(!id.equals(pw)) {
			    	
			    	// 로그인 진행 
			    	insertUser(id,pw);
	
			    }else {
			    	System.out.println("아이디와 비밀번호는 다르게 설정해주세요.");
			    }
				
			}
		});
		btnNewInsert.setBounds(129, 365, 125, 35);
		contentPane.add(btnNewInsert);
		
		JButton btnNewCancle = new JButton("취소");
		btnNewCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewCancle.setBounds(304, 365, 125, 35);
		contentPane.add(btnNewCancle);
	}//UserCreate()

	
	private boolean sessionToken(String id, String pw) {
		System.out.println("sessionToken()");
		// DB에서 고객 정보 가져오기
		ArrayList<CustomerVO> list = dao.getAllCustomers();
		
		// DB에서 아이디,비밀번호 비교해서 둘 중 하나라도 중복이 나오면 경고 문구 나가게 하기
		for(CustomerVO vo : list) {
			if(vo.getCustomerId().equals(id)) {
				return true; // 아이디, 비번이 일치하면 중복 로그인
			}
		}// 중복로그인이 아님
		return false;
	}//end ssesionToken()
	
	
	private void insertUser(String id,String pw) {
		System.out.println("insertUser()");
		
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
			pstmt = conn.prepareStatement(CUST_INSERT);
			
			// 5. SQL 문장 완성 - SQL_INSERT 쿼리의 ?를 채워주는 코드
			pstmt.setString(1, textFieldId.getText());
			pstmt.setString(2, textFieldPw.getText());
			pstmt.setString(3, textFieldName.getText());
			pstmt.setString(4, textFieldContact.getText());
			
			// 중복 아이디 또는 비밀번호를 확인하기
			if(sessionToken(id,pw)) {
				System.out.println("중복된 아이디가 존재합니다.");
				return; // 중복된 경우 삽입을 중단
			}
			
			// 6. SQL 문장 실행(DB 서버로 SQL 전송)
			result = pstmt.executeUpdate();
			
			// 7. DB 서버가 보낸 결과 확인/처리
			System.out.println(result + "행이 삽입됐습니다.");
			System.out.println("신규 등록 성공!");
			
			// 신규 등록을 완료했다면 현재 창을 닫아준다.
			dispose();
			
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
		//return result; // 0 : 실패, 1 : 성공
	}//end insertUser()
	
	
	

		
}//end UserCreate 
