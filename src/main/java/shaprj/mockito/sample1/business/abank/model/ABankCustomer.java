package shaprj.mockito.sample1.business.abank.model;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ABankCustomer {
    private String name;
    private String lastName;
    private Integer age;
    private String email;
    private String phone;
}
