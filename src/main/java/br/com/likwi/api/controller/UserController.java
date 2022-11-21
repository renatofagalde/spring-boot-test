package br.com.likwi.api.controller;

import br.com.likwi.api.controller.request.UserRequest;
import br.com.likwi.api.controller.response.UserResponse;
import br.com.likwi.api.domain.User;
import br.com.likwi.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v01/users")
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(this.modelMapper.map(this.userService.findById(id), UserResponse.class));
    }
    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll(){
        List<UserResponse> usersResponse = this.userService.findAll()
                .stream().map(user -> this.modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(usersResponse);
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest user){
        UserResponse userResponse = this.modelMapper.map(
                this.userService.create(this.modelMapper.map(user, User.class)), UserResponse.class);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/v1/users/{id}")
                .buildAndExpand(userResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(userResponse);

    }

}
