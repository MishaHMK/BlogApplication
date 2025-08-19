package michhmk.blogapp.dto.userdata;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class UserDto {
    private Long id;
    private String email;
    private String nickname;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String phone;
    private String role;
}
