package michhmk.blogapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import michhmk.blogapp.dto.userdata.UpdateUserDataDto;
import michhmk.blogapp.dto.userdata.UserDto;
import michhmk.blogapp.service.user.UserService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User controller", description = "User management endpoint")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "Get user by id",
            description = "Get detailed user data by their id")
    public UserDto getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Get all users",
            description = "Get all users data with pagination/sorting")
    public Page<UserDto> getAll(@ParameterObject Pageable pageable) {
        return userService.getAll(pageable);
    }

    @GetMapping("me")
    @Operation(summary = "Get user info",
            description = "Receive currently logged in user info")
    public UserDto receiveCurrentUserInfo() {
        return userService.getCurrentUserData();
    }

    @PutMapping("update")
    @Operation(summary = "Update user info",
            description = "Update currently logged in user profile info")
    public UserDto updateCurrentUserInfo(@RequestBody UpdateUserDataDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("delete")
    @Operation(summary = "Remove current user account",
            description = "Remove currently logged in user profile")
    public void removeCurrentUser() {
        userService.deleteUser();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete/{id}")
    @Operation(summary = "Remove user by id [ADMIN ONLY]",
            description = "Remove selected user by their id [ADMIN ONLY]")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
