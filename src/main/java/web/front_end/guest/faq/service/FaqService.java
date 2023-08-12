package web.front_end.guest.faq.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.guest.faq.entity.Faq;

public interface FaqService extends CoreService{
	Faq increase (Faq faq);
	Faq update (Faq faq);
	Faq show (Faq faq);
	List<Faq> showall();
	boolean save (Faq faq); 
	boolean remove (Integer faq);
}
