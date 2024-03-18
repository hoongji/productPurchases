package Products;

public class ProductVO {

	private int productId;
	private String productName;
	private double price;
	private int stock;
	
	
	public ProductVO() {}


	public ProductVO(int productId, String productName, double price, int stock) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.stock = stock;
	}

	

	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	
	@Override
	public String toString() {
		return "ProductVO [productId=" + productId + ", productName=" + productName + ", price=" + price + ", stock="
				+ stock + "]";
	}
	
	
	
	
	
	
}
