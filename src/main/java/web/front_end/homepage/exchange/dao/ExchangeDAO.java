package web.front_end.homepage.exchange.dao;

import core.dao.CoreDao;
import web.front_end.homepage.exchange.entity.Exchange;

public interface ExchangeDAO extends CoreDao<Exchange, Integer>{

	Exchange selectByDateExchange(Exchange dateExchange);

}
