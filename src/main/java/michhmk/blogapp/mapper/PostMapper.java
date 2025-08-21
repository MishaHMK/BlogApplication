package michhmk.blogapp.mapper;

import michhmk.blogapp.config.MapperConfig;
import michhmk.blogapp.dto.blog.CreatePostDto;
import michhmk.blogapp.dto.blog.PostDto;
import michhmk.blogapp.dto.blog.PostDtoWithAuthorData;
import michhmk.blogapp.dto.blog.UpdatePostDto;
import michhmk.blogapp.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, uses = {UserMapper.class})
public interface PostMapper {
    @Mapping(target = "author", source = "authorId", qualifiedByName = "userFromId")
    Post toPost(PostDto postDto);

    Post toPost(CreatePostDto createPost);

    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "authorNickname", source = "author.nickname")
    PostDto toDto(Post post);

    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "authorNickname", source = "author.nickname")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "authorFatherName", source = "author.fatherName")
    PostDtoWithAuthorData toDtoWithAuthorData(Post post);

    @Mapping(target = "id", ignore = true)
    void updateFromDto(UpdatePostDto dto, @MappingTarget Post post);
}
