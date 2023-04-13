package com.app.jira.BugTracker.service;
import java.util.List;

import org.springframework.stereotype.Service;
import com.app.jira.BugTracker.entity.Ticket;

import com.app.jira.BugTracker.repository.TicketRepository;
import com.app.jira.BugTracker.service.util.BadReqeustException;

@Service
public class TicketService {

	private final TicketRepository ticketRepository;


	public TicketService(TicketRepository ticketRepository) {
		super();
		this.ticketRepository = ticketRepository;
	}

	public List<Ticket> getAllTickets() {
		return ticketRepository.findAll();
	}

	public Ticket getTicketById(int ticketId) {
		return ticketRepository.findById(ticketId).get();
	}

	public void addTicket(Ticket ticket) {
		ticketRepository.save(ticket);
	}

	public void updateTicket(Integer id,Ticket ticket) {
		if (id != ticket.getId()) {
			throw new BadReqeustException("incoming id in body doesn't match path");
		}
		this.ticketRepository.save(ticket);
	}


	public void deleteTicket(int ticketId) {
		this.ticketRepository.deleteById(ticketId);
	}

	public void closeTicket(Integer ticketId) {
		Ticket ticket = getTicketById(ticketId);
		ticket.setStatus("Resolved");
		ticketRepository.save(ticket);

	}

	public List<Ticket> getAllTicketsPerApplication(Integer appId) {

		return ticketRepository.getAllTicketsPerApplication(appId);
	}

	public List<Ticket> findTicketByStatus(String status) {

		return ticketRepository.findTicketByStatus(status);
	}
}
