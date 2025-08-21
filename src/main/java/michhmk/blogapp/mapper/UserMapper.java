package michhmk.blogapp.mapper;

import michhmk.blogapp.config.MapperConfig;
import michhmk.blogapp.dto.auth.UserRegisterRequestDto;
import michhmk.blogapp.dto.auth.UserRegisterResponseDto;
import michhmk.blogapp.dto.userdata.UpdateUserDataDto;
import michhmk.blogapp.dto.userdata.UserDto;
import michhmk.blogapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    User toUser(UserRegisterRequestDto dto);

    UserDto toDto(User user);

    UserRegisterResponseDto toResponse(User user);

    void updateFromDto(UpdateUserDataDto dto, @MappingTarget User user);

    @Named("userFromId")
    default User userFromId(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}
