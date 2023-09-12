package web.front_end.guest.prod.entity;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "opa_prcats", catalog = "buygo")
public class OpaPrcats implements java.io.Serializable {

	private static final long serialVersionUID = -2915913482543009374L;
	private Integer opaPrcatsNo;
	private String opaPrcatsName;
	private Set<OpaProducts> opaProductses = new HashSet<OpaProducts>(0);

	public OpaPrcats() {
	}

	public OpaPrcats(String opaPrcatsName) {
		this.opaPrcatsName = opaPrcatsName;
	}

	public OpaPrcats(String opaPrcatsName, Set<OpaProducts> opaProductses) {
		this.opaPrcatsName = opaPrcatsName;
		this.opaProductses = opaProductses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "OPA_PRCATS_NO", unique = true, nullable = false)
	public Integer getOpaPrcatsNo() {
		return this.opaPrcatsNo;
	}

	public void setOpaPrcatsNo(Integer opaPrcatsNo) {
		this.opaPrcatsNo = opaPrcatsNo;
	}

	@Column(name = "OPA_PRCATS_NAME", nullable = false, length = 20)
	public String getOpaPrcatsName() {
		return this.opaPrcatsName;
	}

	public void setOpaPrcatsName(String opaPrcatsName) {
		this.opaPrcatsName = opaPrcatsName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "opaPrcats")
	public Set<OpaProducts> getOpaProductses() {
		return this.opaProductses;
	}

	public void setOpaProductses(Set<OpaProducts> opaProductses) {
		this.opaProductses = opaProductses;
	}

}
