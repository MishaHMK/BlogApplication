package michhmk.blogapp.dto.blog;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreatePostDto {
    @NotBlank(message = "Post title is required")
    private String title;
    @NotBlank(message = "Post content is required")
    private String content;
    private String imageRef;
}
