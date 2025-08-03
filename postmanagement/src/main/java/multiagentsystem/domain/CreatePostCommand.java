package multiagentsystem.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CreatePostCommand {

    private Long postId;
    private String title;
    private String content;
    private Integer viewCount;
    private Date createdAt;
    private Date updatedAt;
    private Long userId;
}
