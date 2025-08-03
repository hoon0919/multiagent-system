package multiagentsystem.domain;

import multiagentsystem.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "invites", path = "invites")
public interface InviteRepository
    extends PagingAndSortingRepository<Invite, Long> {}
