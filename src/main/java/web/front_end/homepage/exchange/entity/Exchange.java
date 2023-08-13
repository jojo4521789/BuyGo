package web.front_end.homepage.exchange.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.entity.Core;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "EXCHANGE_RATE", catalog = "buygo")
public class Exchange extends Core{
	
	private static final long serialVersionUID = -5086681722748224858L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EXCHANGE_RATE_NO")
	private Integer exchangeRateNo;
    private String currency;
    @Column(name = "EXCHANGE_RATE")
    private double exchangeRate;
    @Column(name = "DATE_EXCHANGE", insertable = false)
    private Timestamp dateExchange;

}
