package multiagentsystem.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import multiagentsystem.ProjectmanagementApplication;
import multiagentsystem.domain.ProjectCreated;

@Entity
@Table(name = "Project_table")
@Data
//<<< DDD / Aggregate Root
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projetId;

    private Long userId;

    private String projectName;

    private String projectDescription;

    @ElementCollection
    private List<Attachment> attachments;

    private String membersEmail;

    @PostPersist
    public void onPostPersist() {
        ProjectCreated projectCreated = new ProjectCreated(this);
        projectCreated.publishAfterCommit();
    }

    public static ProjectRepository repository() {
        ProjectRepository projectRepository = ProjectmanagementApplication.applicationContext.getBean(
            ProjectRepository.class
        );
        return projectRepository;
    }

    //<<< Clean Arch / Port Method
    public void memberInvite(MemberInviteCommand memberInviteCommand) {
        //implement business logic here:

        Membersinvited membersinvited = new Membersinvited(this);
        membersinvited.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void createProject(CreateProjectCommand createProjectCommand) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void projectDelete(ProjectDeleteCommand projectDeleteCommand) {
        //implement business logic here:

        ProjectDeleted projectDeleted = new ProjectDeleted(this);
        projectDeleted.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
