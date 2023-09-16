package web.back_end.opa.req.entity; 


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "opa_request", catalog = "buygo")
public class OpaRequest implements java.io.Serializable {

	private static final long serialVersionUID = 5432766975786253889L;
	private Integer opaRequestNo;
	private int memberNo;
	private String opaRequestProductsName;
	private String opaRequestProductsUrl;
	private String opaRequestProductsContent;
	private byte opaRequestStatus;
	private Date opaRequestStartdate;
	private Integer opaFailedReason;

	public OpaRequest() {
	}

	public OpaRequest(int memberNo, String opaRequestProductsName, String opaRequestProductsUrl,
			String opaRequestProductsContent, byte opaRequestStatus, Date opaRequestStartdate, Integer opaFailedReason) {
		this.memberNo = memberNo;
		this.opaRequestProductsName = opaRequestProductsName;
		this.opaRequestProductsUrl = opaRequestProductsUrl;
		this.opaRequestProductsContent = opaRequestProductsContent;
		this.opaRequestStatus = opaRequestStatus;
		this.opaRequestStartdate = opaRequestStartdate;
		this.opaFailedReason = opaFailedReason;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "OPA_REQUEST_NO", unique = true, nullable = false)
	public Integer getOpaRequestNo() {
		return this.opaRequestNo;
	}

	public void setOpaRequestNo(Integer opaRequestNo) {
		this.opaRequestNo = opaRequestNo;
	}

	@Column(name = "MEMBER_NO", nullable = false)
	public int getMemberNo() {
		return this.memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	@Column(name = "OPA_REQUEST_PRODUCTS_NAME", nullable = false, length = 40)
	public String getOpaRequestProductsName() {
		return this.opaRequestProductsName;
	}

	public void setOpaRequestProductsName(String opaRequestProductsName) {
		this.opaRequestProductsName = opaRequestProductsName;
	}

	@Column(name = "OPA_REQUEST_PRODUCTS_URL", nullable = false, length = 65535)
	public String getOpaRequestProductsUrl() {
		return this.opaRequestProductsUrl;
	}

	public void setOpaRequestProductsUrl(String opaRequestProductsUrl) {
		this.opaRequestProductsUrl = opaRequestProductsUrl;
	}

	@Column(name = "OPA_REQUEST_PRODUCTS_CONTENT", nullable = false, length = 65535)
	public String getOpaRequestProductsContent() {
		return this.opaRequestProductsContent;
	}

	public void setOpaRequestProductsContent(String opaRequestProductsContent) {
		this.opaRequestProductsContent = opaRequestProductsContent;
	}

	@Column(name = "OPA_REQUEST_STATUS", nullable = false)
	public byte getOpaRequestStatus() {
		return this.opaRequestStatus;
	}

	public void setOpaRequestStatus(byte opaRequestStatus) {
		this.opaRequestStatus = opaRequestStatus;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OPA_REQUEST_STARTDATE", nullable = false, length = 19)
	public Date getOpaRequestStartdate() {
		return this.opaRequestStartdate;
	}

	public void setOpaRequestStartdate(Date opaRequestStartdate) {
		this.opaRequestStartdate = opaRequestStartdate;
	}

	@Column(name = "OPA_REQUEST_REASON", nullable = true)
	public Integer getOpaFailedReason() {
        return this.opaFailedReason;
    }

	public void setOpaFailedReason(Integer opaFailedReason) {
        this.opaFailedReason = opaFailedReason;
    }
}
