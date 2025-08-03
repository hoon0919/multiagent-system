package multiagentsystem.domain;

import java.util.*;
import lombok.*;
import multiagentsystem.domain.*;
import multiagentsystem.infra.AbstractEvent;

@Data
@ToString
public class Membersinvited extends AbstractEvent {

    private String membersEmail;
    private Long projectId;
}
