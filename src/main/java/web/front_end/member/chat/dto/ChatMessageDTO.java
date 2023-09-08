package web.front_end.member.chat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ChatMessageDTO {
	private String type;
	private String sender;
	private String receiver;
	private String message;
	private String time;
	private String unreadMsgCount;

	public ChatMessageDTO(String type, String sender, String receiver, String message, String time) {
		this.type = type;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.time = time;
	}
	
	public ChatMessageDTO(String type, String sender, String receiver, String message) {
		this.type = type;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
	}
}
