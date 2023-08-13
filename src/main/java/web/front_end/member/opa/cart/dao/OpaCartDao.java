package web.front_end.member.opa.cart.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.opa.cart.entity.OpaCart;
import web.front_end.member.opa.cart.entity.OpaCartPrimaryKey;

public interface OpaCartDao extends CoreDao<OpaCart, OpaCartPrimaryKey> {

    OpaCart getById(OpaCartPrimaryKey id);
    int deleteById(OpaCartPrimaryKey id);

    List<OpaCart> getAll();

    List<OpaCart> getByMember(Integer memberNo);

    void saveOpaCart(OpaCart opaCart);

    void updateOpaCart(OpaCart opaCart);

    void deleteOpaCart(OpaCart opaCart);
}