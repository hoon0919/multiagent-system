package multiagentsystem.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import multiagentsystem.domain.*;
import multiagentsystem.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class PostCreated extends AbstractEvent {

    private Long postId;
    private List<FileUrl> fileUrl;

    public PostCreated(Post aggregate) {
        super(aggregate);
    }

    public PostCreated() {
        super();
    }
}
//>>> DDD / Domain Event
