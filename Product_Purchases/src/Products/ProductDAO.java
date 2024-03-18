package Products;

import java.util.ArrayList;

public interface ProductDAO {

//	- 상품 등록
	public abstract int insert(ProductVO vo);
	
//  - 상품 전체 조회
	public abstract ArrayList<ProductVO> select();
	
//	- 특정 상품 조회
	public abstract ProductVO select(int productId); 
	
//	- 상품 수정
	public abstract int update(int productId, ProductVO vo);
	
//	- 상품 삭제
	public abstract int delete(int productId);
	
	
	
}//end ProductDAO
