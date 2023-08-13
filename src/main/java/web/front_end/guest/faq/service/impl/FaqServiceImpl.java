package web.front_end.guest.faq.service.impl;

import java.util.List;

import web.front_end.guest.faq.dao.FaqDao;
import web.front_end.guest.faq.dao.impl.FaqDaoImpl;
import web.front_end.guest.faq.entity.Faq;
import web.front_end.guest.faq.service.FaqService;

public class FaqServiceImpl implements FaqService{
	private FaqDao dao;
	public FaqServiceImpl() {
		dao = new FaqDaoImpl();
		
	}
	
	@Override
	public Faq increase(Faq faq) {
		if(faq.getFaqTitle() == null) {
			faq.setMessage("尚未輸入標題");
			faq.setSuccessful(false);
			return faq;
		}
		if(faq.getFaqContent() == null) {
			faq.setMessage("請輸入內容");
			faq.setSuccessful(false);
			return faq;
		}
		faq.setMessage("新增成功");
		faq.setSuccessful(true);
		return faq;
	}

	@Override
	public Faq update(Faq faq) {
		
		return null;
	}

	@Override
	public Faq show(Faq faq) {
		return null;
	}

	@Override
	public List<Faq> showall() {
		return dao.selectAll();
	}

	@Override
	public boolean save(Faq faq) {
		return dao.update(faq) > 0;
	}

	@Override
	public boolean remove(Integer faq) {
		return dao.deleteById(faq) > 0;
	}


}
