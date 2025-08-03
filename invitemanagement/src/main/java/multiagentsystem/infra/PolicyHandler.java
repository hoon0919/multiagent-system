package multiagentsystem.infra;

import javax.transaction.Transactional;

import multiagentsystem.config.kafka.KafkaProcessor;
import multiagentsystem.domain.Membersinvited;
import multiagentsystem.domain.Signuped;
import multiagentsystem.service.InviteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    private InviteService inviteService;

    // 필요 없는 이벤트 수신기 (유지하되 내용 없음)
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    // Membersinvited 이벤트 수신 → 초대 생성
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Membersinvited'"
    )
    public void wheneverMembersinvited_CreateInviteByProject(
        @Payload Membersinvited event
    ) {
        System.out.println("[Invite] Membersinvited 수신: " + event);
        inviteService.createInviteByProject(event);
    }

    // Signuped 이벤트 수신 → 초대 승인 처리
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Signuped'"
    )
    public void wheneverSignuped_CheckInviteBySignup(
        @Payload Signuped event
    ) {
        System.out.println("[Invite] Signuped 수신: " + event);
        inviteService.checkInviteBySignup(event);
    }
}
