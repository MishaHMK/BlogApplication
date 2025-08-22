package michhmk.blogapp.service.post;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import michhmk.blogapp.dto.blog.CreatePostDto;
import michhmk.blogapp.dto.blog.PostDto;
import michhmk.blogapp.dto.blog.UpdatePostDto;
import michhmk.blogapp.mapper.PostMapper;
import michhmk.blogapp.model.Post;
import michhmk.blogapp.repository.user.PostRepository;
import michhmk.blogapp.security.SecurityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public PostDto getById(Long id) {
        return postMapper.toDto(getPostById(id));
    }

    @Override
    public Page<PostDto> getAll(Pageable pageable) {
        return postRepository.findAll(pageable).map(postMapper::toDto);
    }

    @Transactional
    @Override
    public PostDto savePost(CreatePostDto createPostDto) {
        Post toCreate = postMapper.toPost(createPostDto);
        toCreate.setAuthor(SecurityUtil.getLoggedInUser())
                .setPublicationDate(OffsetDateTime.now());
        return postMapper.toDto(postRepository.save(toCreate));
    }

    @Transactional
    @Override
    public PostDto updatePost(Long id, UpdatePostDto updatePostDto) {
        Post toUpdate = getPostById(id);
        postMapper.updateFromDto(updatePostDto, toUpdate);
        toUpdate.setUpdateDate(OffsetDateTime.now());
        return postMapper.toDto(toUpdate);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    private Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Post not found with id " + id)
        );
    }
}
