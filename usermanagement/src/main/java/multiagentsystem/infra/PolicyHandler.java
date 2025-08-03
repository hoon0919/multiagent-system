package multiagentsystem.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import multiagentsystem.config.kafka.KafkaProcessor;
import multiagentsystem.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    UserRepository userRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Teamapproved'"
    )
    public void wheneverTeamapproved_AddProjectId(
        @Payload Teamapproved teamapproved
    ) {
        Teamapproved event = teamapproved;
        System.out.println(
            "\n\n##### listener AddProjectId : " + teamapproved + "\n\n"
        );

        // Sample Logic //
        User.addProjectId(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ProjectDeleted'"
    )
    public void wheneverProjectDeleted_CancelProject(
        @Payload ProjectDeleted projectDeleted
    ) {
        ProjectDeleted event = projectDeleted;
        System.out.println(
            "\n\n##### listener CancelProject : " + projectDeleted + "\n\n"
        );

        // Sample Logic //
        User.cancelProject(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
