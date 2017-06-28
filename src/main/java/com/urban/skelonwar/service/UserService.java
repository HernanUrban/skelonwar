package com.urban.skelonwar.service;

import com.urban.skelonwar.domain.User;
import com.urban.skelonwar.dto.UserDTO;
import com.urban.skelonwar.dto.UserRequest;
import org.springframework.data.domain.Page;

/**
 * Created by hernan.urban on 5/20/2017.
 */
public interface UserService {

    /**
     * Retrieves an user for a given account ID.
     *
     * @param id the account ID.
     * @return the account for a given ID.
     */
    public UserDTO get(long id);

    /**
     * Creates a new account.
     *
     * @param userRequest the creation request object.
     * @return the created or updated account.
     */
    public UserDTO create(UserRequest userRequest);

    /**
     * Updates an account for a given ID.
     *
     * @param userDTO the creation request object.
     * @return the updated account.
     */
    public UserDTO update(UserDTO userDTO);

    /**
     * Removes an account for a given ID.
     *
     * @param id the account ID.
     */
    public void delete(long id);

    /**
     * Provides the blocked status for a given account ID
     *
     * @param accountId the account ID.
     * @return TRUE if the account is blocked.
     */
    public boolean isBlocked(long accountId);

    /**
     * Provides the active status for a given account ID.
     *
     * @param id the account ID.
     * @return TRUE if teh account is active.
     */
    public boolean isActive(long id);

    /**
     * Verifies if the given username is available.
     *
     * @param username the username to verify.
     * @return TRUE if the username does not exists.
     */
    public boolean isUsernameAvailable(String username);

    /**
     * Retrieves a given page of all users
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public Page<User> getAll(int pageNumber, int pageSize);
}
