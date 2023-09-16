package web.front_end.member.forum.service.impl;

import java.util.List;

import web.front_end.member.forum.dao.RpArticleMsgDao;
import web.front_end.member.forum.dao.impl.RpArticleMsgDaoImpl;
import web.front_end.member.forum.entity.RpArticleMsg;
import web.front_end.member.forum.service.RpArticleMsgService;

public class RpArticleMsgServiceImpl implements RpArticleMsgService {
	private RpArticleMsgDao dao;

	public RpArticleMsgServiceImpl() {
		dao = new RpArticleMsgDaoImpl();
	}

	@Override
	public RpArticleMsg add(Integer rpmemberNo, RpArticleMsg rpArticlerpMsg) {
		if (rpArticlerpMsg.getRpContent() == null) {
			rpArticlerpMsg.setMessage("檢舉文章留言內容未輸入");
			rpArticlerpMsg.setSuccessful(false);
			return rpArticlerpMsg;
		}
		rpArticlerpMsg.setRpMemberNo(rpmemberNo);
		rpArticlerpMsg.setMessage("檢舉新增成功");
		rpArticlerpMsg.setSuccessful(true);

		dao.insert(rpArticlerpMsg);
		return rpArticlerpMsg;
	}

	@Override
	public RpArticleMsg update(RpArticleMsg rpArticlerpMsg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RpArticleMsg> finAll() {
		List<RpArticleMsg> listrArticleMsgs = dao.selectAll();
		return listrArticleMsgs;
	}

	@Override
	public boolean remove(Integer rpMsgNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateByRpArticleMsgNo(Integer rpMsgNo, Integer msgeStat, Integer auditResult) {
		return dao.updateByRpArticleMsgNo(rpMsgNo, msgeStat, auditResult) > 0;
	}

}
