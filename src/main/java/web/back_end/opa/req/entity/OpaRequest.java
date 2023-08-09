package web.back_end.opa.req.entity; 

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.entity.Core;
import lombok.Data;

@Data
@Entity
@Table(name = "OPA_REQUEST") // 表格名稱
public class OpaRequest extends Core  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPA_REQUEST_NO") // 平臺委託單編號
    private Integer opaRequestNo;

    @Column(name = "MEMBER_NO") // 會員編號
    private Integer memberNo;

    @Column(name = "OPA_REQUEST_PRODUCTS_NAME") // 平臺委託商品名稱
    private String opaRequestProductsName;

    @Column(name = "OPA_REQUEST_PRODUCTS_URL") // 平臺委託商品網址
    private String opaRequestProductsUrl;

    @Column(name = "OPA_REQUEST_PRODUCTS_CONTENT") // 平臺委託商品內容
    private String opaRequestProductsContent;

    @Column(name = "OPA_REQUEST_STATUS") // 平臺委託單審核狀態
    private Integer opaRequestStatus;

    @Column(name = "OPA_REQUEST_STARTDATE") // 平臺委託單建立日期
    private Timestamp opaRequestStartDate;
}