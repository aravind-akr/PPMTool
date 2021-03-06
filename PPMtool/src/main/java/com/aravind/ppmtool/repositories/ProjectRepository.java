package com.aravind.ppmtool.repositories;

import com.aravind.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {

    Project findByProjectIdentifier(String projectID);

    @Override
    Iterable<Project> findAll();
}
