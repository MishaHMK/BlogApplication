package michhmk.blogapp.service.user;

import michhmk.blogapp.dto.auth.UserRegisterRequestDto;
import michhmk.blogapp.dto.auth.UserRegisterResponseDto;
import michhmk.blogapp.dto.userdata.UpdateUserDataDto;
import michhmk.blogapp.dto.userdata.UserDto;
import michhmk.blogapp.exception.RegisterException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserRegisterResponseDto save(UserRegisterRequestDto userRegisterRequestDto)
            throws RegisterException;

    UserDto getCurrentUserData();

    UserDto updateUser(UpdateUserDataDto updateDto);

    void deleteUser();

    void deleteUserById(Long id);

    UserDto getById(Long id);

    Page<UserDto> getAll(Pageable pageable);
}
