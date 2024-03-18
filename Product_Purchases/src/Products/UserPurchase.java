package Products;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class UserPurchase extends JFrame {

	private JPanel contentPane;

	// productDAOImple 싱글톤 객체 가져오기 
	private ProductDAO dao = ProductDAOImple.getInstance();
		
	// purchaseDAOImple 싱글톤 객체 가져오기 
	private PurchaseDAO pdao = PurchaseDAOImple.getInstance();
	
	// UserPage 클래스의 인스턴스를 생성
	//UserPage user = new UserPage(getName());
	UserPage user; // 위에꺼 대신 이거 쓰자ㅎㅎ
	
	
	private JTextField textField;
	private JTextField textView;
	private JTextField textInputStock;
	
	
	public UserPurchase(int productId,String productName,double price,int stock) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 509, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("수량 선택 : ");
		lblNewLabel.setBounds(46, 219, 93, 44);
		lblNewLabel.setFont(new Font("나눔고딕", Font.PLAIN, 18));
		contentPane.add(lblNewLabel);
		
		
		textField = new JTextField();
		textField.setFont(new Font("나눔고딕", Font.PLAIN, 18));
		textField.setBounds(142, 219, 93, 44);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("구매");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// 빈 칸 & 숫자가 아닌 값을 입력했을 때, 예외처리
				int inputStock;
			    try {
			    	inputStock = Integer.parseInt(textInputStock.getText());
			    	if(inputStock < 0) {
			    	// 음수값을 입력한 경우
				        JOptionPane.showMessageDialog(null, "음수값은 입력할 수 없습니다.");
			    	}
			    } catch (NumberFormatException e1) {
			        // 숫자가 아닌 값을 입력한 경우
			        JOptionPane.showMessageDialog(null, "수량은 숫자만 입력 가능합니다.");
			        return;
			    }
				
				productUpdate();
				purchaseInsert();
			}
		});
		btnNewButton.setBounds(289, 211, 119, 57);
		btnNewButton.setFont(new Font("나눔고딕", Font.PLAIN, 18));
		contentPane.add(btnNewButton);
		
		ProductVO productVO = dao.select(productId);
		
		textView = new JTextField(productVO.toString());
		textView.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		textView.setHorizontalAlignment(SwingConstants.CENTER);
		textView.setBounds(12, 84, 469, 57);
		contentPane.add(textView); 
		textView.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("구매상품");
		lblNewLabel_1.setFont(new Font("나눔고딕", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(207, 38, 93, 18);
		contentPane.add(lblNewLabel_1);
		
		textInputStock = new JTextField();
		textInputStock.setBounds(137, 219, 116, 44);
		contentPane.add(textInputStock);
		textInputStock.setColumns(10);
	}

	//ProductDB -> update : 
	//사용자가 입력한 수량을 원래 상품 stock에서 빼고 업데이트
	
	private void productUpdate() { 
		System.out.println("productUpdate()");
		// stock 값을 입력 받음
		
		int inputStock = Integer.parseInt(textInputStock.getText());
			
		// 사용자가 입력한 수량이 상품의 재고량을 초과하는지 확인
	    if(inputStock > user.selectedProductStock) {
	        JOptionPane.showMessageDialog(null, "구매하려는 수량이 상품의 재고량을 초과합니다.");
	        return; // 구매를 중단하고 메서드를 종료
	    }
	    
		System.out.println("stock 값을 읽어옴");
			
		
		if(inputStock > 0) {
		// UserPage에서 선택한 ProductId에 해당되는 row 값을 가져옴	
			
			ProductVO vo = new ProductVO(user.selectedProductId, user.selectedProductName, user.selectedProductPrice, user.selectedProductStock - inputStock);
			
			int result = dao.update(user.selectedProductId, vo);
			
			System.out.println("update 완료!");
			
			if(result == 1) {
				System.out.println("상품 stockUpdate 완료");
			} else {
				System.out.println("상품 stockUpdate 실패");
			}
		
		}
	}//end productUpdate()


	
	// PurchaseDB -> insert
	private void purchaseInsert() {
		System.out.println("purchaseInsert()");
		
		int result = 0;
		
		// - 상품 정보 불러오기 
		
		UserLogin login = new UserLogin(); // UserPage 클래스의 인스턴스를 생성 (초기화)
		
		
		String customerId = login.userId; //UserLogin에서 입력한 Id를 customerId으로 쓸 것

		int productId = user.selectedProductId;

		int inputStock = Integer.parseInt(textInputStock.getText());
		
		if(inputStock > 0 && inputStock <= user.selectedProductStock) {
			
			//  PurchaseVO 클래스의 객체 생성
			PurchaseVO vo = new PurchaseVO(0, customerId, productId, inputStock, null);
			
			System.out.println("purchaseInsert 상품 정보 읽기 성공");
			
			System.out.println("상품 등록 성공!");
			
			// 현재 productVO에 저장된 상품의 수량보다 많은 상품의 수량을 구매하게 되면 제한을 걸어두기 -> inputStock 제한 두기
			
			try {
				
				result = pdao.insert(vo);
				
				System.out.println("result = " + result);
				
				if(result == 1) { 
					System.out.println("구매 정보 등록 완료!");
				}
			} catch (Exception e) {
				e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
			}
			
		}else {
			
		    try {
		    	inputStock = Integer.parseInt(textInputStock.getText());
		    } catch (NumberFormatException e1) {
		        // 숫자가 아닌 값을 입력한 경우
		        JOptionPane.showMessageDialog(null, "수량을 다시 입력해주세요.");
		        return;
		    }
			
			//System.out.println("------ 수량을 다시 입력해주세요. ------");
		}
		
	}//end purchaseInsert()
	
}//end UserPurchase()
