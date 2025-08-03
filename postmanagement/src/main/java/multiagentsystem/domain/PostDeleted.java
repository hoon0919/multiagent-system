package multiagentsystem.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import multiagentsystem.domain.*;
import multiagentsystem.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class PostDeleted extends AbstractEvent {

    private Long postId;

    public PostDeleted(Post aggregate) {
        super(aggregate);
    }

    public PostDeleted() {
        super();
    }
}
//>>> DDD / Domain Event
