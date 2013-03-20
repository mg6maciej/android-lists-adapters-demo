package pl.mg6.programistamag.linad.model;

import android.graphics.Bitmap;

public class ChatMessage {

	public ChatMessage(String author, String message, Bitmap avatar) {
		this.author = author;
		this.message = message;
		this.avatar = avatar;
	}

	private String author;
	private String message;
	private Bitmap avatar;

	public String getAuthor() {
		return author;
	}

	public String getMessage() {
		return message;
	}

	public Bitmap getAvatar() {
		return avatar;
	}
}
