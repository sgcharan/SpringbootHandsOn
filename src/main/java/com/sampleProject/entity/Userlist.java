package com.sampleProject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"username","othersemail"}))
public class Userlist {
	
	public Userlist(){
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	private String others;
	private String othersaddress;
	private String othersemail;
	
	@Digits(integer=10, fraction=0,message="Contact number has to be Numeric and length of 10")
	private String otherscontact;
	
	
	public String getOthersaddress() {
		return othersaddress;
	}
	public void setOthersaddress(String othersaddress) {
		this.othersaddress = othersaddress;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	
	
	public String getOthersemail() {
		return othersemail;
	}
	public void setOthersemail(String othersemail) {
		this.othersemail = othersemail;
	}

	public String getOtherscontact() {
		return otherscontact;
	}
	public void setOtherscontact(String otherscontact) {
		this.otherscontact = otherscontact;
	}
	@Override
	public String toString() {
		return "Userlist [username=" + username + ", others=" + others + ", othersaddress=" + othersaddress
				+ ", othersemail=" + othersemail + ", otherscontact=" + otherscontact + "]";
	}
	public Userlist(String username, String others, String othersaddress, String othersemail, String otherscontact) {
		super();
		this.username = username;
		this.others = others;
		this.othersaddress = othersaddress;
		this.othersemail = othersemail;
		this.otherscontact = otherscontact;
	}
	
	
	
	
	
}
