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
    PostRepository postRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ProjectCreated'"
    )
    public void wheneverProjectCreated_CreatePostByProject(
        @Payload ProjectCreated projectCreated
    ) {
        ProjectCreated event = projectCreated;
        System.out.println(
            "\n\n##### listener CreatePostByProject : " +
            projectCreated +
            "\n\n"
        );

        // Sample Logic //
        Post.createPostByProject(event);

        CreatePostCommand command = new CreatePostCommand();
        //command.setPostId("???");
        //command.setTitle("???");
        //command.setContent("???");
        //command.setViewCount("???");
        //command.setCreatedAt("???");
        //command.setUpdatedAt("???");
        //command.setUserId("???");

        Post post = new Post();
        post.createPost(command);
        postRepository.save(post);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ProjectDeleted'"
    )
    public void wheneverProjectDeleted_DeletePostByProject(
        @Payload ProjectDeleted projectDeleted
    ) {
        ProjectDeleted event = projectDeleted;
        System.out.println(
            "\n\n##### listener DeletePostByProject : " +
            projectDeleted +
            "\n\n"
        );

        // Sample Logic //
        Post.deletePostByProject(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
