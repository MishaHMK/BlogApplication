package michhmk.blogapp.dto.blog;

public record UpdatePostDto(
        String title,
        String content,
        String imageRef
) {
}
