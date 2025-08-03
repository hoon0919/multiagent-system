package multiagentsystem.domain;

import java.util.*;
import lombok.*;
import multiagentsystem.domain.*;
import multiagentsystem.infra.AbstractEvent;

@Data
@ToString
public class ProjectCreated extends AbstractEvent {

    private Long projetId;
    private String projectDescription;
    private Object attachments;
    private String projectName;
}
