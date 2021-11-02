package shaprj.mockito.sample1.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BankTransfer {
    private UUID transferUuid;
    private BigDecimal amount;
    private AccountCurrency currency;
    private UUID customerFromUuid;
    private UUID customerToUuid;
}
