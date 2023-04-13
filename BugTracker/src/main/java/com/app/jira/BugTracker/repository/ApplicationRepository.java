package com.app.jira.BugTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.jira.BugTracker.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer>{

}
