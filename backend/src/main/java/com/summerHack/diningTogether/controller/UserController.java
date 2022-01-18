package com.summerHack.diningTogether.controller;

//import com.summerHack.diningTogether.converter.UserConverter;
import com.summerHack.diningTogether.dto.UserDTO;
import com.summerHack.diningTogether.exceptions.UnimplementedException;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
@RestController
@Validated
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    private ModelMapper modelMapper;
    private UserConverter userConverter;
    @GetMapping("/{id}")
    public UserDTO getProfile(@PathVariable long id) throws Exception {
        return userService.getProfile(id);
    }

    @PutMapping("/{id}")
    public UserDTO updateProfile(
            @PathVariable long id,  @RequestBody  UserDTO userDTO) throws Exception {

        final User userInput = userConverter.userDTOToUser(userDTO);
        final User userOutput = userService.update(id, userInput);
        return userConverter.userToUserDTO(userOutput);
    }

}
