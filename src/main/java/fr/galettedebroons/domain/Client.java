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
	private String customerAddress_;

	public String getCustomerCode() {
		return customerCode_;
	}
	public void setCustomerCode(String customerCode_) {
		this.customerCode_ = customerCode_;
	}
	public String getCustomerTeaches() {
		return customerTeaches_;
	}
	public void setCustomerTeaches(String customerTeaches_) {
		this.customerTeaches_ = customerTeaches_;
	}
	public String getCustomerAddress() {
		return customerAddress_;
	}
	public void setCustomerAddress(String customerAddress_) {
		this.customerAddress_ = customerAddress_;
	}
	
	
}