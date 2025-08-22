package michhmk.blogapp.service.post;

import michhmk.blogapp.dto.blog.CreatePostDto;
import michhmk.blogapp.dto.blog.PostDto;
import michhmk.blogapp.dto.blog.UpdatePostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    PostDto getById(Long id);

    Page<PostDto> getAll(Pageable pageable);

    PostDto savePost(CreatePostDto createPostDto);

    PostDto updatePost(Long id, UpdatePostDto updatePostDto);

    void deletePost(Long id);
}
