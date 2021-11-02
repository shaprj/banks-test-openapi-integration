package shaprj.mockito.sample1.service;

import shaprj.mockito.sample1.model.*;

import java.util.List;
import java.util.UUID;

public interface BankTransferApi {

    UUID transfer(BankTransfer transfer);

    TransferStatus requestStatus(UUID transferUuid);

    List<BankRequest> findAllTransfers(UUID customerUuid);

    List<BankRequest> findAllTransfersByCurrency(UUID customerUuid, AccountCurrency currency);

}
