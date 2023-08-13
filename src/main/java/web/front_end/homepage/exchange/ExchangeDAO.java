package web.front_end.homepage.exchange;

import core.dao.CoreDao;

public interface ExchangeDAO extends CoreDao<Exchange, Integer>{

	Exchange selectByDateExchange(Exchange dateExchange);

}
