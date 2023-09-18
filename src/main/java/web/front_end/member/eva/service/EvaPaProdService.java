package web.front_end.member.eva.service;

import java.util.List;

//import web.front_end.member.eva.entity.PaProd;
import web.front_end.member.pa.prod.entity.PaProd;

public interface EvaPaProdService {
	List<PaProd> loadByMemberNo(Integer memberNo);
}
