package shaprj.mockito.sample1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import shaprj.mockito.generated.openapi.api.BankOperationsApi;
import shaprj.mockito.generated.openapi.invoker.ApiException;
import shaprj.mockito.generated.openapi.model.*;
import shaprj.mockito.sample1.business.abank.model.ABankRequestStatus;
import shaprj.mockito.sample1.model.AccountCurrency;
import shaprj.mockito.sample1.model.BankRequest;
import shaprj.mockito.sample1.model.Customer;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@SpringBootTest
public class CitiBankTest {

    BankOperationsApi api = spy(BankOperationsApi.class);

    @Test
    @DisplayName("Test sample scenario 1")
    public void testSampleScenario1(){
        try {
            /* Create sessions for both customers */
            var sessionUuid1 = api.createSession("testtoken");
            Objects.requireNonNull(sessionUuid1);
            var sessionUuid2 = api.createSession("testtoken");
            Objects.requireNonNull(sessionUuid2);

            /* Create both customers */
            var customer1 = createCustomerApi(sessionUuid1, Customer.builder()
                    .name("Oleg")
                    .lastName("Shalaevsky")
                    .age(36)
                    .email("aaa@mail.ru")
                    .phone("89216572929")
                    .build());
            var customer2 = createCustomerApi(sessionUuid2, Customer.builder()
                    .name("Ivan")
                    .lastName("Petrov")
                    .age(40)
                    .email("bbb@mail.ru")
                    .phone("89112345678")
                    .build());
            var customerUuid1 = api.createCustomer(customer1);
            Objects.requireNonNull(customerUuid1);
            var customerUuid2 = api.createCustomer(customer2);
            Objects.requireNonNull(customerUuid2);

            /* Check if both customers have accounts */

            var customer1AccountList = api.accounts(sessionUuid1, customerUuid1);
            assertThat(!customer1AccountList.isEmpty());
            var customer2AccountList = api.accounts(sessionUuid2, customerUuid2);
            assertThat(!customer2AccountList.isEmpty());

            /* Check if both customers have EUR accounts*/
            assertThat(customer1AccountList.stream().filter(a -> a.getCurrency().equals(CitiBankAccount.CurrencyEnum.EUR)).count() > 0);
            assertThat(customer2AccountList.stream().filter(a -> a.getCurrency().equals(CitiBankAccount.CurrencyEnum.EUR)).count() > 0);

            /* Execute transaction request */
            var requestUUid = api.createRequest(createRequestApi(sessionUuid2, BankRequest.builder()
                    .amount(new BigDecimal(100))
                    .currency(AccountCurrency.EUR)
                    .customerFromUuid(customerUuid1)
                    .customerToUuid(customerUuid2)
                    .build()));
            Objects.requireNonNull(requestUUid);
            var status = api.requestStatus(sessionUuid2, requestUUid);
            assertThat(status.equals(ABankRequestStatus.CREATED.name()));

        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private CitiBankRequestApi createRequestApi(final UUID sessionUuid, final BankRequest request){
        var citiBankRequest = new CitiBankRequestApi();
        citiBankRequest.setSessionUuid(sessionUuid);
        CitiBankRequestData requestData = new CitiBankRequestData();
        requestData.setAmount(request.getAmount());
        requestData.setCurrency(citiBankCurrencyTransform(request.getCurrency()));
        requestData.setCustomerFromUuid(request.getCustomerFromUuid());
        requestData.setCustomerToUuid(request.getCustomerToUuid());
        citiBankRequest.setRequestData(requestData);
        return citiBankRequest;
    }

    private CitiBankRequestData.CurrencyEnum citiBankCurrencyTransform(final AccountCurrency currency){
        return CitiBankRequestData.CurrencyEnum.valueOf(currency.name());
    }

    private CitiBankCustomerApi createCustomerApi(final UUID sessionUuid, final Customer customer){
        var customerData1 = new CitiBankCustomerData();
        customerData1.setName(customer.getName());
        customerData1.setLastName(customer.getLastName());
        customerData1.setAge(customer.getAge());
        customerData1.setEmail(customer.getEmail());
        customerData1.setPhone(customer.getPhone());
        var customer1 = new CitiBankCustomerApi();
        customer1.sessionUuid(sessionUuid);
        customer1.setCustomerData(customerData1);
        return customer1;
    }
}
