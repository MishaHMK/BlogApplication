package michhmk.blogapp.security;

import java.util.Collection;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public class UserPrincipal {
    private final Long userId;
    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;

    public UserPrincipal(Long userId, String username, String password,
                         Collection<? extends GrantedAuthority> authorities,
                         boolean enabled) {
        this.userId = userId;
        this.username = username;
        this.authorities = authorities;
        this.enabled = enabled;
    }
}
