package com.books.app.pojo;

public class ExchangeRequestResource {

    private String senderUsername;

    private String recieverUsername;

    private String book;

    private String deliveryMethod;

    private String duration;

	public String getSenderUsername() {
		return senderUsername;
	}

	public void setSenderUsername(String senderUsername) {
		this.senderUsername = senderUsername;
	}

	public String getRecieverUsername() {
		return recieverUsername;
	}

	public void setRecieverUsername(String recieverUsername) {
		this.recieverUsername = recieverUsername;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

}
