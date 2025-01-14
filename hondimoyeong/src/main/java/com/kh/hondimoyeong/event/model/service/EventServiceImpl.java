package com.kh.hondimoyeong.event.model.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.kh.hondimoyeong.common.model.vo.PageInfo;
import com.kh.hondimoyeong.event.model.mapper.EventMapper;
import com.kh.hondimoyeong.event.model.vo.Event;
import com.kh.hondimoyeong.event.model.vo.EventImg;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

	private final EventMapper eventMapper;

	@Override
	public int selectListCount() {
		return eventMapper.selectListCount();
	}

	@Override
	public List<Event> allEvents(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return eventMapper.allEvents(rowBounds);
	}

	@Override
	public Event selectEvent(int eventNo) {
		return eventMapper.selectEvent(eventNo);
	}

	@Override
	public List<EventImg> selectEventImg(int eventNo) {
		return eventMapper.selectEventImg(eventNo);
	}

	@Override
	public int increaseCount(int eventNo) {
		return eventMapper.increaseCount(eventNo);
	}

	@Override
	public int insert(Event event) {
		return eventMapper.insert(event);
	}

	@Override
	public int insertImg(EventImg eventImg) {
		return eventMapper.insertImg(eventImg);
	}

	@Override
	public int update(Event event) {
		return eventMapper.update(event);
	}

	@Override
	public int updateImg(EventImg eventImg) {
		return eventMapper.updateImg(eventImg);
	}

	@Override
	public List<EventImg> selectEventImgs(int eventNo) {
		return eventMapper.selectEventImgs(eventNo);
	}

	@Override
	public int delete(int eventNo) {
		return eventMapper.delete(eventNo);
	}
}
