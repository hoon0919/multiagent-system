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
    AttachmentRepository attachmentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PostCreated'"
    )
    public void wheneverPostCreated_CreateAttachmentByPost(
        @Payload PostCreated postCreated
    ) {
        PostCreated event = postCreated;
        System.out.println(
            "\n\n##### listener CreateAttachmentByPost : " +
            postCreated +
            "\n\n"
        );

        // Sample Logic //
        Attachment.createAttachmentByPost(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PostDeleted'"
    )
    public void wheneverPostDeleted_DeleteAttachmentByPost(
        @Payload PostDeleted postDeleted
    ) {
        PostDeleted event = postDeleted;
        System.out.println(
            "\n\n##### listener DeleteAttachmentByPost : " +
            postDeleted +
            "\n\n"
        );

        // Sample Logic //
        Attachment.deleteAttachmentByPost(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
