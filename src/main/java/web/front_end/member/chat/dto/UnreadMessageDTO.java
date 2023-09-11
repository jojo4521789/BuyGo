package web.front_end.member.chat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UnreadMessageDTO {
	private String otherMemberNo;
	private String numOfMsg;

	public UnreadMessageDTO(String otherMemberNo, String numOfMsg) {
		this.otherMemberNo = otherMemberNo;
		this.numOfMsg = numOfMsg;
	}
}
