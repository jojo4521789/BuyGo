package web.front_end.guest.prod.service.impl;

import java.util.List;

import web.front_end.guest.prod.dao.OpaPrpicsDao;
import web.front_end.guest.prod.dao.ProdDao;
import web.front_end.guest.prod.dao.impl.OpaPrcatsDaoImpl;
import web.front_end.guest.prod.dao.impl.OpaPrpicsDaoImpl;
import web.front_end.guest.prod.dao.impl.ProdDaoImpl;
import web.front_end.guest.prod.entity.OpaPrcats;
import web.front_end.guest.prod.entity.OpaProducts;
import web.front_end.guest.prod.entity.OpaPrpics;
import web.front_end.guest.prod.service.ProdDaoService;

public class ProdDaoServiceImpl implements ProdDaoService {
	private OpaPrcatsDaoImpl opaPrcatsDao;
    private ProdDao opaProductsDao;
    private OpaPrpicsDao opaPrpicsDao;

    public ProdDaoServiceImpl() {
        opaPrcatsDao = new OpaPrcatsDaoImpl(); 
        opaProductsDao = new ProdDaoImpl();
        opaPrpicsDao = new OpaPrpicsDaoImpl();
    }

    public List<OpaPrcats> findAllCategory() {
        List<OpaPrcats> opaPrcats = opaPrcatsDao.selectAll();
        return opaPrcats;
    }


    public List<OpaProducts> findAllProducts(String[] categoryFilters, String minPrice, String maxPrice, String page, int productPerPage) {
        List<OpaProducts> opaProducts = opaProductsDao.selectAll(categoryFilters, minPrice, maxPrice, page, productPerPage);
        return opaProducts;
    }

    public byte[] findProductPicture(int opaProdNo) {
        OpaPrpics opaPrpic = opaPrpicsDao.selectById(opaProdNo);
        if (opaPrpic != null)
            return opaPrpic.getOpaProdPicture();
        return null;
    }

    public Long countProducts(String[] categoryFilters, String minPrice, String maxPrice) {
        return opaProductsDao.countProducts(categoryFilters, minPrice, maxPrice);
    }
}
