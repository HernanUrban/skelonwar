package com.urban.skelonwar.repository;

import com.urban.skelonwar.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by hernan.urban on 5/24/2017.
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    Optional<User> findByAccountId(Long accountId);

    Optional<User> findByAccountIdAndActive(Long accountId, boolean active);

    Optional<User> findByUsername(String username);

    Page<User> findAll(Pageable page);

}
