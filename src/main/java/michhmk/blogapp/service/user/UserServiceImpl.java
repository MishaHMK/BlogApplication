package michhmk.blogapp.service.user;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import michhmk.blogapp.dto.auth.UserRegisterRequestDto;
import michhmk.blogapp.dto.auth.UserRegisterResponseDto;
import michhmk.blogapp.dto.userdata.UpdateUserDataDto;
import michhmk.blogapp.dto.userdata.UserDto;
import michhmk.blogapp.exception.RegisterException;
import michhmk.blogapp.mapper.UserMapper;
import michhmk.blogapp.model.User;
import michhmk.blogapp.repository.user.UserRepository;
import michhmk.blogapp.security.SecurityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDto getById(Long id) {
        User userById = getUserById(id);
        return userMapper.toDto(userById);
    }

    @Override
    public Page<UserDto> getAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toDto);
    }

    @Transactional
    @Override
    public UserRegisterResponseDto save(UserRegisterRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RegisterException("User with email "
                    + requestDto.getEmail() + " already exists");
        }
        User user = userMapper.toUser(requestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public UserDto getCurrentUserData() {
        return userMapper.toDto(SecurityUtil.getLoggedInUser());
    }

    @Transactional
    @Override
    public void deleteUser() {
        User user = SecurityUtil.getLoggedInUser();
        userRepository.deleteById(user.getId());
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public UserDto updateUser(UpdateUserDataDto updateDto) {
        User currentUser = SecurityUtil.getLoggedInUser();
        userMapper.updateFromDto(updateDto, currentUser);
        return userMapper.toDto(userRepository.save(currentUser));
    }

    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id "
                        + id + " not found"));
    }
}
