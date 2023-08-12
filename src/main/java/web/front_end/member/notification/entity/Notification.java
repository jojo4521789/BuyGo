package web.front_end.member.notification.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification extends Core {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NOTIFI_NO")
	private Integer notifiNo;
	@Column(name = "MEMBER_NO")
	private Integer memberNo;
	@Column(name = "NOTIFI_TITLE")
	private String notifiTitle;
	@Column(name = "NOTIFI_CONTENT")
	private String notifiContent;
	@Column(name = "NOTIFI_TIME")
	private Timestamp notifiTime;
	@Column(name = "NOTIFI_STATUS")
	private Integer notifiStatus;
	
	public Notification (Integer notifiStatus) {
		this.notifiStatus = notifiStatus;
	}
}
