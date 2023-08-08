package web.front_end.member.gpa.track.dao.impl;

import java.util.List;

import org.hibernate.Session;

import web.front_end.member.gpa.track.dao.GpaTrackDao;
import web.front_end.member.gpa.track.entity.GpaTrack;

public class GpaTrackDaoImpl implements GpaTrackDao{

	@Override
	public int insert(GpaTrack entity) {
		getSession().persist(entity);
		return 1;
	}

	@Override
	public int deleteById(Integer gpaProdNo) {
		Session session = getSession();
		GpaTrack gpaTrack = session.get(GpaTrack.class, gpaProdNo);
		session.remove(gpaTrack);
		return 1;
	}

	@Override
	public int update(GpaTrack gpaTrack) {
		Session session = getSession();
		GpaTrack oldGpaTrack = session.get(GpaTrack.class, gpaTrack.getMemberNo());
		final Integer getGpaProdNo = gpaTrack.getGpaProdNo();
		if(getGpaProdNo != null) {
			oldGpaTrack.setGpaProdNo(getGpaProdNo);
		}
		return 1;
	}

	@Override
	public GpaTrack selectById(Integer gpaProdNo) {
		return getSession().get(GpaTrack.class, gpaProdNo);
	}

	@Override
	public List<GpaTrack> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
