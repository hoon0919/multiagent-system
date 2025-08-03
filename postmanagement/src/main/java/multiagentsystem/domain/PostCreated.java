package multiagentsystem.domain;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;

import lombok.*;
import multiagentsystem.domain.*;
import multiagentsystem.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class PostCreated extends AbstractEvent {

    private Long postId;

    @ElementCollection
    @CollectionTable(name = "post_files", joinColumns = @JoinColumn(name = "post_id"))
    private List<File> fileUrl;

    public PostCreated(Post aggregate) {
        super(aggregate);
    }

    public PostCreated() {
        super();
    }
}
//>>> DDD / Domain Event
