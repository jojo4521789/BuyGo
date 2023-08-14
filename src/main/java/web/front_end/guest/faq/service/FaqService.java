package web.front_end.guest.faq.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.guest.faq.entity.Faq;

public interface FaqService extends CoreService{
	Faq increase (Faq faq);
	Faq update (Faq faq);
	List<Faq> show (String input);
	List<Faq> showall();
	boolean save (Faq faq); 
	boolean remove (Integer faq);
}
