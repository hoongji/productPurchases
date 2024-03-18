package Products;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class adminLogin extends JFrame implements OracleProductQuery {
	// 관리자 로그인 프레임
	
	private JTextField textFieldId;
	private JTextField textFieldPw;
	private JPanel contentPane;
	
	private JFrame frame;

	private JTextField textID;
	private JTextField textName;
	private JTextField textPrice;
	private JTextField textStock;
	private JTextField textResult;

	
	public adminLogin() {
		// contentPane을 초기화
		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        setBounds(100, 100, 450, 300);
	        contentPane = new JPanel(); // contentPane을 초기화하고 생성합니다.
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane); // 생성한 contentPane을 JFrame에 설정합니다.
	        contentPane.setLayout(new BorderLayout(0, 0));
	        
	        
		setBounds(100, 100, 480, 347);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(60, 250, 262, 48);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton LoginButton = new JButton("로그인");
				LoginButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						
						administratorLogin(); // 관리자 로그인 
						
					}

				});
				LoginButton.setFont(new Font("굴림", Font.PLAIN, 18));
				LoginButton.setActionCommand("Login");
				buttonPane.add(LoginButton);
				getRootPane().setDefaultButton(LoginButton);
			}
			{
				JButton cancelButton = new JButton("취소");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose(); // 창을 닫으면 현재 창만 종료됨
					}
				});
				cancelButton.setFont(new Font("굴림", Font.PLAIN, 18));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		JLabel lblId = new JLabel("ID : ");
		lblId.setFont(new Font("굴림", Font.BOLD, 24));
		lblId.setBounds(76, 63, 73, 34);
		getContentPane().add(lblId);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(144, 63, 194, 34);
		getContentPane().add(textFieldId);
		textFieldId.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("PW : ");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 23));
		lblNewLabel.setBounds(76, 125, 68, 48);
		getContentPane().add(lblNewLabel);
		
		textFieldPw = new JTextField();
		textFieldPw.setBounds(144, 136, 194, 34);
		getContentPane().add(textFieldPw);
		textFieldPw.setColumns(10);
	}
	
	private void administratorLogin() {
		System.out.println("administratorLogin");
		
		String Id = textFieldId.getText();
		String Pw = textFieldPw.getText();
		
		if(Id.equals(USER) && Pw.equals(PASSWORD)) {
			
			System.out.println("로그인 성공!");
			
			// adminPage 클래스의 인스턴스 생성
		    adminPage admin = new adminPage();
			admin.setVisible(true);
			
		}else {
			System.err.println("잘못된 로그인 입니다. 다시 입력하세요.");
		}
	}

	
	
	

}
