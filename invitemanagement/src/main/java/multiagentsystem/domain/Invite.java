package multiagentsystem.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import multiagentsystem.InvitemanagementApplication;
import multiagentsystem.domain.Teamapproved;

@Entity
@Table(name = "Invite_table")
@Data
//<<< DDD / Aggregate Root
public class Invite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inviteId;

    private Long projectId;

    private String email;

    private String status;

    private Long userId;

    public static InviteRepository repository() {
        InviteRepository inviteRepository = InvitemanagementApplication.applicationContext.getBean(
            InviteRepository.class
        );
        return inviteRepository;
    }

    //<<< Clean Arch / Port Method
    public static void createInviteByProject(Membersinvited membersinvited) {
        //implement business logic here:

        /** Example 1:  new item 
        Invite invite = new Invite();
        repository().save(invite);

        */

        /** Example 2:  finding and process
        

        repository().findById(membersinvited.get???()).ifPresent(invite->{
            
            invite // do something
            repository().save(invite);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void checkInviteBySignup(Signuped signuped) {
        //implement business logic here:

        /** Example 1:  new item 
        Invite invite = new Invite();
        repository().save(invite);

        Teamapproved teamapproved = new Teamapproved(invite);
        teamapproved.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(signuped.get???()).ifPresent(invite->{
            
            invite // do something
            repository().save(invite);

            Teamapproved teamapproved = new Teamapproved(invite);
            teamapproved.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
