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
    private Long userId;  // ✅ 추가
    private String projectDescription;
    private String uploadPath;
    private String projectName;

    public ProjectCreated(Project aggregate) {
        super(aggregate);
        this.projetId = aggregate.getProjetId();
        this.userId = aggregate.getUserId();  // ✅ 추가
        this.projectDescription = aggregate.getProjectDescription();
        this.uploadPath = aggregate.getUploadPath();
        this.projectName = aggregate.getProjectName();
    }

    public ProjectCreated() {
        super();
    }
}
//>>> DDD / Domain Event
