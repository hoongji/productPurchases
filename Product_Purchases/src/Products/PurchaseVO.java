package Products;

import java.sql.Date;

public class PurchaseVO {
	private int purchaseId; 
	private String customerId;
	private int productId;
	private int stock;
	private Date purchaseDate;
	
	
	public PurchaseVO(){}


	public PurchaseVO(int purchaseId, String customerId, int productId, int stock, Date purchaseDate) {
		super();
		this.purchaseId = 0; // 시퀀스에서 자동으로 증가되므로 0으로 설정
		this.customerId = customerId;
		this.productId = productId;
		this.stock = stock;
		this.purchaseDate = purchaseDate;
	}


	public int getPurchaseId() {
		return purchaseId;
	}


	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}


	public String getCustomerId() {
		return customerId;
	}


	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public Date getPurchaseDate() {
		return purchaseDate;
	}


	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}


	
	@Override
	public String toString() {
		return "PurchaseVO [purchaseId=" + purchaseId + ", customerId=" + customerId + ", productId=" + productId
				+ ", stock=" + stock + ", purchaseDate=" + purchaseDate + "]";
	}
	
	
	
	
	
	
	
	
	
}
