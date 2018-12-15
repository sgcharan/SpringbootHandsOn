package com.sampleProject.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;
@Component
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"id","ordernumber","invoiceid"}))
public class Orders {
	
	
	public Orders(){
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Long id;
	
	private String ordernumber;
	private String invoiceid;


	public String getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(String invoiceid) {
		this.invoiceid = invoiceid;
	}

	
	
	public String getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}

	@CreationTimestamp	
	private Date createdtimestamp;
	
	@UpdateTimestamp
	private Date updatedtimestamp;


	@Override
	public String toString() {
		return "Orders [ordernumber=" + ordernumber + ", invoiceid=" + invoiceid + ", createdtimestamp="
				+ createdtimestamp + ", updatedtimestamp=" + updatedtimestamp + "]";
	}

	public Orders(String ordernumber, String invoiceid, Date createdtimestamp, Date updatedtimestamp) {
		super();
		this.ordernumber = ordernumber;
		this.invoiceid = invoiceid;
		this.createdtimestamp = createdtimestamp;
		this.updatedtimestamp = updatedtimestamp;
	}
	
	
}
