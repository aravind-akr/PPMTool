package com.aravind.ppmtool.web;

import com.aravind.ppmtool.domain.Project;
import com.aravind.ppmtool.services.MapValidationErrorService;
import com.aravind.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationErrorService(result);
        if(errorMap!=null) return errorMap;
        
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectByID(@PathVariable String projectId){
        Project project = projectService.findProjectByIdentifier(projectId.toUpperCase());
        return  new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects(){
        return projectService.findAllProjects();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId){
        projectService.deleteProjectByIdentifier(projectId.toUpperCase());
        return new ResponseEntity<String>("Project with ID:"+projectId+" was deleted",HttpStatus.OK);
    }

}
