package multiagentsystem.domain;

import java.util.*;
import lombok.*;
import multiagentsystem.domain.*;
import multiagentsystem.infra.AbstractEvent;

@Data
@ToString
public class PostCreated extends AbstractEvent {

    private Long postId;
    private Object fileUrl;
}
