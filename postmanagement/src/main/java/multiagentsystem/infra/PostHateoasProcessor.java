package multiagentsystem.infra;

import multiagentsystem.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class PostHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Post>> {

    @Override
    public EntityModel<Post> process(EntityModel<Post> model) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/increaseviewcount"
                )
                .withRel("increaseviewcount")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/editpost")
                .withRel("editpost")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/createpost")
                .withRel("createpost")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/postdelete")
                .withRel("postdelete")
        );

        return model;
    }
}
