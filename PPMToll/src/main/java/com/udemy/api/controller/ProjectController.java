package com.udemy.api.controller;

import com.udemy.api.domain.Project;
import com.udemy.api.services.ProjectService;
import com.udemy.api.services.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ValidationErrorService validationErrorService;

    @PostMapping("/save")
    public ResponseEntity<?> saveProject(@Valid @RequestBody Project project, BindingResult result){

        ResponseEntity<?> errorMap = validationErrorService.mapValidationService(result);
        if(errorMap != null) return errorMap;

        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{projectId}")
    public ResponseEntity<?> getProjectByProjectId(@PathVariable("projectId") String projectId){
        Project project = projectService.findProjectByIdentifier(projectId);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public  Iterable<Project> getAllProjects(){
        return projectService.findAllProjects();
    }

    @DeleteMapping("/deleteById/{projectId}")
    public ResponseEntity<?> deleteProjectByProjectId(@PathVariable("projectId") String projectId){
        projectService.deleteByIdentifier(projectId);
        return new ResponseEntity<String>("The project with '" + projectId +"' is deleted successfully.", HttpStatus.OK);
    }

}
