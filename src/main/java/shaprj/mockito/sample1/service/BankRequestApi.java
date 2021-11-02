package shaprj.mockito.sample1.service;

import shaprj.mockito.sample1.model.AccountCurrency;
import shaprj.mockito.sample1.model.BankRequest;
import shaprj.mockito.sample1.model.RequestStatus;

import java.util.List;
import java.util.UUID;

public interface BankRequestApi {

    UUID request(BankRequest request);

    RequestStatus requestStatus(UUID requestUuid);

    List<BankRequest> findAllRequests(UUID customerUuid);

    List<BankRequest> findAllRequestsByCurrency(UUID customerUuid, AccountCurrency currency);

}
