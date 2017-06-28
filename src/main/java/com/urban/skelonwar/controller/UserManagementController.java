package com.urban.skelonwar.controller;

import com.urban.skelonwar.constants.GlobalConstants;
import com.urban.skelonwar.domain.User;
import com.urban.skelonwar.dto.UserDTO;
import com.urban.skelonwar.dto.UserRequest;
import com.urban.skelonwar.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by hernan.urban on 5/12/2017.
 */
@RestController
@RequestMapping(value = GlobalConstants.ACCOUNT_URI)
@Api(basePath = GlobalConstants.ACCOUNT_URI, description = "User resources")
public class UserManagementController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/{accountId}")
    @ApiOperation(value = "Get an account", notes = "Retrieves an account for a given account ID",
            response = UserDTO.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getAccount(@PathVariable(value = "accountId", name = "accountId", required = true) @NotBlank Long accountId) {
        UserDTO account = userService.get(accountId);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Creates an account", notes = "Creates a new account",
            response = UserDTO.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createAccount(@Valid @RequestBody UserRequest userRequest) {
        UserDTO account = userService.create(userRequest);
        return new ResponseEntity<UserDTO>(account, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{accountId}")
    @ApiOperation(value = "Updates an account", notes = "Updates an account for a given account ID",
            response = UserDTO.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> upteAccount(@PathVariable(value = "accountId", name =
            "accountId", required = true) @NotBlank Long accountId, @Valid @RequestBody
                                                               UserDTO user) {
        user.setAccountId(accountId);
        UserDTO account = userService.update(user);
        return new ResponseEntity<UserDTO>(account, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{accountId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete account", notes = "Deletes an account for a given account ID")
    public void deleteAccount(@PathVariable(value = "accountId", name =
            "accountId", required = true) @NotBlank Long accountId) {
        userService.delete(accountId);
    }

    @GetMapping()
    @ApiOperation(value = "Get all accounts", notes = "Retrieves all accounts",
            response = List.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<User>> getAllUsers(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size){
        return new ResponseEntity<Page<User>>(userService.getAll(page, size), HttpStatus.OK);
    }
}
