package Products;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

public class adminPage extends JFrame {
	// 관리자의 상품 등록 페이지

	private JPanel contentPane;
	private JTextField textId,textName, textPrice,textStock;
	private JTable table;
	private JTextArea txtAreaInfo;
	// Jframe 클래스에서 contentPane 이라는 이름의 JPanel을 생성함
	// 즉 contentPane을 생성할 때 JPanel로 초기화
	// JFrame 에 표시되는 모든 GUI를 이 패널에 추가

	// 싱글톤 객체 가져오기
	private ProductDAO dao = ProductDAOImple.getInstance(); 

	//클래스 인스턴스 생성
	ProductVO vo = new ProductVO();
	
	/* 스윙 테이블을 사용하기 위한 멤버 변수 선언 */  
	
	private String[] colNames = {"Id", "이름", "가격", "수량"}; // 테이블 헤더에 들어갈 이름들
	private Object[] records = new Object[colNames.length]; // 테이블 데이터를 저장할 배열 객체
	private DefaultTableModel tableModel; // 테이블 형태를 만들 모델 변수
	
	public adminPage() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 583, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("상품 목록");
		lblNewLabel.setFont(new Font("나눔고딕", Font.PLAIN, 25));
		lblNewLabel.setBounds(217, 10, 119, 44);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("상품 ID : ");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(73, 205, 84, 30);
		contentPane.add(lblNewLabel_1);
		
		textId = new JTextField();
		textId.setBounds(175, 205, 161, 30);
		contentPane.add(textId);
		textId.setColumns(10);
		
