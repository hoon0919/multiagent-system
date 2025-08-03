package multiagentsystem.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import multiagentsystem.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/projects")
@Transactional
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @RequestMapping(
        value = "/projects/memberinvite",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Project memberInvite(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody MemberInviteCommand memberInviteCommand
    ) throws Exception {
        System.out.println("##### /project/memberInvite  called #####");
        Project project = new Project();
        project.memberInvite(memberInviteCommand);
        projectRepository.save(project);
        return project;
    }

    @RequestMapping(
        value = "/projects/createproject",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Project createProject(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody CreateProjectCommand createProjectCommand
    ) throws Exception {
        System.out.println("##### /project/createProject  called #####");
        Project project = new Project();
        project.createProject(createProjectCommand);
        projectRepository.save(project);
        return project;
    }

    @RequestMapping(
        value = "/projects/{id}/projectdelete",
        method = RequestMethod.DELETE,
        produces = "application/json;charset=UTF-8"
    )
    public Project projectDelete(
        @PathVariable(value = "id") Long id,
        @RequestBody ProjectDeleteCommand projectDeleteCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /project/projectDelete  called #####");
        Optional<Project> optionalProject = projectRepository.findById(id);

        optionalProject.orElseThrow(() -> new Exception("No Entity Found"));
        Project project = optionalProject.get();
        project.projectDelete(projectDeleteCommand);

        projectRepository.delete(project);
        return project;
    }
}
//>>> Clean Arch / Inbound Adaptor
