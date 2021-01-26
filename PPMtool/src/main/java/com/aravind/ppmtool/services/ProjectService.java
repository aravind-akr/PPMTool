package com.aravind.ppmtool.services;

import com.aravind.ppmtool.domain.Project;
import com.aravind.ppmtool.exceptions.ProjectIDException;
import com.aravind.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception ex){
            throw new ProjectIDException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
        }
    }

    public Project findProjectByIdentifier(String projectID){

        Project project = projectRepository.findByProjectIdentifier(projectID.toUpperCase());
        if(project == null){
            throw new ProjectIDException("Project ID '"+projectID+"' does not exists");
        }
        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectID){
        Project project = projectRepository.findByProjectIdentifier(projectID);
        if(project == null){
            throw new ProjectIDException("Cannot delete with Project ID'"+projectID+"'. This project doesn't exist");
        }
        projectRepository.delete(project);
    }
}
