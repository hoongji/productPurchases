package Products;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class ProductMain {

	private JFrame frame;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private static ProductDAO dao;
	
	private JTextField textField;
	
	
	public static void main(String[] args) {
		dao = ProductDAOImple.getInstance();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductMain window = new ProductMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public ProductMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JRadioButton rdbtnAdministrator = new JRadioButton("관리자 ");
		rdbtnAdministrator.setSelected(true);
		buttonGroup.add(rdbtnAdministrator);
		rdbtnAdministrator.setBounds(83, 39, 140, 23);
		frame.getContentPane().add(rdbtnAdministrator);
		
		JRadioButton rdbtnUser = new JRadioButton("사용자");
		buttonGroup.add(rdbtnUser);
		rdbtnUser.setBounds(260, 39, 121, 23);
		frame.getContentPane().add(rdbtnUser);
		
		JButton btnChoose = new JButton("선택");
		btnChoose.addMouseListener(new MouseAdapter() {
			// 관리자 선택인 경우 -> 선택 클릭 -> textField에 " '관리자'를 선택하셨습니다." 출력
            // 사용자 선택인 경우 -> 선택 클릭 -> textField에 " '사용자'를 선택하셨습니다." 출력
			public void mouseClicked(MouseEvent e) {
				if(rdbtnAdministrator.isSelected()) {
	        		textField.setText("'관리자'를 선택하셨습니다.");  
	        
	        		adminLogin adminLogin = new adminLogin();
					adminLogin.setVisible(true);
					
	        	}else {
	        		textField.setText("'사용자'를 선택하셨습니다.");   
	        		
	        		UserLogin user = new UserLogin();
	        		user.setVisible(true);
	        	
	        	}
			}
		});
		btnChoose.setBounds(113, 89, 186, 64);
		frame.getContentPane().add(btnChoose);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(37, 177, 344, 40);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
