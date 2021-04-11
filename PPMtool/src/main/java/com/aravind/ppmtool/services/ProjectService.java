package com.aravind.ppmtool.services;

import com.aravind.ppmtool.domain.Backlog;
import com.aravind.ppmtool.domain.Project;
import com.aravind.ppmtool.exceptions.ProjectIDException;
import com.aravind.ppmtool.repositories.BacklogRepository;
import com.aravind.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    public Project saveOrUpdateProject(Project project){
        String projectIdentifier = project.getProjectIdentifier().toUpperCase();
        try{
            project.setProjectIdentifier(projectIdentifier);
            if(project.getId() == null) {
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(projectIdentifier);
            }
            if(project.getId()!=null){
                project.setBacklog(backlogRepository.findByProjectIdentifier(projectIdentifier));
            }

            return projectRepository.save(project);
        }catch (Exception ex){
            throw new ProjectIDException("Project ID '"+ projectIdentifier +"' already exists");
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
