package michhmk.blogapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import michhmk.blogapp.dto.blog.CreatePostDto;
import michhmk.blogapp.dto.blog.PostDto;
import michhmk.blogapp.dto.blog.UpdatePostDto;
import michhmk.blogapp.service.post.PostServiceImpl;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Post controller", description = "Post management endpoint")
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostServiceImpl postService;

    @GetMapping
    @Operation(summary = "Get all posts", description = "Get all posts in paged representation")
    public Page<PostDto> getAllPosts(
            @ParameterObject Pageable pageable) {
        return postService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get post by id", description = "Get selected post by id")
    public PostDto getPostById(
            @PathVariable Long id) {
        return postService.getById(id);
    }

    @PostMapping()
    @Operation(summary = "Create post", description = "Create new post as logged in user")
    public PostDto createPost(
            @Valid @RequestBody CreatePostDto createPostDto) {
        return postService.savePost(createPostDto);
    }

    @PreAuthorize("hasRole('ADMIN') or @postSecurity.isOwner(id)")
    @PutMapping("/{id}")
    @Operation(summary = "Update post", description = "Update post data as logged in"
            + " owner user or ADMIN")
    public PostDto updatePost(@PathVariable Long id,
            @Valid @RequestBody UpdatePostDto updatePostDto) {
        return postService.updatePost(id, updatePostDto);
    }

    @PreAuthorize("hasRole('ADMIN') or @postSecurity.isOwner(#id)")
    @DeleteMapping({"/{id}"})
    @Operation(summary = "Delete post", description = "Remove post by its id")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
