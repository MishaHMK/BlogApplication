package michhmk.blogapp.security;

import michhmk.blogapp.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("securityUtil")
public class SecurityUtil {
    public static User getLoggedInUser() {
        Object principal = SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        return (User) principal;
    }
}
