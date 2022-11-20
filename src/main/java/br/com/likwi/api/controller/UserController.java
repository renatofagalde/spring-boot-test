package br.com.likwi.api.controller;

import br.com.likwi.api.controller.response.UserResponse;
import br.com.likwi.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
