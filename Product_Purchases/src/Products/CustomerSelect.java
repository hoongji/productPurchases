package Products;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.ScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CustomerSelect extends JFrame {
// 회원 정보 조회 
	private JPanel contentPane;
	private JTable table;

	// 싱글톤 객체 가져오기
	private CustomerDAO dao = CustomerDAOImple.getInstance();
	
	/* 스윙 테이블을 사용하기 위한 멤버 변수 선언 */  
	
	private String[] colNames = {"Id", "Pw", "이름", "연락처"}; // 테이블 헤더에 들어갈 이름들
	private Object[] records = new Object[colNames.length]; // 테이블 데이터를 저장할 배열 객체
	private DefaultTableModel tableModel; // 테이블 형태를 만들 모델 변수
	
	public CustomerSelect() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("회원 관리");
		lblNewLabel.setFont(new Font("나눔고딕", Font.PLAIN, 17));
		lblNewLabel.setBounds(177, 10, 80, 28);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 54, 369, 221);
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
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				Object value = table.getValueAt(row, col);
				System.out.println(value);
			}
		});
		
		scrollPane.setViewportView(table);
		
		JButton btnSelectAll = new JButton("전체 조회");
		btnSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayCustomer();
			}
		});
		btnSelectAll.setBounds(160, 305, 114, 43);
		contentPane.add(btnSelectAll);
	}
	
	private void displayCustomer() {
		System.out.println("displayCustomer()");
		
		// DB에서 상품목록을 가져오는 메서드 호출
		ArrayList<CustomerVO> list = dao.getAllCustomers();
		
		// 가져온 상품 목록을 테이블에 표시 
		for(CustomerVO vo : list) {
			tableModel.addRow(new Object[] {
					vo.getCustomerId(),
					vo.getPassword(),
					vo.getCustomerName(),
					vo.getContact()
			});
		}
		
		
	}//end displayCustomer()	
}// end CustomerSelect
