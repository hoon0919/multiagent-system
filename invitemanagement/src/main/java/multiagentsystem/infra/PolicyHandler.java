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
    InviteRepository inviteRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Membersinvited'"
    )
    public void wheneverMembersinvited_CreateInviteByProject(
        @Payload Membersinvited membersinvited
    ) {
        Membersinvited event = membersinvited;
        System.out.println(
            "\n\n##### listener CreateInviteByProject : " +
            membersinvited +
            "\n\n"
        );

        // Sample Logic //
        Invite.createInviteByProject(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Signuped'"
    )
    public void wheneverSignuped_CheckInviteBySignup(
        @Payload Signuped signuped
    ) {
        Signuped event = signuped;
        System.out.println(
            "\n\n##### listener CheckInviteBySignup : " + signuped + "\n\n"
        );

        // Sample Logic //
        Invite.checkInviteBySignup(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
