package web.front_end.member.opa.order.entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class OpaOrderdetailsId implements java.io.Serializable {

	private static final long serialVersionUID = 1531947311440472005L;
	private int opaSoNo;
	private int opaProdNo;

	public OpaOrderdetailsId() {
	}

	public OpaOrderdetailsId(int opaSoNo, int opaProdNo) {
		this.opaSoNo = opaSoNo;
		this.opaProdNo = opaProdNo;
	}

	@Column(name = "OPA_SO_NO", nullable = false)
	public int getOpaSoNo() {
		return this.opaSoNo;
	}

	public void setOpaSoNo(int opaSoNo) {
		this.opaSoNo = opaSoNo;
	}

	@Column(name = "OPA_PROD_NO", nullable = false)
	public int getOpaProdNo() {
		return this.opaProdNo;
	}

	public void setOpaProdNo(int opaProdNo) {
		this.opaProdNo = opaProdNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OpaOrderdetailsId))
			return false;
		OpaOrderdetailsId castOther = (OpaOrderdetailsId) other;

		return (this.getOpaSoNo() == castOther.getOpaSoNo()) && (this.getOpaProdNo() == castOther.getOpaProdNo());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getOpaSoNo();
		result = 37 * result + this.getOpaProdNo();
		return result;
	}

}