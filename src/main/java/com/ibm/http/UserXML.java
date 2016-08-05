package com.ibm.http;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserXML {

	private Long id;
	private String name;
	private String email;
	private boolean status;

	public UserXML(Long id, String name, String email, boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
