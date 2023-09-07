package web.front_end.member.chat.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ChatMessage {
	private String type;
	private String sender;
	private String receiver;
	private String message;
	private String time;

	public ChatMessage(String type, String sender, String receiver, String message, String time) {
		this.type = type;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.time = time;
	}
	
	public ChatMessage(String type, String sender, String receiver, String message) {
		this.type = type;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
	}
}
