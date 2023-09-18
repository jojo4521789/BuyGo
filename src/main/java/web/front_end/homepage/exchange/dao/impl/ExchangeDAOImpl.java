package web.front_end.homepage.exchange.dao.impl;

import java.util.List;

import web.front_end.homepage.exchange.dao.ExchangeDAO;
import web.front_end.homepage.exchange.entity.Exchange;

public class ExchangeDAOImpl implements ExchangeDAO{

	@Override
	public int insert(Exchange exchange) {
		getSession().persist(exchange);
		return 1;
	}
	
	public Exchange selectByDateExchange(Exchange dateExchange) {
		return getSession().get(Exchange.class, dateExchange);
	}
	
	@Override
	public Exchange selectById(Integer id) {
		return null;
	}

	@Override
	public int deleteById(Integer id) {
		return 0;
	}

	@Override
	public int update(Exchange pojo) {
		return 0;
	}

	@Override
	public List<Exchange> selectAll() {
		return null;
	}

}
