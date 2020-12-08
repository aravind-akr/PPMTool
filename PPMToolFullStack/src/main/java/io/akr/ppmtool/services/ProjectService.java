package io.akr.ppmtool.services;

import io.akr.ppmtool.domain.Project;
import io.akr.ppmtool.exceptions.ProjectIDException;
import io.akr.ppmtool.repository.ProjectRepository;
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
        }
        catch (Exception ex){
            throw new ProjectIDException("Project ID:'"+project.getProjectIdentifier().toUpperCase()+"' already exists");
        }
    }

    public Project findByProjectIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIDException("Project ID '"+projectId+"' doesn't exist");
        }
        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId);

        if(project == null) {
            throw new ProjectIDException("Cannot delete project with ID'"+projectId+"'. This project doesn't exist");
        }

        projectRepository.delete(project);
    }
}
