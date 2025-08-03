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
// @RequestMapping(value="/posts")
@Transactional
public class PostController {

    @Autowired
    PostRepository postRepository;

    @RequestMapping(
        value = "/posts/{id}/increaseviewcount",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Post increaseViewCount(
        @PathVariable(value = "id") Long id,
        @RequestBody IncreaseViewCountCommand increaseViewCountCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /post/increaseViewCount  called #####");
        Optional<Post> optionalPost = postRepository.findById(id);

        optionalPost.orElseThrow(() -> new Exception("No Entity Found"));
        Post post = optionalPost.get();
        post.increaseViewCount(increaseViewCountCommand);

        postRepository.save(post);
        return post;
    }

    @RequestMapping(
        value = "/posts/{id}/editpost",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Post editPost(
        @PathVariable(value = "id") Long id,
        @RequestBody EditPostCommand editPostCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /post/editPost  called #####");
        Optional<Post> optionalPost = postRepository.findById(id);

        optionalPost.orElseThrow(() -> new Exception("No Entity Found"));
        Post post = optionalPost.get();
        post.editPost(editPostCommand);

        postRepository.save(post);
        return post;
    }

    @RequestMapping(
        value = "/posts/createpost",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Post createPost(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody CreatePostCommand createPostCommand
    ) throws Exception {
        System.out.println("##### /post/createPost  called #####");
        Post post = new Post();
        post.createPost(createPostCommand);
        postRepository.save(post);
        return post;
    }

    @RequestMapping(
        value = "/posts/{id}/postdelete",
        method = RequestMethod.DELETE,
        produces = "application/json;charset=UTF-8"
    )
    public Post postDelete(
        @PathVariable(value = "id") Long id,
        @RequestBody PostDeleteCommand postDeleteCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /post/postDelete  called #####");
        Optional<Post> optionalPost = postRepository.findById(id);

        optionalPost.orElseThrow(() -> new Exception("No Entity Found"));
        Post post = optionalPost.get();
        post.postDelete(postDeleteCommand);

        postRepository.delete(post);
        return post;
    }
}
//>>> Clean Arch / Inbound Adaptor
