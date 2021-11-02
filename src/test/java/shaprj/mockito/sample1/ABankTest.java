package shaprj.mockito.sample1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import shaprj.mockito.sample1.business.abank.ABankApi;
import shaprj.mockito.sample1.business.abank.model.ABankCustomer;

import java.util.Objects;
import java.util.UUID;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ABankTest {

    @Test
    @DisplayName("Test A-Bank")
    public void testABankRequestApi(){
        testABankRequestApiScenario(true);
    }

    private void testABankRequestApiScenario(final Boolean isMock){
        var aBankApi = buildABankApi(isMock);
        var sessionUuid = createSession(aBankApi, isMock);
        Objects.requireNonNull(sessionUuid);
        var customerUuid = createCustomer(aBankApi, sessionUuid, isMock);
        Objects.requireNonNull(customerUuid);
    }

    private ABankApi buildABankApi(final Boolean isMock){
        if(isMock){
            return mock(ABankApi.class);
        } else {
            return spy(ABankApi.class);
        }
    }

    private UUID createSession(final ABankApi aBankApi, final Boolean isMock){
        if(isMock){
            var predefinedSessionUUID = UUID.fromString("f71240e8-5593-4456-99f9-76f364142eab");
            when(aBankApi.createSession("testtoken")).thenReturn(predefinedSessionUUID);
        }
        return aBankApi.createSession("testtoken");
    }

    private UUID createCustomer(final ABankApi aBankApi, final UUID sessionUuid, final Boolean isMock){
        var customer = ABankCustomer.builder()
                .name("Oleg")
                .lastName("Shalaevsky")
                .age(36)
                .email("aaa@mail.ru")
                .phone("89211234567")
                .build();
        if(isMock){
            var predefinedCustomerUUID = UUID.fromString("f71240e8-5593-4456-99f9-76f364142eab");
            when(aBankApi.createCustomer(sessionUuid, customer)).thenReturn(predefinedCustomerUUID);
        }
        return aBankApi.createCustomer(sessionUuid, customer);
    }

}
