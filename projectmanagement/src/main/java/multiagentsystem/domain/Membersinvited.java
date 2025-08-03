package multiagentsystem.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import multiagentsystem.domain.*;
import multiagentsystem.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class Membersinvited extends AbstractEvent {

    private String membersEmail;
    private Long projectId;

    public Membersinvited(Project aggregate) {
        super(aggregate);
    }

    public Membersinvited() {
        super();
    }
}
//>>> DDD / Domain Event
