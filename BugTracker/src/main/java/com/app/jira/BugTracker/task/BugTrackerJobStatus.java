package com.app.jira.BugTracker.task;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.stereotype.Component;

import com.app.jira.BugTracker.entity.Ticket;
import com.app.jira.BugTracker.repository.TicketRepository;
import com.app.jira.BugTracker.service.util.Status;
import static java.util.stream.Collectors.groupingBy;
@Component
@Endpoint(id="ticket-jobs")
public class BugTrackerJobStatus {
	
	private final TicketRepository ticketRepository;

	public BugTrackerJobStatus(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}
	Map<String,Long> getTicketJobMetrics(){
		return ticketRepository.findAll()
				.stream()
				.collect(groupingBy(Ticket::getStatus,Collectors.counting()));
		
		
		
	}
	

}
