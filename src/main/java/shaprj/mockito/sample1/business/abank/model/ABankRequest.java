package shaprj.mockito.sample1.business.abank.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ABankRequest {
    private UUID accountFrom;
    private UUID accountTo;
    private ABankCurrency currency;
    private BigDecimal amount;
}
