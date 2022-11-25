package br.com.likwi.api.controller;

import br.com.likwi.api.controller.request.UserRequest;
import br.com.likwi.api.controller.response.UserResponse;
import br.com.likwi.api.domain.User;
import br.com.likwi.api.services.UserCreateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/v01/users")
public class UserCreateController {

    public static final String ID = "/{id}";
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserCreateService userCreateService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest){

        UserResponse userResponse = getUserResponse(this.userCreateService.create(this.modelMapper.map(userRequest, User.class)));

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/v1/users/{id}")
                .buildAndExpand(userResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(userResponse);

    }

    private UserResponse getUserResponse(User userService) {
        UserResponse userResponse = this.modelMapper.map(
                userService, UserResponse.class);
        return userResponse;
    }

}
