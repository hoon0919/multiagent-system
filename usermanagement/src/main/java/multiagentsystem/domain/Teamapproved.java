package multiagentsystem.domain;

import java.util.*;
import lombok.*;
import multiagentsystem.domain.*;
import multiagentsystem.infra.AbstractEvent;

@Data
@ToString
public class Teamapproved extends AbstractEvent {

    private Long userId;
    private Long projectId;
}
