package com.app.jira.BugTracker.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/*
 * Each Release has multiple application
 * 
 */
@Entity
@Table(name="RELEASES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Release {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;

	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	//	private LocalDate releaseDate;
	@Column(name="RELEASE_DATE")
	private String releaseDate;
	private String description;
	@Column(name="RELEASE_MANAGER")
	private String releaseManager;

	@OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	private List<Application> applications = new ArrayList<>();

	public Release() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public String getReleaseManager() {
		return releaseManager;
	}

	public void setReleaseManager(String releaseManager) {
		this.releaseManager = releaseManager;
	}



}
