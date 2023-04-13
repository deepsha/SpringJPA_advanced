package com.app.jira.BugTracker.controller;

import java.net.http.HttpHeaders;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.jira.BugTracker.entity.Application;
import com.app.jira.BugTracker.entity.Release;
import com.app.jira.BugTracker.entity.Ticket;
import com.app.jira.BugTracker.service.ApplicationService;
import com.app.jira.BugTracker.service.ReleaseService;
import com.app.jira.BugTracker.service.TicketService;
import com.app.jira.BugTracker.service.util.NotFoundException;
/*
 * this is bugtracker application where we have 3 entities
 * Release:
 * Application:
 * Tickets
 * 
 */
@RestController
@RequestMapping("/tza")
public class BugTrackerController {
	
	 private Logger logger = LoggerFactory.getLogger(BugTrackerController.class);

	private final ApplicationService applicationService;
	private final ReleaseService releaseService;
	private final TicketService ticketService;



	public BugTrackerController(ApplicationService applicationService, ReleaseService releaseService,
			TicketService ticketService) {
		super();
		this.applicationService = applicationService;
		this.releaseService = releaseService;
		this.ticketService = ticketService;
	}

	@PostMapping("/application")
	public ResponseEntity<Void> addApplication(@RequestBody Application application) {
		boolean flag = applicationService.addApplication(application);
		if (!flag) return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping("/application/{id}")
	public ResponseEntity<Application> getApplicationById(@PathVariable("id") Integer id) {
		Application app = applicationService.getApplicationById(id);
		if(app.getId()==0)
			throw new NotFoundException("Application does not exist");
		return new ResponseEntity<Application>(app, HttpStatus.OK);

	}

	@PutMapping("/application")
	public ResponseEntity<Application> updateApplication(@RequestBody Application application) {
		applicationService.updateApplication(application.getId(),application);
		return new ResponseEntity<Application>(application, HttpStatus.OK);
	}

	@DeleteMapping("/application/{id}")
	public ResponseEntity<Void> deleteApplication(@PathVariable("id") Integer id) {
		applicationService.deleteApplication(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/ticket/{id}")
	public ResponseEntity<Ticket> getTicketById(@PathVariable("id") Integer id) {
		Ticket ticket = ticketService.getTicketById(id);
		return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);

	}

	@GetMapping("/tickets")
	public ResponseEntity<List<Ticket>> getAllTickets() {
		List<Ticket> list = ticketService.getAllTickets();
		return new ResponseEntity<List<Ticket>>(list, HttpStatus.OK);
	}

	@PostMapping("/ticket")
	public ResponseEntity<Void> addTicket(@RequestBody Ticket ticket) {
		ticketService.addTicket(ticket);		
		return new ResponseEntity<Void>( HttpStatus.CREATED);
	}

	@PutMapping("/ticket")
	public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket) {
		ticketService.updateTicket(ticket.getId(),ticket);
		return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
	}

	@DeleteMapping("/ticket/{id}")
	public ResponseEntity<Void> deleteTicket(@PathVariable("id") Integer id) {
		ticketService.deleteTicket(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/ticket/{id}")
	public ResponseEntity<Ticket> closeTicket(@PathVariable("id") Integer id) {
		ticketService.closeTicket(id);
		return new ResponseEntity<Ticket>(HttpStatus.OK);
	}

	@PostMapping("/release")
	public ResponseEntity<Void> addRelease(@RequestBody Release release) {
		releaseService.addRelease(release);
		return new ResponseEntity<Void>( HttpStatus.CREATED);
	}

	@PutMapping("/release/{appid}/{releaseid}")
	public ResponseEntity<Void> addApptoRelease(@PathVariable("appid") Integer appid, @PathVariable("releaseid") Integer releaseid) {
		releaseService.addApptoRelease(appid, releaseid);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	// get total number ticket per every release
	@GetMapping("/tickets/application/{id}")
	public ResponseEntity<List<Ticket>> getAllTicketsPerApplication(@PathVariable("id") Integer appId) {
		List<Ticket> ticketList = ticketService.getAllTicketsPerApplication(appId);
		logger.info("getAllTicketsPerApplication:"+ticketList.size());
		return new ResponseEntity<List<Ticket>>(ticketList, HttpStatus.OK);
	}
	
	
	@GetMapping("/tickets/{status}")
	public ResponseEntity<List<Ticket>> findTicketByStatus(@PathVariable("status") String ticketStatus) {
		List<Ticket> list = ticketService.findTicketByStatus(ticketStatus);
		return new ResponseEntity<List<Ticket>>(list, HttpStatus.OK);
	}

}
