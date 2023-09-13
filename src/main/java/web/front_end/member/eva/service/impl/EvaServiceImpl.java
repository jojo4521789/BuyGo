package web.front_end.member.eva.service.impl;

import java.util.LinkedList;
import java.util.List;

import web.front_end.member.eva.dao.EvaPaSoDao;
import web.front_end.member.eva.dao.impl.EvaPaSoDaoImpl;
//import web.front_end.member.eva.entity.PaProdPic;
//import web.front_end.member.eva.entity.PaSo;
import web.front_end.member.pa.order.entity.PaSo;
//import web.front_end.member.eva.entity.PaSoDetails;
import web.front_end.member.pa.order.entity.PaSoDetails;
import web.front_end.member.eva.service.EvaService;
import web.front_end.member.pa.prod.entity.PaProd;
import web.front_end.member.pa.prodpic.entity.ProdPic;

public class EvaServiceImpl implements EvaService {
	private EvaPaSoDao dao;

	public EvaServiceImpl() {
		dao = new EvaPaSoDaoImpl();
	}

	@Override
	public List<PaSo> loadPaSoBySellerMemberNoAndPaSoStatus(Integer memberNo, Integer paSoStatus) {
		List<PaSo> paSoList = dao.selectAll(); // 將取回所有的PaSo
		
		List<PaSo> newPaSoList = new LinkedList<PaSo>(); // 新建立的List，將過濾後的PaSo保存後回傳
//		String paProdPicToBase64; // 用於保存轉換成Base64的圖片
		for(PaSo paSo : paSoList) { // 遍歷paSoList
			boolean isPaSoAdd = false; // 用於判斷是否將此paSo加入newPaSoList
			for(PaSoDetails paSoDetails : paSo.getPaSoDetails()) { // 遍歷
				if((paSoDetails.getPaProd().getMemberNo().equals(memberNo)) && (Integer.valueOf(paSo.getPaSoStatus()) == paSoStatus)) { // 如果該筆So存在指定賣家的memberNo 且 paSoStatus為指定，將該筆paSo存入newPaSoList
					isPaSoAdd = true; // 如果該paSo的(Seller)memberNo符合，則將此boolean改為true，供後續加入此paSo至newPaSoList
					for(ProdPic paProdPic : paSoDetails.getPaProd().getPaProdPic()) { // 遍歷PaProdPic，並將符合(Seller)memberNo的圖片轉換為Base64後放入paSoList
						try {
//							paProdPicToBase64 = Base64EncoderByByte(paProdPic.getPaProdPic());
//							paProdPic.setPaProdPicToBase64(paProdPicToBase64);
							paProdPic.setPaProdPicToBase64(paProdPic.getPaProdPic());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			if(isPaSoAdd) {
				newPaSoList.add(paSo);
			}
		}
		return newPaSoList;
	}
}
