package multiagentsystem.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class EditPostCommand {

    private String title;
    private String content;
}
