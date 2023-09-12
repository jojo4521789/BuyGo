package web.front_end.guest.prod.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.guest.prod.entity.OpaProducts;

public interface ProdDao extends CoreDao<OpaProducts, Integer> {
	public List<OpaProducts> selectAll(String[] categoryFilters, String minPrice, String maxPrice, String page, int productPerPage);
    public Long countProducts(String[] categoryFilters, String minPrice, String maxPrice);
	    
}