		JButton btnSelectBy = new JButton("검색");
		btnSelectBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				selectByProd();
				
			}
		});
		btnSelectBy.setBounds(375, 205, 107, 29);
		contentPane.add(btnSelectBy);
		
		JLabel lblNewLabel_2 = new JLabel("상품 이름 : ");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(44, 259, 119, 30);
		contentPane.add(lblNewLabel_2);
		
		textName = new JTextField();
		textName.setBounds(175, 260, 161, 30);
		contentPane.add(textName);
		textName.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("상품 가격 : ");
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(44, 320, 119, 33);
		contentPane.add(lblNewLabel_3);
		
		textPrice = new JTextField();
		textPrice.setBounds(173, 320, 163, 30);
		contentPane.add(textPrice);
		textPrice.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("수량 : ");
		lblNewLabel_4.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(73, 385, 84, 24);
		contentPane.add(lblNewLabel_4);
		
		textStock = new JTextField();
		textStock.setBounds(175, 379, 161, 30);
		contentPane.add(textStock);
		textStock.setColumns(10);
		
		JButton btnInsert = new JButton("등록");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String name = textName.getText();
				
				// 빈칸으로 등록을 누른다면 
		        if(textId.getText().isBlank() || name.isBlank() || textPrice.getText().isBlank() ||  textStock.getText().isBlank()) {
					JOptionPane.showMessageDialog( btnInsert,"입력란을 모두 작성해주세요");
					return;
				}
				
				insertProd();
				selectAllProdTable();
				
			}

		});
		btnInsert.setBounds(84, 433, 97, 30);
		contentPane.add(btnInsert);
		
		JButton btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = textName.getText();
				
				// 빈칸으로 등록을 누른다면 
		        if(textId.getText().isBlank() || name.isBlank() || textPrice.getText().isBlank() ||  textStock.getText().isBlank()) {
					JOptionPane.showMessageDialog( btnInsert,"입력란을 모두 작성해주세요");
					return;
				}
				
				updateProd();
				selectAllProdTable();
				
			}
		});
		btnUpdate.setBounds(228, 433, 97, 30);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				deleteProd();
				selectAllProdTable();
				
			}
		});
		btnDelete.setBounds(375, 244, 107, 30);
		contentPane.add(btnDelete);
		
		JButton btnSelect = new JButton("전체검색");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				selectAllProd();
				selectAllProdTable();
				
			}
		});
		btnSelect.setBounds(385, 433, 97, 30);
		contentPane.add(btnSelect);
		
		JButton btnCustomerSelect = new JButton("회원 조회");
		btnCustomerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//회원조회(CustomerSelect) newFrame 생성
				CustomerSelect cust = new CustomerSelect();
				cust.setVisible(true);
				
			}
		});
		btnCustomerSelect.setBounds(398, 10, 123, 44);
		contentPane.add(btnCustomerSelect);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 65, 477, 130);
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
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(44, 480, 477, 74);
		contentPane.add(scrollPane_1);
		
		txtAreaInfo = new JTextArea();
		txtAreaInfo.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		scrollPane_1.setViewportView(txtAreaInfo);
	}// end initialize()
	
	
	
	private void insertProd() {
		System.out.println("insertProd()");
		
		int result = 0;
		
		int size = dao.select().size();
		// id에 string을 입력하면 숫자만 입력가능합니다를 뜨게 만들고 싶음
	    int Id = 0;
	    int stock = 0;
	    double price = 0; 
	    
	    try {
	        Id = Integer.parseInt(textId.getText());
	        stock = Integer.parseInt(textStock.getText());
	        price = Integer.parseInt(textPrice.getText());
	        
	    } catch (NumberFormatException e) {
	        // 숫자가 아닌 값을 입력한 경우 예외 처리
	        JOptionPane.showMessageDialog(null, "숫자만 입력 가능합니다.");
	        return;
	    }
		
		
		System.out.println("size = " + size + ", Id = " + Id );
		
		//int stock = Integer.parseInt(textStock.getText());
		
		String name = textName.getText();
		
		//double price = Double.parseDouble(textPrice.getText());
		
		//현재 등록된 길이(size)보다 큰 Id 값을 입력하는 경우에는 입력하지 못하도록 하기
		//중복도 입력하지 못하게 하기
		if(Id >= 0 && Id > size && stock > 0) {
					
			ProductVO vo = new ProductVO(Id, name, price, stock);
			
			System.out.println("상품 등록 성공!");
			
				try {
					
					result = dao.insert(vo);
					
					size = dao.select().size();
					
					System.out.println("크기 측정 완료!");
					System.out.println(result);
					
					if(result == 1) { 
						txtAreaInfo.setText("등록된 상품 개수 : " + size + "\n"
								+ "등록 완료했습니다.");
					}
				} catch (Exception e) {
					e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
				}
			
		}else {
			System.err.println("------ Id와 수량을 다시 확인하세요. ------");
		}

	
	}//end insertProd()
	
	
	private void updateProd() {
		System.out.println("updateProd()");
		
		int prodId;
		double price;
		int stock; 
	    try {
	        prodId = Integer.parseInt(textId.getText());
	        price = Double.parseDouble(textPrice.getText());
	        stock = Integer.parseInt(textStock.getText());
	        
	    } catch (NumberFormatException e) {
	        // 숫자가 아닌 값을 입력한 경우
	        JOptionPane.showMessageDialog(null, "숫자만 입력 가능합니다.");
	        return;
	    }
		
		int size = dao.select().size(); //이게 필요하려면 등록할때 부터 제한을 둬야할듯
		
		
		if(prodId >= 0 && prodId <= size) { 
			
			
			//int id = Integer.parseInt(textId.getText());
			
			String name = textName.getText();
			
			//double price = Double.parseDouble(textPrice.getText());
			
			//int stock = Integer.parseInt(textStock.getText());
						
			ProductVO vo = new ProductVO(prodId, name, price, stock);
			
			int result = dao.update(prodId, vo); 
			System.out.println("update 완료!");
			
			if(result == 1) {
				txtAreaInfo.setText("상품 수정 완료!");
			}
		} else {
			txtAreaInfo.setText("------ 해당 상품이 없습니다. ------");
		}
		
	}//end updateProd()
	
	
	private void deleteProd() {
		System.out.println("deleteProd()");
		
		int productId;
	    try {
	        productId = Integer.parseInt(textId.getText());
	    } catch (NumberFormatException e) {
	        // 숫자가 아닌 값을 입력한 경우
	        JOptionPane.showMessageDialog(null, "상품 ID는 숫자만 입력 가능합니다.");
	        return;
	    }
		
	
		int size = dao.select().size();
		System.out.println("productId = " + productId + ", size = " + size);
		
		if(productId >= 0 && productId <= size || productId > size) {
			int result = dao.delete(productId);
			
			txtAreaInfo.setText("상품 삭제 완료!");
		} else {
			txtAreaInfo.setText("------ 해당 상품이 없습니다. ------");
		}
	}//deleteProd()
	
	
	private void selectAllProd() {
		System.out.println("selectAllProd()");
		
		ArrayList<ProductVO> list = dao.select();
		int size = list.size();
		StringBuffer buffer = new StringBuffer();
		
		for(int i = 0; i < size; i++) {
			buffer.append("상품[" + i + "] \n" 
					+ list.get(i) + "\n");
		}
		txtAreaInfo.setText(buffer.toString()); 
		
	}//end selectAllProd()
	
	
	private void selectByProd() {
		System.out.println("selectByProd()");
		
		int productId;
	    try {
	        productId = Integer.parseInt(textId.getText());
	    } catch (NumberFormatException e) {
	        // 숫자가 아닌 값을 입력한 경우
	        JOptionPane.showMessageDialog(null, "상품 ID는 숫자만 입력 가능합니다.");
	        return;
	    }
		
	    int size = dao.select().size();
		
		System.out.println("상품 Id :" + productId + ", 상품 size : " + size);
		
		if(productId >= 0 && productId <= size) {
			txtAreaInfo.setText(dao.select(productId).toString()); 
		} else {
			txtAreaInfo.setText("------ 해당 상품이 없습니다. ------");
		}
	}//selectByProd()
	
	
	private void selectAllProdTable() {
		System.out.println("selectAllProdTable()");
		
		ArrayList<ProductVO> list = dao.select();
		tableModel.setRowCount(0);
		for(int i = 0; i < list.size(); i++) {
			ProductVO vo = list.get(i);
			records[0] = vo.getProductId();
			records[1] = vo.getProductName();
			records[2] = vo.getPrice();
			records[3] = vo.getStock();
 			tableModel.addRow(records);
		}
	}//end selectAllProdTable()
	
}//end adminPage()
