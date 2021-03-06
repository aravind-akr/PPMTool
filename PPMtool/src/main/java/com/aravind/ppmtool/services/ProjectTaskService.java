package com.aravind.ppmtool.services;

import com.aravind.ppmtool.domain.Backlog;
import com.aravind.ppmtool.domain.ProjectTask;
import com.aravind.ppmtool.repositories.BacklogRepository;
import com.aravind.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){

        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        projectTask.setBacklog(backlog);
        Integer backlogSequence= backlog.getPTSequence();
        backlogSequence++;
        backlog.setPTSequence(backlogSequence);
        projectTask.setProjectSequence(projectIdentifier+"-"+backlogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);
        if(projectTask.getPriority()==null) {
            projectTask.setPriority(3);
        }
        if(projectTask.getStatus()==""||projectTask.getStatus()==null){
            projectTask.setStatus("TO_DO");
        }
        return projectTaskRepository.save(projectTask);
    }

    public Iterable<ProjectTask> findBacklogById(String backlog_id) {

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
    }
}
