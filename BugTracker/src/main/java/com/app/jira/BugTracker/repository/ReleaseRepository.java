package com.app.jira.BugTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.jira.BugTracker.entity.Release;


public interface ReleaseRepository extends JpaRepository<Release, Integer>{

}
