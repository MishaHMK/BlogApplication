package michhmk.blogapp.helper;

import lombok.RequiredArgsConstructor;
import michhmk.blogapp.model.Post;
import michhmk.blogapp.model.User;
import michhmk.blogapp.repository.user.PostRepository;
import michhmk.blogapp.security.SecurityUtil;
import org.springframework.stereotype.Component;

@Component("postSecurity")
@RequiredArgsConstructor
public class PostSecurity {
    private final PostRepository postRepository;

    public boolean isOwner(Long id) {
        User loggedInUser = SecurityUtil.getLoggedInUser();
        Post post = postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Post not found")
        );
        return loggedInUser.getId().equals(post.getAuthor().getId());
    }
}
