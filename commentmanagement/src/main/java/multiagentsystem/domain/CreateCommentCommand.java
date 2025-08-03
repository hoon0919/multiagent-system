package multiagentsystem.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CreateCommentCommand {

    private String content;
    private Long postId;
}
