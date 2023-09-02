package web.front_end.guest.faq.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import web.front_end.guest.faq.dao.FaqDao;
import web.front_end.guest.faq.dao.impl.FaqDaoImpl;
import web.front_end.guest.faq.entity.Faq;
import web.front_end.guest.faq.service.FaqService;
@Service
public class FaqServiceImpl implements FaqService{
	
	private FaqDao dao;
	public FaqServiceImpl() {
		dao = new FaqDaoImpl();
		
	}
	
	@Override
	public Faq increase(Faq faq) {
		if(faq.getFaqTitle() == null) {
			faq.setMessage("請輸入標題");
			faq.setSuccessful(false);
			return faq;
		}
		if(faq.getFaqContent() == null) {
			faq.setMessage("請輸入內容");
			faq.setSuccessful(false);
			return faq;
		}
		final int resultcount = dao.insert(faq);
		if(resultcount != 1) {
			faq.setMessage("新增錯誤");
			faq.setSuccessful(false);
			return faq;
		}
		faq.setMessage("新增成功");
		faq.setSuccessful(true);
		return faq;
	}

	@Override
	public Faq update(Faq faq) {
//		final Faq oldFaq = dao.selectById(faq.getFaqNo()); 
//		faq.setFaqTitle(oldFaq.getFaqTitle());
//		faq.setFaqContent(oldFaq.getFaqContent());
		System.out.println(faq);
		final int resultCount = dao.update(faq);
		faq.setSuccessful(resultCount > 0);
		faq.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		return faq;
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

	@Override
	public List<Faq> show(String input) {
		return dao.selectByInput(input);
	}

}
