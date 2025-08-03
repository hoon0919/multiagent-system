package multiagentsystem.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import multiagentsystem.CommentmanagementApplication;

@Entity
@Table(name = "Comment_table")
@Data
//<<< DDD / Aggregate Root
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    private String content;

    private Date createdAt;

    private Date updatedAt;

    private Long postId;

    private Long userId;

    public static CommentRepository repository() {
        CommentRepository commentRepository = CommentmanagementApplication.applicationContext.getBean(
            CommentRepository.class
        );
        return commentRepository;
    }

    //<<< Clean Arch / Port Method
    public void createComment(CreateCommentCommand createCommentCommand) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void deleteComment() {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void deleteCommentByPost(PostDeleted postDeleted) {
        //implement business logic here:

        /** Example 1:  new item 
        Comment comment = new Comment();
        repository().save(comment);

        */

        /** Example 2:  finding and process
        
        // if postDeleted.blobStorageId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<, Object> postMap = mapper.convertValue(postDeleted.getBlobStorageId(), Map.class);

        repository().findById(postDeleted.get???()).ifPresent(comment->{
            
            comment // do something
            repository().save(comment);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
