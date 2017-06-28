package com.urban.skelonwar.converter;

import com.urban.skelonwar.domain.User;
import com.urban.skelonwar.dto.UserRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by hernan.urban on 5/24/2017.
 */
@Component
public class UserRequestToUserConverter implements Converter<UserRequest, User> {

    @Override
    public User convert(UserRequest userRequest) {
        return new User.AccountBuilder()
                .withBlocked(false)
                .withActive(true)
                .withFirstname(userRequest.getFirstname())
                .withLastname(userRequest.getLastname())
                .withUsername(userRequest.getUsername())
                .withPassword(userRequest.getPassword())
                .build();
    }
}
