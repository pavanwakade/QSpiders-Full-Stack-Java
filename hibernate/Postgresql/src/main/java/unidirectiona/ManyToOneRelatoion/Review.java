package unidirectiona.ManyToOneRelatoion;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	

	

	@Id
	private int id;
	
	private String msg;
	
	@ManyToOne
	private Product product;

	public Review() {
		super();
	}
	
	
	public int getId() {
		return id;
	}

	public String getMsg() {
		return msg;
	}

	public Product getProduct() {
		return product;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
	public String toString() {
		return "Review [id=" + id + ", msg=" + msg + "]";
	}
	
}
