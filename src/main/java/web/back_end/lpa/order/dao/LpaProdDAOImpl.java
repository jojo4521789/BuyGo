package web.back_end.lpa.order.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import core.util.HibernateUtil;
import web.back_end.lpa.product.entity.Lpa_Prod;

public class LpaProdDAOImpl implements LpaProdDAO {
	private static final long serialVersionUID = 1L;
	
//	private static final String INSERT_STMT = "INSERT INTO lpa_prod(MEMBER_NO, LPA_PROD_NAME, LPA_PROD_CATS_NO, LPA_PROD_OFF_TIME) VALUES (?, ?, ?, ?)";
//	private static final String DELETE_STMT = "DELETE FROM lpa_prod WHERE LPA_PROD_NO = ?";
//	private static final String UPDATE_STMT = "UPDATE lpa_prod SET LPA_PROD_CONTENT = ?, LPA_PROD_STOCK = ?, LPA_PROD_PRICE = ?, LPA_PROD_STATUS = ? where LPA_PROD_NO = ?";
//	private static final String FIND_BY_PK = "SELECT * FROM lpa_prod WHERE LPA_PROD_NO = ?";
//	private static final String GET_ALL = "SELECT * FROM lpa_prod";
//
//	static {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		} catch (ClassNotFoundException ce) {
//			ce.printStackTrace();
//		}
//	}

	public void add(Lpa_Prod lpaProd) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction(); // 開始交易
			session.persist(lpaProd); // 將實體物件新增⾄對應資料表中
			transaction.commit(); // 送交，同時會結束交易
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback(); // 還原，同時會結束交易
		}

		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lpa?serverTimezone=Asia/Taipei",
//					"root", "root");
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setInt(1, lpaProd.getMemberNo());
//			pstmt.setString(2, lpaProd.getLpaProdName());
//			pstmt.setInt(3, lpaProd.getLpaProdCatsNo());
//			pstmt.setTimestamp(4, lpaProd.getLpaProdOffTime());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			se.printStackTrace();
//			// Clean up JDBC resources
//		} finally {
//			closeResources(con, pstmt, null);
//		}
	}

	public void deleteByProdNo(Integer lpaProdNo) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lpa?serverTimezone=Asia/Taipei",
//					"root", "root");
//			pstmt = con.prepareStatement(DELETE_STMT);
//
//			pstmt.setInt(1, lpaProdNo);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			se.printStackTrace();
//			// Clean up JDBC resources
//		} finally {
//			closeResources(con, pstmt, null);
//		}
	}
	
	public int updateByProdNo(Lpa_Prod lpaProd) {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			try {
				Transaction transaction = session.beginTransaction();
				Lpa_Prod current_Lpa_Prod = session.get(Lpa_Prod.class, lpaProd.getLpaProdNo()); // 依ProdNo取得persistent物件
				
				// 如果傳LPA_PROD_NAME
				final String lpaProdName = lpaProd.getLpaProdName();
				if (lpaProdName != null) {
					current_Lpa_Prod.setLpaProdName(lpaProdName);
				}
				
				// 如果傳LPA_PROD_CONTENT
				final String lpaProdContent = lpaProd.getLpaProdContent();
				if (lpaProdContent != null) {
					current_Lpa_Prod.setLpaProdContent(lpaProdContent);
				}
				
				// 如果傳LPA_PROD_STOCK
				final Integer lpaProdStock = lpaProd.getLpaProdStock();
				if (lpaProdStock != null) {
					current_Lpa_Prod.setLpaProdStock(lpaProdStock);
				}
				
				// 如果傳LPA_PROD_SOLD
				final Integer lpaProdSold = lpaProd.getLpaProdSold();
				if (lpaProdSold != null) {
					current_Lpa_Prod.setLpaProdSold(lpaProdSold);
				}
				
				// 如果傳LPA_PROD_PRICE
				// 如果傳LPA_PROD_OFF_TIME
				// 如果傳LPA_PROD_STATUS
				transaction.commit();
				return 1;
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
				return -1;
			}
		}
		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lpa?serverTimezone=Asia/Taipei",
//					"root", "root");
//			pstmt = con.prepareStatement(UPDATE_STMT);
//
//			pstmt.setString(1, lpaProd.getLpaProdContent());
//			pstmt.setInt(2, lpaProd.getLpaProdStock());
//			pstmt.setInt(3, lpaProd.getLpaProdPrice());
//			pstmt.setInt(4, lpaProd.getLpaProdStatus());
//			pstmt.setInt(5, lpaProd.getLpaProdNo());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			se.printStackTrace();
//			// Clean up JDBC resources
//		} finally {
//			closeResources(con, pstmt, null);
//		}
//	}
	
//	public Lpa_Prod findByPK(Integer lpaProdNo) {
//		Lpa_Prod lpaProd = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lpa?serverTimezone=Asia/Taipei",
//					"root", "root");
//			pstmt = con.prepareStatement(FIND_BY_PK);
//			pstmt.setInt(1, lpaProdNo);
//			rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				lpaProd = new Lpa_Prod();
//				lpaProd.setLpaProdNo(rs.getInt("LPA_PROD_NO"));
//				lpaProd.setMemberNo(rs.getInt("MEMBER_NO"));
//				lpaProd.setLpaProdName(rs.getString("LPA_PROD_NAME"));
//				lpaProd.setLpaProdContent(rs.getString("LPA_PROD_CONTENT"));
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			se.printStackTrace();
//			// Clean up JDBC resources
//		} finally {
//			closeResources(con, pstmt, rs);
//		}
//		return lpaProd;
//	}
	
//	public List<Lpa_Prod> getAll() {
//		List<Lpa_Prod> list = new ArrayList<>();
//		Lpa_Prod lpaProd = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lpa?serverTimezone=Asia/Taipei",
//					"root", "root");
//			pstmt = con.prepareStatement(GET_ALL);
//			rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				Lpa_Prod lpaProd2 = new Lpa_Prod();
//				lpaProd2.setLpaProdNo(rs.getInt("LPA_PROD_NO"));
//				lpaProd2.setMemberNo(rs.getInt("MEMBER_NO"));
//				lpaProd2.setLpaProdName(rs.getString("LPA_PROD_NAME"));
//				lpaProd2.setLpaProdContent(rs.getString("LPA_PROD_CONTENT"));
//				list.add(lpaProd2);
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			se.printStackTrace();
//			// Clean up JDBC resources
//		} finally {
//			closeResources(con, pstmt, rs);
//		}
//		return list;
//	}
	
	private void closeResources(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}

}
