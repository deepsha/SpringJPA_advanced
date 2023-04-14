package com.app.jira.BugTracker.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.jira.BugTracker.entity.Application;
import com.app.jira.BugTracker.entity.Release;
import com.app.jira.BugTracker.repository.ApplicationRepository;
import com.app.jira.BugTracker.repository.ReleaseRepository;
import com.app.jira.BugTracker.repository.TicketRepository;

import jakarta.transaction.Transactional;

@Service
public class ReleaseService {

	private final ReleaseRepository releaseRepository;
	private final ApplicationRepository applicationRepository;

	public ReleaseService(ReleaseRepository releaseRepository,ApplicationRepository applicationRepository) {
		super();
		this.releaseRepository = releaseRepository;
		this.applicationRepository=applicationRepository;
	}

	public void addRelease(Release release) {
		releaseRepository.save(release);
		if(release.getApplications()!=null && release.getApplications().size()>0)
		{
			for(Application app:release.getApplications()) {
				applicationRepository.save(app);
			}

		}

	}

	public Release getReleaseById(Integer releaseId) {
		return releaseRepository.findById(releaseId).get();

	}

	public void addApptoRelease(Integer appId, Integer releaseId) {
		Release release = getReleaseById(releaseId);
		Application application = applicationRepository.findById(appId).get();   
		if(release.getId()==null && application.getId()==null)
		{
			List<Application> applicationList =new ArrayList<>();
			applicationList.add(application);
			release.setApplications(applicationList);
			addRelease(release);
		}


	}



}
