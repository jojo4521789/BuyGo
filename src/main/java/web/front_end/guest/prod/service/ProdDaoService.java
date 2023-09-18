package web.front_end.guest.prod.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.guest.prod.entity.OpaProducts;
import web.front_end.guest.prod.entity.OpaPrcats;


public interface ProdDaoService extends CoreService {

    public List<OpaPrcats> findAllCategory();

    public List<OpaProducts> findAllProducts(String[] categoryFilters, String minPrice, String maxPrice, String page, int productPerPage);
    
    public byte[] findProductPicture(int opaProdNo);

    public Long countProducts(String[] categoryFilters, String minPrice, String maxPrice);
}
