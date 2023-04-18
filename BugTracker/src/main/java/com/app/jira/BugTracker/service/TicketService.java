package com.app.jira.BugTracker.service;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

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
	public Page<Ticket> getAllTicketsPaging(int page,int size) {
		return ticketRepository.findAll(PageRequest.of(page,size));
	}

	public Ticket getTicketById(int ticketId) {
		if(ticketRepository.existsById(ticketId))
		{
			return ticketRepository.findById(ticketId).get();
		}
		else
			throw new BadReqeustException("incoming id  doesn't exist");
	}

	public void addTicket(Ticket ticket) {
		ticketRepository.save(ticket);
	}

	public void updateTicket(Integer ticketId,Ticket ticket) {
		if (!ticketRepository.existsById(ticketId)) {
			throw new BadReqeustException("incoming id in body doesn't exist");
		}
		this.ticketRepository.save(ticket);
	}


	public void deleteTicket(int ticketId) {
		if (!ticketRepository.existsById(ticketId)) {
			throw new BadReqeustException("incoming id in body doesn't exist");
		}
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

	public Ticket patchTicketByStatus(Integer id, String ticketStatus) {
		Optional<Ticket> ticketOpt=ticketRepository.findById(id);
		if(ticketOpt.isPresent())
		{
			Ticket ticket=ticketOpt.get();
			ticket.setStatus(ticketStatus);
			return ticketRepository.save(ticket);
		}
		else
			throw new BadReqeustException("incoming id doesn't exist");
	}
}
