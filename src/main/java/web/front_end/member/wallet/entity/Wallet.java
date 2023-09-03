package web.front_end.member.wallet.entity;
import core.entity.Core;
import lombok.*;

@Getter @Setter @AllArgsConstructor
public class Wallet extends Core {
    private int currentBalance;
    private int pendingTransaction;
    private int totalBalance;
}
