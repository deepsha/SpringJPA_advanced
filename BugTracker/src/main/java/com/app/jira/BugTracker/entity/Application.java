package com.app.jira.BugTracker.entity;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 * Each application has multiple ticket and releases
 * 
 */
@Entity
@Table(name="APPLICATIONS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="application_id")
	private Integer id;

	@Column(name = "app_name", nullable = false)
	private String name;

	@Column(length = 2000)
	private String description;
	private String owner;
	
	
	public Application() {		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	@Override
	public String toString() {
		return "Application [id=" + id + ", name=" + name + ", description=" + description + ", owner=" + owner + "]";
	}
	
	
}
