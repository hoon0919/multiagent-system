package multiagentsystem.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import multiagentsystem.domain.*;
import multiagentsystem.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class Signuped extends AbstractEvent {

    private Long userId;
    private String email;

    public Signuped(User aggregate) {
        super(aggregate);
    }

    public Signuped() {
        super();
    }
}
//>>> DDD / Domain Event
