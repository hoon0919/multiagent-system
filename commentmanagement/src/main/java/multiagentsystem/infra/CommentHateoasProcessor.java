package multiagentsystem.infra;

import multiagentsystem.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class CommentHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Comment>> {

    @Override
    public EntityModel<Comment> process(EntityModel<Comment> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/createcomment")
                .withRel("createcomment")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/deletecomment")
                .withRel("deletecomment")
        );

        return model;
    }
}
