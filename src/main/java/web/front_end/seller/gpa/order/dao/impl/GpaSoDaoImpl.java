package web.front_end.seller.gpa.order.dao.impl;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import web.front_end.seller.gpa.order.dao.GpaSoDao;
import web.front_end.seller.gpa.order.dto.GpaSoAndGpaProdDTO;
import web.front_end.seller.gpa.order.entity.GpaSo;
import web.front_end.seller.gpa.prod.entity.GpaProd;

public class GpaSoDaoImpl implements GpaSoDao {

	public static void main(String[] args) {
		GpaSoDaoImpl gpaSoDaoImpl = new GpaSoDaoImpl();

		// 測試
		// 新增insert
		Session session = gpaSoDaoImpl.getSession();
		
		GpaSo gpaSo = new GpaSo();
		gpaSo.setGpaProdNo(3);
		gpaSo.setMemberNo(5);
		gpaSo.setGpaProdCount(1);
		gpaSo.setGpaProdPrice(1000);
		gpaSo.setGpaProdTotal(100000);
		gpaSo.setGpaSoStat(1);
		gpaSo.setGpaBuyName("Alan");
		gpaSo.setGpaBuyTel("0912345678");
		gpaSo.setGpaBuyAdd("台北市");
		gpaSo.setGpaProd(null); // 未使用到該關聯表單資料，將此表單null避免報錯
		gpaSo.setMember(null); // 未使用到該關聯表單資料，將此表單null避免報錯
		
		try {
			Transaction transaction = session.beginTransaction(); // 開始交易
			gpaSoDaoImpl.insert(gpaSo); // 新增
			transaction.commit(); // 送交，同時會結束交易
			System.out.println("成功");
		}
		catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback(); // 還原，同時會結束交易
			System.out.println("失敗");
		}

		// 刪除deleteById
//		Session session = gpaSoDaoImpl.getSession();
//
//		try {
//			Transaction transaction = session.beginTransaction(); // 開始交易
//			gpaSoDaoImpl.deleteById(4); // 刪除
//			transaction.commit(); // 送交，同時會結束交易
//			System.out.println("成功");
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback(); // 還原，同時會結束交易
//			System.out.println("失敗");
//		}

		// 修改update
//		Session session = gpaSoDaoImpl.getSession();
//		
//		GpaSo gpaSo = new GpaSo();
//		gpaSo.setGpaSoNo(4);
//		gpaSo.setGpaProdNo(3);
//		gpaSo.setMemberNo(5);
//		gpaSo.setGpaProdCount(100);
//		gpaSo.setGpaProdPrice(1000);
//		gpaSo.setGpaProdTotal(100000);
//		gpaSo.setGpaSoStat(1);
//		gpaSo.setGpaBuyName("Alan");
//		gpaSo.setGpaBuyTel("0912345678");
//		gpaSo.setGpaBuyAdd("台中市");
//		gpaSo.setGpaEvaSeller(5);
//		gpaSo.setGpaEvaMember(5);
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		System.out.println(gpaSoDaoImpl.update(gpaSo));
//		transaction.commit(); // 送交，同時會結束交易

		// 查詢selectById
//		Session session = gpaSoDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		System.out.println(gpaSoDaoImpl.selectById(3));
//		transaction.commit(); // 送交，同時會結束交易
//		
		// 查詢selectAll
//		Session session = gpaSoDaoImpl.getSession();
//
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		List<GpaSo> gpaSoList = gpaSoDaoImpl.selectAll();
//		for (GpaSo gpaSo : gpaSoList) {
//			System.out.print("gpaSoNo:" + gpaSo.getGpaSoNo() + ",");
//			System.out.print("gpaProdNo:" + gpaSo.getGpaProdNo() + ",");
//			System.out.print("memberNo:" + gpaSo.getMemberNo() + ",");
//			System.out.println("gpaProdTotal:" + gpaSo.getGpaProdTotal());
//		}
//		transaction.commit(); // 送交，同時會結束交易

		// 查詢selectByMemberNo
//		Session session = gpaSoDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		List<GpaSo> GpaSoList = gpaSoDaoImpl.selectByMemberNo(2);
//		for (GpaSo gpaSo : GpaSoList) {
//			System.out.print("gpaSoNo:" + gpaSo.getGpaSoNo() + ",");
//			System.out.print("memberNo:" + gpaSo.getMemberNo() + ",");
//			System.out.print("gpaProdNo:" + gpaSo.getGpaProdNo() + ",");
//			System.out.println("gpaProdTotal:" + gpaSo.getGpaProdTotal());
//		}
//		transaction.commit(); // 送交，同時會結束交易

