package com.sampleProject.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"id","name","username"}))
public class Products {
	
	public Products() {
		
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	private String name;
	
	private float price;
	private String weight;
	private String added;
	private String imgUrl;
	
	private String forusername;
	private String orderNumber;



	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getForusername() {
		return forusername;
	}

	public void setForusername(String forusername) {
		this.forusername = forusername;
	}



	private String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAdded() {
		return added;
	}

	public void setAdded(String added) {
		this.added = added;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}





	@Override
	public String toString() {
		return "Products [name=" + name + ", price=" + price + ", weight=" + weight + ", added=" + added + ", imgUrl="
				+ imgUrl + ", forusername=" + forusername + ", orderNumber=" + orderNumber + ", username="
				+ username + "]";
	}

	public Products(String name, float price, String weight, String added, String imgUrl, String username) {
		super();
		this.name = name;
		this.price = price;
		this.weight = weight;
		this.added = added;
		this.imgUrl = imgUrl;
		this.username = username;
	}


}
