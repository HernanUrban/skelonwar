package com.urban.skelonwar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.urban.skelonwar.converter.UserRequestToUserConverter;
import com.urban.skelonwar.converter.UserToUserDTOConverter;
import com.urban.skelonwar.domain.User;
import com.urban.skelonwar.dto.UserDTO;
import com.urban.skelonwar.dto.UserRequest;
import com.urban.skelonwar.error.EntityNotFoundError;
import com.urban.skelonwar.error.EntityOperationError;
import com.urban.skelonwar.helper.CopyPropertiesHelper;
import com.urban.skelonwar.repository.UserRepository;
import com.urban.skelonwar.security.DefaultPasswordEncoder;

/**
 * Created by hernan.urban on 5/20/2017.
 */
@Service
public class DefaultUserService implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserToUserDTOConverter userToUserDTOConverter;

    @Autowired
    UserRequestToUserConverter accountConverter;

    @Autowired
    DefaultPasswordEncoder defaultPasswordEncoder;

    @Override
    public UserDTO get(long id) {
        User user = userRepository.findByAccountIdAndActive(id, true).orElseThrow(() -> new
                EntityNotFoundError
                ("User not found for ID " + id));
        return userToUserDTOConverter.convert(user);
    }

    @Override
    public UserDTO create(UserRequest userRequest) {
        if (!isUsernameAvailable(userRequest.getUsername())){
            throw new EntityOperationError("Username already exists");
        }
        User newUser = accountConverter.convert(userRequest);
        newUser.setPassword(defaultPasswordEncoder.encode(userRequest.getPassword()));
        User user = userRepository.save(newUser);
        return userToUserDTOConverter.convert(user);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        if (null == userDTO.getAccountId()) {
            throw new EntityOperationError("Unable to update User if ID is empty");
        }
        User user = userRepository.findByAccountIdAndActive(userDTO.getAccountId(),true)
                .orElseThrow(() -> new
                EntityNotFoundError("User not found"));
        if (!userDTO.getUsername().equalsIgnoreCase(user.getUsername()) &&
            !isUsernameAvailable(userDTO.getUsername())){
                throw new EntityOperationError("Username already exists");
        }
        CopyPropertiesHelper.copyNonNullProperties(userDTO, user);

        return userToUserDTOConverter.convert(userRepository.save(user));
    }

    @Override
    public void delete(long id) {
        User user = userRepository.findByAccountIdAndActive(id, true).orElse(null);
        if (null != user){
            user.setActive(false);
            userRepository.save(user);
        }
    }

    @Override
    public boolean isBlocked(long id) {
        User user = userRepository.findByAccountId(id).orElse(null);
        return (user != null)?user.isBlocked():false;
    }

    @Override
    public boolean isActive(long id) {
        User user = userRepository.findByAccountId(id).orElse(null);
        return (user != null)?user.isActive():false;
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        return null == user;
    }

    @Override
    public Page<User> getAll(int pageNumber, int pageSize) {
        return userRepository.findAll(new PageRequest(pageNumber, pageSize));
    }

}
