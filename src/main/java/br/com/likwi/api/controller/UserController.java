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

    public static final String ID = "/{id}";
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @GetMapping(value = ID)
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
        UserResponse userResponse = getUserResponse(this.userService.create(this.modelMapper.map(user, User.class)));

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/v1/users/{id}")
                .buildAndExpand(userResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(userResponse);

    }

    @PutMapping(ID)
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UserRequest user){

        user.setId(id);
        return ResponseEntity.ok().body(getUserResponse(this.userService.update(this.modelMapper.map(user, User.class))));

    }

    @DeleteMapping(ID)
    public ResponseEntity<UserResponse> delete(@PathVariable Long id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();

    }

    private UserResponse getUserResponse(User userService) {
        UserResponse userResponse = this.modelMapper.map(
                userService, UserResponse.class);
        return userResponse;
    }

}
