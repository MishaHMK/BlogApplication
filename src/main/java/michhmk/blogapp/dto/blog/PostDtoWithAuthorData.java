package michhmk.blogapp.dto.blog;

import java.time.OffsetDateTime;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PostDtoWithAuthorData {
    private Long id;
    private String title;
    private String content;
    private String imageRef;
    private Long authorId;
    private String authorNickname;
    private String authorFirstName;
    private String authorLastName;
    private String authorFatherName;
    private OffsetDateTime publicationDate;
    private OffsetDateTime updateDate;
}
