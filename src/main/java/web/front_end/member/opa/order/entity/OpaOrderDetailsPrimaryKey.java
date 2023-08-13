package web.front_end.member.opa.order.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class OpaOrderDetailsPrimaryKey implements Serializable {

    private static final long serialVersionUID = 8041161673682381243L;

    private Integer opaSoNo; // 平台訂單編號

    private Integer opaProdNo; // 平台商品編號

}