		// 查詢selectByMemberNoAndSoStat
//		Session session = gpaSoDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		List<GpaSo> gpaSoList = gpaSoDaoImpl.selectByMemberNoAndSoStat(1, 0);
//		for (GpaSo gpaSo : gpaSoList) {
//			System.out.print("gpaSoNo:" + gpaSo.getGpaSoNo() + ",");
//			System.out.print("memberNo:" + gpaSo.getMemberNo() + ",");
//			System.out.print("gpaProdNo:" + gpaSo.getGpaProdNo() + ",");
//			System.out.println("gpaProdTotal:" + gpaSo.getGpaProdTotal());
//		}
//		transaction.commit(); // 送交，同時會結束交易

		// 修改updateGpaEvaSellerByGpaSoNo
//		Session session = gpaSoDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		
//		gpaSoDaoImpl.updateGpaEvaSellerByGpaSoNo(1, 4);
//		
//		transaction.commit(); // 送交，同時會結束交易

		// 查詢select
//		Session session = gpaSoDaoImpl.getSession();
//
//		Transaction transaction = session.beginTransaction(); // 開始交易
//
//		GpaSo gpaSo = new GpaSo();
//		GpaProd gpaProd = new GpaProd();
//
//		List<GpaSoAndGpaProdDTO> gpaSoAndGpaProdDTOList = gpaSoDaoImpl.selectByGpaSoAndGpaProd(gpaSo, gpaProd);
//
//		System.out.println(gpaSoAndGpaProdDTOList.size());
//		for (GpaSoAndGpaProdDTO gpaSoAndGpaProdDTO : gpaSoAndGpaProdDTOList) {
//			System.out.println("-------------");
//			System.out.println("GPA_SO");
//			System.out.println("getGpaSoNo:" + gpaSoAndGpaProdDTO.getGpaSo().getGpaSoNo());
//			System.out.println("getMemberNo:" + gpaSoAndGpaProdDTO.getGpaSo().getMemberNo());
//			System.out.println("getGpaProdCount:" + gpaSoAndGpaProdDTO.getGpaSo().getGpaProdCount());
//			System.out.println("getGpaProdTotal:" + gpaSoAndGpaProdDTO.getGpaSo().getGpaProdTotal());
//			System.out.println("GPA_PROD");
//			System.out.println("getGpaProdName:" + gpaSoAndGpaProdDTO.getGpaProd().getGpaProdName());
//			System.out.println("getMemberNo:" + gpaSoAndGpaProdDTO.getGpaProd().getMemberNo());
//			System.out.println("getGpaEndDate:" + gpaSoAndGpaProdDTO.getGpaProd().getGpaEndDate());
//			System.out.println("-------------");
//		}
//
//		transaction.commit(); // 送交，同時會結束交易
		
		// 查詢selectByGpaSoStat
//		Session session = gpaSoDaoImpl.getSession();
//
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		List<GpaSo> gpaSoList = gpaSoDaoImpl.selectByGpaSoStat(4);
//		
//		for(GpaSo gpaSo : gpaSoList) {
//			System.out.println("---------");
//			System.out.println("getGpaSoNo:" + gpaSo.getGpaSoNo());
//			System.out.println("getGpaProdNo:" + gpaSo.getGpaProdNo());
//			System.out.println("getMemberNo:" + gpaSo.getMemberNo());
//			System.out.println("getGpaSoStat:" + gpaSo.getGpaSoStat());
//			System.out.println("---------");
//		}
//		transaction.commit(); // 送交，同時會結束交易
		
		// 查詢selectGpaSoCountByMemberNoAndSoStatAndSearchStr
//		Session session = gpaSoDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		Integer count = gpaSoDaoImpl.selectGpaSoCountByMemberNoAndSoStatAndSearchStr(2, 0, "電");
//		System.out.println("count:" + count);
//		transaction.commit(); // 送交，同時會結束交易
		
		// 查詢selectGpaSoCountBySellerMemberNoAndSoStatAndSearchStr
//		Session session = gpaSoDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		Integer count = gpaSoDaoImpl.selectGpaSoCountBySellerMemberNoAndSoStatAndSearchStr(2, 0, "");
//		System.out.println("count:" + count);
//		transaction.commit(); // 送交，同時會結束交易
		
