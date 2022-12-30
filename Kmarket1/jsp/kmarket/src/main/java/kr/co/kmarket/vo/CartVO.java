package kr.co.kmarket.vo;

public class CartVO {
	private int cartNo;
	private String uid;
	private int proNo;
	private int count;
	private int price;
	private int discount;
	private int point;
	private int delivery;
	private int total;
	private String rdate;
	
	//추가 
	private String proName;
	private String descript;
	private String thumb1;
	private int cate1;
	private int cate2;
	
	public int getCate1() {
		return cate1;
	}

	public void setCate1(int cate1) {
		this.cate1 = cate1;
	}
	
	public void setCate1(String cate1) {
		this.cate1 = Integer.parseInt(cate1);
	}

	public int getCate2() {
		return cate2;
	}

	public void setCate2(int cate2) {
		this.cate2 = cate2;
	}
	
	public void setCate2(String cate2) {
		this.cate2 = Integer.parseInt(cate2);
	}

	public void setThumb1(String thumb1) {
		this.thumb1 = thumb1;
	}
	
	public String getThumb1() {
		return thumb1;
	}
	
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public int getCartNo() {
		return cartNo;
	}
	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public int getProNo() {
		return proNo;
	}
	public void setProNo(int proNo) {
		this.proNo = proNo;
	}
	
	public void setProNo(String proNo) {
		this.proNo = Integer.parseInt(proNo);
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public void setCount(String count) {
		this.count = Integer.parseInt(count);
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void setPrice(String price) {
		this.price = Integer.parseInt(price);
	}
	
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	public void setDiscount(String discount) {
		this.discount = Integer.parseInt(discount);
	}
	
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
	public void setPoint(String point) {
		this.point = Integer.parseInt(point);
	}
	
	public int getDelivery() {
		return delivery;
	}
	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}
	
	public void setDelivery(String delivery) {
		this.delivery = Integer.parseInt(delivery);
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public void setTotal(String total) {
		this.total = Integer.parseInt(total);
	}
	
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	
	
}
