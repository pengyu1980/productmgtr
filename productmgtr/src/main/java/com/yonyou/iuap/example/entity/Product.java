package com.yonyou.iuap.example.entity;

import com.yonyou.iuap.persistence.jdbc.framework.annotation.Column;
import com.yonyou.iuap.persistence.jdbc.framework.annotation.Entity;
import com.yonyou.iuap.persistence.jdbc.framework.annotation.GeneratedValue;
import com.yonyou.iuap.persistence.jdbc.framework.annotation.Id;
import com.yonyou.iuap.persistence.jdbc.framework.annotation.Stragegy;
import com.yonyou.iuap.persistence.jdbc.framework.annotation.Table;
import com.yonyou.iuap.persistence.vo.BaseEntity;
import java.util.Date;
	

/**
 * The persistent class for the good_demo database table.
 * 
 */
@Entity
@Table(name="good_demo")
public class Product extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "productid")
	@GeneratedValue(strategy=Stragegy.UUID,moudle="example_demo")
	private String productid;
	@Column(name = "productname")
	private String productname;
	@Column(name = "supplier")
	private String supplier;
	@Column(name = "prodate")
	private Date prodate;
	@Column(name = "orgin")
	private String orgin;
	@Column(name = "ts")
	private Date ts;

	public Product() {
	}


	public String getProductid() {
		return this.productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}


	public String getProductname() {
		return this.productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}


	public String getSupplier() {
		return this.supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}


	public Date getProdate() {
		return this.prodate;
	}

	public void setProdate(Date prodate) {
		this.prodate = prodate;
	}


	public String getOrgin() {
		return this.orgin;
	}

	public void setOrgin(String orgin) {
		this.orgin = orgin;
	}


	public Date getTs() {
		return this.ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}

	@Override
    public String getMetaDefinedName() {
        return "good_demo";
    }

    @Override
    public String getNamespace() {
		return "com.yonyou.iuap.example.entity";
    }
}