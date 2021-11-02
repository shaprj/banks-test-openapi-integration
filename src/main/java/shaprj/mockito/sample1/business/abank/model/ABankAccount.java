package shaprj.mockito.sample1.business.abank.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ABankAccount {
    private UUID account;
    private ABankCurrency currency;
    private BigDecimal amount;
    private ABankCard card;
}
