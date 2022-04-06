package com.udemy.api.services;

import com.udemy.api.domain.Project;
import com.udemy.api.exceptions.ProjectIDException;
import com.udemy.api.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception ex){
            throw new ProjectIDException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already present in the system.");
        }

    }

}
