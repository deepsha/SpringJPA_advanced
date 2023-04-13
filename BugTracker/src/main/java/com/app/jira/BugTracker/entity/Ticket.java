package com.app.jira.BugTracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
/*
 * Multiple tickets are mapped to single application and release 
 * 
 */
@Entity
@Table(name="TICKETS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private String status;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    @ManyToOne
    @JoinTable(name ="ticket_release", joinColumns = @JoinColumn(name = "ticket_fk"), inverseJoinColumns = @JoinColumn(name = "release_fk"))
    private Release release;
    
    

	public Ticket(Integer id, String title, String description, String status, Application application,
			Release release) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.application = application;
		this.release = release;
	}

	public Ticket() {	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Release getRelease() {
		return release;
	}

	public void setRelease(Release release) {
		this.release = release;
	}
    
    
    
}