		// 查詢selectByMemberNoAndSoStatAndLimitAndOffsetAndSearchStr
//		Session session = gpaSoDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		List<GpaSo> gpaSoList = gpaSoDaoImpl.selectByMemberNoAndSoStatAndLimitAndOffsetAndSearchStr(2, 0, 10, 0, "23");
//		
//		for(GpaSo gpaSo : gpaSoList) {
//			System.out.println("----------------");
//			System.out.println("gpaSo.getGpaSoNo():" + gpaSo.getGpaSoNo());
//			System.out.println("gpaSo.getGpaProd().getGpaProdName():" + gpaSo.getGpaProd().getGpaProdName());
//			System.out.println("gpaSo.getGpaProdCount():" + gpaSo.getGpaProdCount());
//			System.out.println("gpaSo.getMemberNo():" + gpaSo.getMemberNo());
//			System.out.println("gpaSo.getGpaSoStat():" + gpaSo.getGpaSoStat());
//			System.out.println("----------------");
//		}
//		
//		transaction.commit(); // 送交，同時會結束交易
		
		// 查詢selectBySellerMemberNoAndSoStatAndLimitAndOffsetAndSearchStr
//		Session session = gpaSoDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		List<GpaSo> gpaSoList = gpaSoDaoImpl.selectBySellerMemberNoAndSoStatAndLimitAndOffsetAndSearchStr(2, 0, 10, 0, "");
//		
//		for(GpaSo gpaSo : gpaSoList) {
//			System.out.println("----------------");
//			System.out.println("gpaSo.getGpaSoNo():" + gpaSo.getGpaSoNo());
//			System.out.println("gpaSo.getGpaProd().getGpaProdName():" + gpaSo.getGpaProd().getGpaProdName());
//			System.out.println("gpaSo.getGpaProdCount():" + gpaSo.getGpaProdCount());
//			System.out.println("gpaSo.getMemberNo():" + gpaSo.getMemberNo());
//			System.out.println("gpaSo.getGpaSoStat():" + gpaSo.getGpaSoStat());
//			System.out.println("----------------");
//		}
//		
//		transaction.commit(); // 送交，同時會結束交易
	}

	@Override
	public int insert(GpaSo gpaSo) {
		getSession().persist(gpaSo);
		return 1;
	}

	@Override
	public int deleteById(Integer id) {
		Session session = getSession();
		GpaSo gpaSo = session.get(GpaSo.class, id);
		session.remove(gpaSo);
		return 1;
	}

	@Override
	public int update(GpaSo entity) {
		final String hql = "UPDATE GpaSo SET gpaProdNo = :gpaProdNo, memberNo = :memberNo, gpaProdCount = :gpaProdCount, gpaProdPrice = :gpaProdPrice, gpaProdTotal = :gpaProdTotal, gpaSoStat = :gpaSoStat, gpaBuyName = :gpaBuyName, gpaBuyTel = :gpaBuyTel, gpaBuyAdd = :gpaBuyAdd, gpaEvaSeller = :gpaEvaSeller, gpaEvaMember = :gpaEvaMember WHERE gpaSoNo = :gpaSoNo";
		Query<?> query = getSession().createQuery(hql);
		return query.setParameter("gpaSoNo", entity.getGpaSoNo()).setParameter("gpaProdNo", entity.getGpaProdNo())
				.setParameter("memberNo", entity.getMemberNo()).setParameter("gpaProdCount", entity.getGpaProdCount())
				.setParameter("gpaProdPrice", entity.getGpaProdPrice())
				.setParameter("gpaProdTotal", entity.getGpaProdTotal()).setParameter("gpaSoStat", entity.getGpaSoStat())
				.setParameter("gpaBuyName", entity.getGpaBuyName()).setParameter("gpaBuyTel", entity.getGpaBuyTel())
				.setParameter("gpaBuyAdd", entity.getGpaBuyAdd()).setParameter("gpaEvaSeller", entity.getGpaEvaSeller())
				.setParameter("gpaEvaMember", entity.getGpaEvaMember()).executeUpdate();
	}

	@Override
	public GpaSo selectById(Integer id) {
		return getSession().get(GpaSo.class, id);
	}

	@Override
	public List<GpaSo> selectAll() {
		final String hql = "FROM GpaSo ORDER BY gpaSoNo";
		return getSession().createQuery(hql, GpaSo.class).getResultList();
	}

	@Override
	public List<GpaSo> selectByMemberNo(Integer memberNo) {
		final String hql = "FROM GpaSo WHERE memberNo = :memberNo ORDER BY gpaSoNo";
		return getSession().createQuery(hql, GpaSo.class).setParameter("memberNo", memberNo).getResultList();
	}

	@Override
	public List<GpaSo> selectByMemberNoAndSoStat(Integer memberNo, Integer soStat) {
		final String hql = "FROM GpaSo WHERE memberNo = :memberNo AND gpaSoStat = :gpaSoStat ORDER BY gpaSoNo DESC";
		return getSession().createQuery(hql, GpaSo.class).setParameter("memberNo", memberNo)
				.setParameter("gpaSoStat", soStat).getResultList();
	}

	@Override
	public boolean updateGpaEvaSellerByGpaSoNo(Integer gpaSoNo, Integer gpaEvaSeller) {
		final String hql = "UPDATE GpaSo set gpaEvaSeller = :gpaEvaSeller WHERE gpaSoNo = :gpaSoNo";
		getSession().createQuery(hql).setParameter("gpaEvaSeller", gpaEvaSeller).setParameter("gpaSoNo", gpaSoNo)
				.executeUpdate();
		return true;
	}
	
	@Override
	public boolean updateGpaEvaMemberByGpaSoNo(Integer gpaSoNo, Integer gpaEvaMember) {
		final String hql = "UPDATE GpaSo set gpaEvaMember = :gpaEvaMember WHERE gpaSoNo = :gpaSoNo";
		getSession().createQuery(hql).setParameter("gpaEvaMember", gpaEvaMember).setParameter("gpaSoNo", gpaSoNo)
		.executeUpdate();
		return true;
	}
	
	@Override
	public boolean updateGpaSoStatByGpaSoNo(Integer gpaSoNo, Integer gpaSoStat) {
		final String hql = "UPDATE GpaSo set gpaSoStat = :gpaSoStat WHERE gpaSoNo = :gpaSoNo";
		getSession().createQuery(hql).setParameter("gpaSoStat", gpaSoStat).setParameter("gpaSoNo", gpaSoNo)
		.executeUpdate();
		return true;
	}

	@Override
	public List<GpaSoAndGpaProdDTO> selectByGpaSoAndGpaProd(GpaSo gpaSo, GpaProd gpaProd) {
		// NativeSQL
		// 前端輸入框 訂單編號, 訂單狀態, 買家會員編號, 賣家會員編號, 總金額
		// 若輸入框有輸入值，則加入SQL的模糊查詢，若無輸入值則不加入SQL查詢
		StringBuilder nativeSql = new StringBuilder();
		nativeSql.append(
				"SELECT GPA_SO_NO, s.MEMBER_NO BUYER_MEMBER_NO, GPA_PROD_COUNT, GPA_PROD_TOTAL, GPA_SO_STAT, GPA_BUY_NAME, GPA_BUY_TEL, GPA_BUY_ADD, GPA_PROD_NAME, p.MEMBER_NO SELLER_MEMBER_NO, GPA_END_DATE FROM GPA_SO s ");
		nativeSql.append("JOIN GPA_PROD p ON s.GPA_PROD_NO = p.GPA_PROD_NO WHERE ");
		
		if (gpaSo.getGpaSoNo() != null) {
			nativeSql.append("GPA_SO_NO LIKE :gpaSoNo AND ");
		}
		if (gpaSo.getGpaSoStat() != null) {
			nativeSql.append("GPA_SO_STAT LIKE :gpaSoStat AND ");
		}
		if (gpaSo.getMemberNo() != null) { // 買家會員編號，傳入值改名為buyerMemberNo
			nativeSql.append("s.MEMBER_NO LIKE :buyerMemberNo AND ");
		}
		if (gpaProd.getMemberNo() != null) { // 賣家會員編號，傳入值改名為sellerMemberNo
			nativeSql.append("p.MEMBER_NO LIKE :sellerMemberNo AND ");
		}
		if (gpaSo.getGpaProdTotal() != null) {
			nativeSql.append("GPA_PROD_TOTAL LIKE :gpaProdTotal AND ");
		}
		
		nativeSql.append("1 ORDER BY GPA_SO_NO");
		
		// 驗證
		System.out.println("gpaSo.getGpaSoNo():" + gpaSo.getGpaSoNo());
		System.out.println("gpaSo.getGpaSoStat():" + gpaSo.getGpaSoStat());
		System.out.println("gpaSo.getMemberNo():" + gpaSo.getMemberNo());
		System.out.println("gpaProd.getMemberNo():" + gpaProd.getMemberNo());
		System.out.println("gpaSo.getGpaProdTotal():" + gpaSo.getGpaProdTotal());
		
		System.out.println("nativeSql:" + nativeSql); // 驗證組合後的SQL指令
		
//		nativeSql.append(
//				"GPA_SO_NO LIKE '%1%'");
//		nativeSql.append(
//				"GPA_SO_NO LIKE '%1%' AND GPA_SO_STAT LIKE '%2%' AND s.MEMBER_NO LIKE '%2%' AND p.MEMBER_NO LIKE '%1%' AND GPA_PROD_TOTAL LIKE '%780%'");

		Query<Object[]> query = getSession().createNativeQuery(nativeSql.toString()); // 因SQL查詢返回的欄位資料涉及多Table，單一的entity沒涵蓋所有欄位，故返回型別使用Object[]
		if (gpaSo.getGpaSoNo() != null) {
			query.setParameter("gpaSoNo", "%" + gpaSo.getGpaSoNo() + "%");
		}
		if (gpaSo.getGpaSoStat() != null) {
			query.setParameter("gpaSoStat", "%" + gpaSo.getGpaSoStat() + "%");
		}
		if (gpaSo.getMemberNo() != null) { // 買家會員編號，傳入值改名為buyerMemberNo
			query.setParameter("buyerMemberNo", "%" + gpaSo.getMemberNo() + "%");
		}
		if (gpaProd.getMemberNo() != null) { // 賣家會員編號，傳入值改名為sellerMemberNo
			query.setParameter("sellerMemberNo", "%" + gpaProd.getMemberNo() + "%");
		}
		if (gpaSo.getGpaProdTotal() != null) {
			query.setParameter("gpaProdTotal", "%" + gpaSo.getGpaProdTotal() + "%");
		}
		
		// 將List<Object[]>設置至gpaSoAndGpaProdDTOList開始
		List<Object[]> queryResultList = query.getResultList(); // 取得查詢後的List資料
		List<GpaSoAndGpaProdDTO> gpaSoAndGpaProdDTOList = new LinkedList<GpaSoAndGpaProdDTO>();
		for (Object[] obj : queryResultList) {
			GpaSoAndGpaProdDTO gpaSoAndGpaProdDTO = new GpaSoAndGpaProdDTO();
			for (int i = 0; i < obj.length; i++) {
				switch (i) {
					case 0:
						gpaSoAndGpaProdDTO.getGpaSo().setGpaSoNo((Integer) obj[i]);
						break;
					case 1:
						gpaSoAndGpaProdDTO.getGpaSo().setMemberNo((Integer) obj[i]);
						break;
					case 2:
						gpaSoAndGpaProdDTO.getGpaSo().setGpaProdCount((Integer) obj[i]);
						break;
					case 3:
						gpaSoAndGpaProdDTO.getGpaSo().setGpaProdTotal((Integer) obj[i]);
						break;
					case 4:
						gpaSoAndGpaProdDTO.getGpaSo().setGpaSoStat(Integer.parseInt(obj[i].toString()));
						break;
					case 5:
						gpaSoAndGpaProdDTO.getGpaSo().setGpaBuyName((String) obj[i]);
						break;
					case 6:
						gpaSoAndGpaProdDTO.getGpaSo().setGpaBuyTel((String) obj[i]);
						break;
					case 7:
						gpaSoAndGpaProdDTO.getGpaSo().setGpaBuyAdd((String) obj[i]);
						break;
					case 8:
						gpaSoAndGpaProdDTO.getGpaProd().setGpaProdName((String)obj[i]);
						break;
					case 9:
						gpaSoAndGpaProdDTO.getGpaProd().setMemberNo((Integer)obj[i]);
						break;
					case 10:
						gpaSoAndGpaProdDTO.getGpaProd().setGpaEndDate((Timestamp)obj[i]);
						break;
				}
			}
			gpaSoAndGpaProdDTOList.add(gpaSoAndGpaProdDTO);
			gpaSoAndGpaProdDTO = null; // 刪除DTO物件
		}
		// 將List<Object[]>設置至GpaSoAndGpaProdDTO結束

		// 傳入DTO測試
//		Query<GpaSoAndGpaProdDTO2> query = getSession().createNativeQuery(nativeSql.toString());
//		query.unwrap(org.hibernate.query.NativeQuery.class)
//		.addScalar("GPA_SO_STAT", IntegerType.INSTANCE)
//		.setResultTransformer(Transformers.aliasToBean(GpaSoAndGpaProdDTO2.class));

		return gpaSoAndGpaProdDTOList;
	}

	@Override
	public List<GpaSo> selectByGpaSoStat(Integer gpaSoStat) {
		final String hql = "FROM GpaSo WHERE gpaSoStat = :gpaSoStat ORDER BY gpaSoNo DESC";
		return getSession()
				.createQuery(hql, GpaSo.class)
				.setParameter("gpaSoStat", gpaSoStat)
				.getResultList();
	}

	@Override
	public Integer selectGpaSoCountByMemberNoAndSoStatAndSearchStr(Integer memberNo, Integer soStat, String searchStr) {
		// NativeSQL
		final String nativeSql = "SELECT COUNT(*) from GPA_SO s JOIN GPA_PROD p on s.GPA_PROD_NO = p.GPA_PROD_NO WHERE s.MEMBER_NO = :memberNo AND GPA_SO_STAT = :soStat AND (GPA_SO_NO like :searchStr OR GPA_PROD_NAME like :searchStr)";
		Object gpaSoCount = getSession()
									    .createNativeQuery(nativeSql)
									    .setParameter("memberNo", memberNo)
									    .setParameter("soStat", soStat)
									    .setParameter("searchStr","%" + searchStr + "%")
									    .uniqueResult();
		return Integer.parseInt(gpaSoCount.toString());
	}
	
	@Override
	public Integer selectGpaSoCountBySellerMemberNoAndSoStatAndSearchStr(Integer memberNo, Integer soStat, String searchStr) {
		// NativeSQL
		final String nativeSql = "SELECT COUNT(*) from GPA_SO s JOIN GPA_PROD p on s.GPA_PROD_NO = p.GPA_PROD_NO WHERE p.MEMBER_NO = :memberNo AND GPA_SO_STAT = :soStat AND (GPA_SO_NO like :searchStr OR GPA_PROD_NAME like :searchStr)";
		Object gpaSoCount = getSession()
				.createNativeQuery(nativeSql)
				.setParameter("memberNo", memberNo)
				.setParameter("soStat", soStat)
				.setParameter("searchStr","%" + searchStr + "%")
				.uniqueResult();
		return Integer.parseInt(gpaSoCount.toString());
	}

	@Override
	public List<GpaSo> selectByMemberNoAndSoStatAndLimitAndOffsetAndSearchStr(Integer memberNo, Integer soStat, Integer limit, Integer offset, String searchStr) {
		// NativeSQL
		final String nativeSql = "SELECT * from GPA_SO s JOIN GPA_PROD p on s.GPA_PROD_NO = p.GPA_PROD_NO WHERE s.MEMBER_NO = :memberNo AND GPA_SO_STAT = :soStat AND (GPA_SO_NO like :searchStr OR GPA_PROD_NAME like :searchStr) ORDER BY GPA_SO_NO DESC LIMIT :limit OFFSET :offset";
		List<GpaSo> gpaSoList = getSession()
									    .createNativeQuery(nativeSql, GpaSo.class)
									    .setParameter("memberNo", memberNo)
									    .setParameter("soStat", soStat)
									    .setParameter("limit", limit)
									    .setParameter("offset", offset)
									    .setParameter("searchStr", "%" + searchStr + "%")
									    .getResultList();
		return gpaSoList;
	}
	
	@Override
	public List<GpaSo> selectBySellerMemberNoAndSoStatAndLimitAndOffsetAndSearchStr(Integer memberNo, Integer soStat, Integer limit, Integer offset, String searchStr) {
		// NativeSQL
		final String nativeSql = "SELECT * from GPA_SO s JOIN GPA_PROD p on s.GPA_PROD_NO = p.GPA_PROD_NO WHERE p.MEMBER_NO = :memberNo AND GPA_SO_STAT = :soStat AND (GPA_SO_NO like :searchStr OR GPA_PROD_NAME like :searchStr) ORDER BY GPA_SO_NO DESC LIMIT :limit OFFSET :offset";
		List<GpaSo> gpaSoList = getSession()
				.createNativeQuery(nativeSql, GpaSo.class)
				.setParameter("memberNo", memberNo)
				.setParameter("soStat", soStat)
				.setParameter("limit", limit)
				.setParameter("offset", offset)
				.setParameter("searchStr", "%" + searchStr + "%")
				.getResultList();
		return gpaSoList;
	}
}