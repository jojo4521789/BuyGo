package web.front_end.member.chat.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CHATROOM_MESSAGE")
public class ChatroomMessage extends Core{
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MESSAGE_NO")
    private Integer messageNo;

    @Column(name = "MEMBER_NO_SEND")
    private Integer memberNoSend;

    @Column(name = "MEMBER_NO_REC")
    private Integer memberNoRec;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "MESSAGE_TIME", insertable = false)
    private Timestamp messageTime;
}
