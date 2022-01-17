package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.converter.ApplicationConverter;
import com.summerHack.diningTogether.dto.ApplicationDTO;
import com.summerHack.diningTogether.dto.UpdateApplicationStatusInput;
import com.summerHack.diningTogether.model.Application;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.service.ApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "application for food list")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/foods/{id}/applications")
public class ApplicationController {

    private ApplicationService applicationService;
    private ApplicationConverter applicationConverter;

    @PostMapping("/")
    @ApiOperation(value = "submit application")
    public Application submitApplication(
        @PathVariable("id") int foodId,
        @RequestBody ApplicationDTO applicationDTO) {
        return applicationService.save(applicationConverter.applicationDtoToApplication(applicationDTO));
    }

    @PatchMapping("/{candidateId}")
    @ApiOperation(value = "Update application status", notes = "approve or reject")
    public Application updateStatus(
        @PathVariable("id") Integer foodId,
        @PathVariable("candidateId") Integer candidateId,
        @RequestBody UpdateApplicationStatusInput input) {

        switch (input.getStatus()) {
            case ACCEPTED:
                return applicationService.approve(foodId, candidateId);
            case DECLINED:
                return applicationService.reject(foodId, candidateId);
            default:
                // TODO: a better response message
                throw new RuntimeException("Only ACCEPTED and DECLINED is acceptable");
        }
    }

    @GetMapping("/")
    @ApiOperation(value = "Return all applications of the food")
    public List<User> getAllApplications(@PathVariable("id") Integer foodId) {
        return applicationService.getAllCandidates(foodId);
    }
}
