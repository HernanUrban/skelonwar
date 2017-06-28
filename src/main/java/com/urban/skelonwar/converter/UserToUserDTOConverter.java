package com.urban.skelonwar.converter;

import com.urban.skelonwar.domain.User;
import com.urban.skelonwar.dto.UserDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by hernan.urban on 5/24/2017.
 */
@Component
public class UserToUserDTOConverter implements Converter<User, UserDTO>{

    @Override
    public UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setAccountId(user.getAccountId());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setBlocked(user.isBlocked());
        return userDTO;
    }
}
