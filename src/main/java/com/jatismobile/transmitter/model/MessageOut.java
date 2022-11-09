package com.jatismobile.transmitter.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Id;

@Entity
@Table(name = "messageout")
public class MessageOut {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "wa_message_id")
	private String wa_message_id;
	
	@Column(name = "message_id")
	private String message_id;
	
	@Column(name = "recipient_type")
	private String recipient_type;
	
	@Column(name = "msisdn")
	private String msisdn;
	
	@Column(name = "sender")
	private String sender;
	
	@Column(name = "message", columnDefinition = "TEXT")
	private String message;
	
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime created_at;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWa_message_id() {
		return wa_message_id;
	}

	public void setWa_message_id(String wa_message_id) {
		this.wa_message_id = wa_message_id;
	}

	public String getMessage_id() {
		return message_id;
	}

	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	public String getRecipient_type() {
		return recipient_type;
	}

	public void setRecipient_type(String recipient_type) {
		this.recipient_type = recipient_type;
	}
	
	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	
	public String getsender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getMessage() {
		return sender;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	
	
}