package com.app.jira.BugTracker.service;

import org.springframework.stereotype.Service;

import com.app.jira.BugTracker.entity.Application;
import com.app.jira.BugTracker.repository.ApplicationRepository;
import com.app.jira.BugTracker.service.util.BadReqeustException;

@Service
public class ApplicationService {
	
	private final ApplicationRepository applicationRepository;

	public ApplicationService(ApplicationRepository applicationRepository) {
		this.applicationRepository = applicationRepository;
	}

	public boolean addApplication(Application application) {
		applicationRepository.save(application);
		return true;
	}

	public Application getApplicationById(Integer id) {
		
		return applicationRepository.getById(id);
	}

	public void updateApplication(Integer id,Application application) {
		if (id != application.getId()) {
            throw new BadReqeustException("incoming id in body doesn't match path");
        }
        this.applicationRepository.save(application);
		
	}

	public void deleteApplication(Integer id) {
		this.applicationRepository.deleteById(id);		
		
	}

}
