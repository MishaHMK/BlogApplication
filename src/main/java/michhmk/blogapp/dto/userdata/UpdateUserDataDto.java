package michhmk.blogapp.dto.userdata;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

@Accessors(chain = true)
@Data
public class UpdateUserDataDto {
    @NotBlank(message = "Email is required")
    @Length(min = 8, max = 30,
            message = "Email size must be between "
                    + "8 and 30 symbols inclusively")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Nickname is required")
    @Length(max = 25, message = "Nickname shouldn't be "
            + "longer than 25 symbols")
    private String nickname;
    @NotBlank(message = "First name is required")
    private String firstName;
    private String lastName;
    private String fatherName;
    private String phone;
}
