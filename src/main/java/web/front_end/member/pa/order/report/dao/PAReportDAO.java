package web.front_end.member.pa.order.report.dao;

import core.dao.CoreDao;
import web.front_end.member.pa.order.report.entity.PAReport;

public interface PAReportDAO extends CoreDao<PAReport, Integer>{
	PAReport selectByPARpNo(String paRpNo);
}
