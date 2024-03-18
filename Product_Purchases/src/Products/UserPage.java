package Products;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class UserPage extends JFrame implements OracleCustomerOuery{
// 로그인한 사용자의 상품 구매 페이지
	private JPanel contentPane;
	private JTextField textUserName;
	private JTable table;

	JTextField textID = new JTextField();
	JTextField textPW = new JTextField();

	// productDAOImple 싱글톤 객체 가져오기 
	private ProductDAO dao = ProductDAOImple.getInstance();
	
	// UserPurchase 클래스로 전달할 선택한 상품 정보
    static int selectedProductId;
    static String selectedProductName;
    static double selectedProductPrice;
    static int selectedProductStock;
	
/* 스윙 테이블을 사용하기 위한 멤버 변수 선언 */  
	
	private String[] colNames = {"Id", "이름", "가격", "수량"}; // 테이블 헤더에 들어갈 이름들
	private Object[] records = new Object[colNames.length]; // 테이블 데이터를 저장할 배열 객체
	private DefaultTableModel tableModel; // 테이블 형태를 만들 모델 변수

	
	public UserPage(String name) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 517, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User_name : ");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setBounds(24, 5, 101, 42);
		contentPane.add(lblNewLabel);
		
		textUserName = new JTextField();
		textUserName.setBounds(118, 10, 137, 33);
		contentPane.add(textUserName);
		textUserName.setColumns(10);
		textUserName.setText(name);
		
		JLabel lblNewLabel_1 = new JLabel("상품 목록");
		lblNewLabel_1.setFont(new Font("나눔고딕", Font.PLAIN, 21));
		lblNewLabel_1.setBounds(200, 73, 101, 33);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 116, 448, 272);
		contentPane.add(scrollPane);
		
		// 테이블 초기화
		tableModel = new DefaultTableModel(colNames, 0) {
			@Override
		public boolean isCellEditable(int row, int column) {
			return false;
			}
		};
		
		table = new JTable(tableModel);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int selectedRow = table.getSelectedRow(); // 선택된 행 인덱스 가져오기
		        selectedProductId = (int) tableModel.getValueAt(selectedRow, 0); // 선택된 행의 첫 번째 열(상품 ID) 값 가져오기
		        selectedProductName  = (String) tableModel.getValueAt(selectedRow, 1); // 두 번째 열(상품 이름)
		        selectedProductPrice = (double) tableModel.getValueAt(selectedRow, 2); // 세 번째 열(상품 가격)
		        selectedProductStock = (int) tableModel.getValueAt(selectedRow, 3); // 네 번째 열(상품 수량)
		        
				// 상품을 클릭하면 UserPurchase 프레임으로 이동
				UserPurchase purchase = new UserPurchase(selectedProductId,selectedProductName,selectedProductPrice,selectedProductStock); //선택한 상품 ID 값 가져와서
				purchase.setVisible(true);
			}
		});
		scrollPane.setViewportView(table);
		
		// DB에서 상품목록을 가져와 DB에 표시하는 메서드
		displayProdList();
		
		JLabel lblNewLabel_2 = new JLabel("상품을 클릭해서 구매해주세요 ");
		lblNewLabel_2.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(146, 433, 203, 33);
		contentPane.add(lblNewLabel_2);
		
		JButton btnRefresh = new JButton("새로고침");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnRefresh.setBounds(347, 15, 97, 33);
		contentPane.add(btnRefresh);
	}
	
	
	// DB에서 상품목록을 가져와 DB에 표시하는 메서드
	private void displayProdList() {
		System.out.println("displayProdList()");
		
		// DB에서 상품목록을 가져오는 메서드 호출
		ArrayList<ProductVO> list = dao.select();
		
		// 가져온 상품 목록을 테이블에 표시 
		for(ProductVO prod : list) {
			tableModel.addRow(new Object[] {
					prod.getProductId(),
					prod.getProductName(),
					prod.getPrice(),
					prod.getStock()
			});
		}
	}//end displayProdList()
	
	// 새로고침하는 메소드
	private void update() {
		ArrayList<ProductVO> list = dao.select();
		
		int rowCount = tableModel.getRowCount(); // 현재 테이블의 행 수를 가져옴
	    // 테이블 초기화
	    for (int i = rowCount - 1; i >= 0; i--) {
	        tableModel.removeRow(i); // 기존 행 모두 삭제
	    }

	    // 새로운 데이터로 테이블에 행을 추가
	    for (ProductVO prod : list) {
	        tableModel.addRow(new Object[]{
	                prod.getProductId(),
	                prod.getProductName(),
	                prod.getPrice(),
	                prod.getStock()
	        });
		
	    }
	}//end refresh()
	
	
	
	
	
}//end UserPage()
