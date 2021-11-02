package shaprj.mockito.sample1.business.abank;

import shaprj.mockito.sample1.business.abank.model.*;

import java.util.List;
import java.util.UUID;

public interface ABankApi {

    UUID createSession(final String token);

    UUID createCustomer(final UUID sessionUuid, final ABankCustomer customer);

    List<ABankAccount> accounts(final UUID sessionUuid, final UUID aBankCustomerUuid);

    List<ABankCard> cards(final UUID sessionUuid, final UUID aBankCustomerUuid);

    UUID createRequest(final UUID sessionUuid, final ABankAccount account, final ABankRequest request);

    UUID createRequest(final UUID sessionUuid, final ABankCard card, final ABankRequest request);

    ABankRequestStatus requestStatus(final UUID requestUuid);

}
