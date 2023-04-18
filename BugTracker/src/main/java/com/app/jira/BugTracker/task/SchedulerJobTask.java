package com.app.jira.BugTracker.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.app.jira.BugTracker.entity.Ticket;
import com.app.jira.BugTracker.repository.TicketRepository;

@Component
public class SchedulerJobTask {
	
	@Autowired
	TicketRepository ticketRepository;
	
	private static final Logger logger= LoggerFactory.getLogger(SchedulerJobTask.class);
	
	@Scheduled(cron="*/10 * * * * *")
	public void reportTicketDaily() {
		List<Ticket> ticketsDaily=ticketRepository.findAll();
		logger.info("we have {} tickets for this application"+ticketsDaily.size());
	}

}
