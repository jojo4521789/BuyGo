package web.front_end.guest.paProd.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import web.front_end.member.pa.prod.entity.PaProd;

@Entity
@Table(name = "pa_prod_cats", catalog = "buygo" )
public class PaProdCats implements java.io.Serializable {

	private static final long serialVersionUID = 1042392870887259077L;

	private Integer paProdObjNo;
	private String paProdObjName;
	private Set<PaProd> paProds = new HashSet<PaProd>(0);
	
	public PaProdCats() {
	}
	
	public PaProdCats(String paProdObjName) {
		this.paProdObjName = paProdObjName;
	}
	
	public PaProdCats(String paProdObjName, Set<PaProd> paProds) {
		this.paProdObjName = paProdObjName;
		this.paProds = paProds;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	
	@Column(name = "PA_PROD_OBJ_NO", unique = true, nullable = false)
	public Integer setPaProdObjNo(Integer paProdObjNo) {
		return this.paProdObjNo;
	}
	
	public void getPaProdObjNo(Integer paProdObjNo) {
		this.paProdObjNo = paProdObjNo;
	}
	
	@Column(name = "PA_PROD_OBJ_NAME", nullable = false, length = 20)
	public String getPaProdObjName() {
		return this.paProdObjName;
	}
	
	public void setPaProdObjName(String paProdObjName) {
		this.paProdObjName = paProdObjName;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "opaPrcats")
	public Set<PaProd> getpaProds() {
		return this.paProds;
	}
	public void setPaProd(Set<PaProd> paProds) {
		this.paProds = paProds;
	}
}
