package org.grimlock.admin.server;
public class Sms {
	String phone;
	String content;

	public Sms() {
	}

	public Sms(String phone, String content) {
		this.phone = phone;
		this.content = content;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}