package Products;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class UserLogin extends JFrame implements OracleCustomerOuery{
	// 사용자의 로그인 페이지 (신규,로그인,취소)
	
	private JPanel contentPane;
	private JTextField textID;
	private JTextField textPW;

	static String userId;
	
	// 싱글톤 객체 가져오기
	private static CustomerDAO dao = CustomerDAOImple.getInstance();
	
	public UserLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 414, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User ");
		lblNewLabel.setFont(new Font("나눔고딕", Font.BOLD, 25));
		lblNewLabel.setBounds(168, 10, 65, 51);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID :");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 25));
		lblNewLabel_1.setBounds(60, 64, 52, 63);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PW : ");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 25));
		lblNewLabel_2.setBounds(47, 137, 65, 51);
		contentPane.add(lblNewLabel_2);
		
		textID = new JTextField();
		textID.setBounds(124, 71, 205, 37);
		contentPane.add(textID);
		textID.setColumns(10);
		
		textPW = new JTextField();
		textPW.setBounds(124, 137, 205, 37);
		contentPane.add(textPW);
		textPW.setColumns(10);
		
		JButton btnNew = new JButton("신규");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 신규 버튼을 누르면 회원가입 페이지(UserCreate)로 이동하게 한다.
			
				UserCreate create = new UserCreate();
				create.setVisible(true);
				
			}
		});
		btnNew.setBounds(15, 209, 97, 23);
		contentPane.add(btnNew);
		
		JButton btnLogin = new JButton("로그인");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textID.getText();
				String pw = textPW.getText();
								
				// 빈칸으로 등록을 누른다면 
		        if(id.isBlank() || pw.isBlank()) {
					JOptionPane.showMessageDialog( btnLogin,"입력란을 모두 작성해주세요");
					return;
				}
		        CustomerLogin();
			}
		});
		btnLogin.setBounds(148, 209, 97, 23);
		contentPane.add(btnLogin);
		
		JButton btnCancel = new JButton("취소");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(274, 209, 97, 23);
		contentPane.add(btnCancel);
	}
	
	
	private void CustomerLogin() {
		System.out.println("CustomerLogin()");
		// DB에서 고객 정보를 가져오기
	    ArrayList<CustomerVO> list = dao.getAllCustomers();

		// 사용자가 입력한 Id,Pw 
	    String inputId = textID.getText();
	    String inputPw = textPW.getText();
	    
	    
	    	//UserPurchase에서 쓸 textID
		    userId = inputId;
		    System.out.println("UserPurchase에서 쓸 textID = " + userId);
		    
		    // DB에서 가져온 고객 정보와 입력된 아이디와 비밀번호를 비교하여 로그인 처리
		    for(CustomerVO vo : list) {
		    	if(vo.getCustomerId().equals(inputId) && vo.getPassword().equals(inputPw)) {
		    		// 로그인 성공
		    		System.out.println("로그인 성공");
		    		// 상품 구매 페이지로 넘어가게 하기
		    		UserPage purchase = new UserPage(vo.getCustomerName());
		    		purchase.setVisible(true);
		    		
		    		// 현재 로그인 창 닫기
		    		dispose();
		    		
		    		// 로그인 성공했으면 반복문 나가기
		    		return; 
		    	}
		    }
		    // 로그인 실패
		    System.out.println("로그인을 실패했습니다.");
		    
	    
	}//end CustomerLogin()
	
	


	
}//end UserLogin()
