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
}
