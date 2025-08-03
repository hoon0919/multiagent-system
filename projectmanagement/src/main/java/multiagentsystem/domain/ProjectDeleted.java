package multiagentsystem.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import multiagentsystem.domain.*;
import multiagentsystem.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ProjectDeleted extends AbstractEvent {

    private Long projetId;

    public ProjectDeleted(Project aggregate) {
        super(aggregate);
    }

    public ProjectDeleted() {
        super();
    }
}
//>>> DDD / Domain Event
