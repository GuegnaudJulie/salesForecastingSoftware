package fr.galettedebroons.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client {

	@Id @Column(name="code_client")
	private String customerCode_;
	
	@Column(name="enseingne_client")
	private String customerTeaches_;
	
	@Column(name="adresse_client")
	private String customerAddress;

	public String getCustomerCode_() {
		return customerCode_;
	}

	public void setCustomerCode_(String customerCode_) {
		this.customerCode_ = customerCode_;
	}

	public String getCustomerTeaches_() {
		return customerTeaches_;
	}

	public void setCustomerTeaches_(String customerTeaches_) {
		this.customerTeaches_ = customerTeaches_;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	
	
}
