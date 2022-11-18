package dao;

import java.util.ArrayList;
import java.util.List;

import db.DBHelper;
import vo.CustomerVO;

public class CustomerDAO extends DBHelper {

	private static CustomerDAO instance = new CustomerDAO();
	public static CustomerDAO getInstance() {
		return instance;
	}
	
	private CustomerDAO() {}
	
	public void insertUser(CustomerVO bv) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("insert into `customer` values (?,?,?,?)");
			psmt.setInt(1, bv.getCustId());
			psmt.setString(2, bv.getName());
			psmt.setString(3, bv.getAddress());
			psmt.setString(4, bv.getPhone());
			psmt.executeUpdate();
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CustomerVO selectUser(String custId) {
		
		CustomerVO cv = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("select * from `customer` where `custId`=?");
			psmt.setString(1, custId);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				cv = new CustomerVO();
				cv.setCustId(rs.getInt(1));
				cv.setName(rs.getString(2));
				cv.setAddress(rs.getString(3));
				cv.setPhone(rs.getString(4));				
			}
			
			close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return cv;
	}
	
	public List<CustomerVO> selectUsers() {
		
		List<CustomerVO> custs = new ArrayList<>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from `customer`");
			
			while(rs.next()) {
				CustomerVO cv = new CustomerVO();
				cv.setCustId(rs.getInt(1));
				cv.setName(rs.getString(2));
				cv.setAddress(rs.getString(3));
				cv.setPhone(rs.getString(4));
				custs.add(cv);
			}
			
			close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return custs;
	}
	
	public void updateUser(CustomerVO cv) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("update `customer` set `name`=?, `address`=?, `phone`=? where `custId`=?");
			psmt.setString(1, cv.getName());
			psmt.setString(2, cv.getAddress());
			psmt.setString(3, cv.getPhone());
			psmt.setInt(4, cv.getCustId());
			psmt.executeUpdate();
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(String custId) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("delete from `customer` where `custId`=?");
			psmt.setString(1, custId);
			psmt.executeUpdate();
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}