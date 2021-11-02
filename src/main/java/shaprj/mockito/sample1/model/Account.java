package shaprj.mockito.sample1.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private UUID uuid;
    private UUID customerUuid;
    private AccountCurrency currency;
    private BigDecimal amount;
}
