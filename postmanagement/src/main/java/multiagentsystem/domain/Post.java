package multiagentsystem.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import multiagentsystem.PostmanagementApplication;

@Entity
@Table(name = "Post_table")
@Data
//<<< DDD / Aggregate Root
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    private String title;

    private String content;

    private Integer viewCount;

    private Date createdAt;

    private Date updatedAt;

    private Long userId;

    @ElementCollection
    @CollectionTable(name = "post_files", joinColumns = @JoinColumn(name = "post_id"))
    private List<File> fileUrl;

    public static PostRepository repository() {
        PostRepository postRepository = PostmanagementApplication.applicationContext.getBean(
            PostRepository.class
        );
        return postRepository;
    }

    //<<< Clean Arch / Port Method
    public void increaseViewCount(
        IncreaseViewCountCommand increaseViewCountCommand
    ) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void editPost(EditPostCommand editPostCommand) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void createPost(CreatePostCommand createPostCommand) {
        //implement business logic here:

        PostCreated postCreated = new PostCreated(this);
        postCreated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void postDelete(PostDeleteCommand postDeleteCommand) {
        //implement business logic here:

        PostDeleted postDeleted = new PostDeleted(this);
        postDeleted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void createPostByProject(ProjectCreated projectCreated) {
        //implement business logic here:

        /** Example 1:  new item 
        Post post = new Post();
        repository().save(post);

        PostCreated postCreated = new PostCreated(post);
        postCreated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(projectCreated.get???()).ifPresent(post->{
            
            post // do something
            repository().save(post);

            PostCreated postCreated = new PostCreated(post);
            postCreated.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void deletePostByProject(ProjectDeleted projectDeleted) {
        //implement business logic here:

        /** Example 1:  new item 
        Post post = new Post();
        repository().save(post);

        PostDeleted postDeleted = new PostDeleted(post);
        postDeleted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(projectDeleted.get???()).ifPresent(post->{
            
            post // do something
            repository().save(post);

            PostDeleted postDeleted = new PostDeleted(post);
            postDeleted.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
