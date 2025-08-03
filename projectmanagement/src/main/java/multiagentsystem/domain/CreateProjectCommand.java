package multiagentsystem.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CreateProjectCommand {

    private Long projetId;
    private String projectName;
    private String projectDescription;
    private List<Attachment> attachments;
}
