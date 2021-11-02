package shaprj.mockito.sample1.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private UUID uuid;
    private String name;
    private String lastName;
    private Integer age;
    private String email;
    private String phone;
}
