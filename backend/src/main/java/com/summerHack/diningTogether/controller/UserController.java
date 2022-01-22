package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.dto.FoodDTO;
import com.summerHack.diningTogether.dto.UpdateUserInput;
import com.summerHack.diningTogether.dto.UserApplicationDTO;
import com.summerHack.diningTogether.dto.UserDTO;
import com.summerHack.diningTogether.exceptions.UnAuthorizedUserAccessException;
import com.summerHack.diningTogether.exceptions.UserNotFoundException;
import com.summerHack.diningTogether.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public UserDTO getProfile(@PathVariable long id) throws UserNotFoundException, UnAuthorizedUserAccessException {
        return userService.getProfile(id);
    }

    @Operation(summary = "update profile")
    @PutMapping("/{id}")
    public UserDTO updateProfile(
        @PathVariable long id, @RequestBody UpdateUserInput userInput) throws Exception {

        return userService.update(id, userInput);
    }

    @Operation(summary = "get all applications of the user")
    @GetMapping("/{id}/applications")
    public List<UserApplicationDTO> getAllApplications(@PathVariable long id) {
        return userService.getAllApplicationsOfUser(id);
    }

    @Operation(summary = "get all food provided")
    @GetMapping("/{id}/foods")
    public List<FoodDTO> getAllFoodProvided(@PathVariable long id)
        throws UserNotFoundException, UnAuthorizedUserAccessException {

        return userService.getAllFoodProvidedByUser(id);
    }
}
