package multiagentsystem.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import multiagentsystem.domain.*;
import multiagentsystem.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ProjectCreated extends AbstractEvent {

    private Long projetId;
    private String projectDescription;
    private List<Attachment> attachments;
    private String projectName;

    public ProjectCreated(Project aggregate) {
        super(aggregate);
    }

    public ProjectCreated() {
        super();
    }
}
//>>> DDD / Domain Event
