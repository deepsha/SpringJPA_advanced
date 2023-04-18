package com.app.jira.BugTracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.jira.BugTracker.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{

	@Query("SELECT t FROM Ticket t  where t.id =?1")
	List<Ticket> getAllTicketsPerApplication(Integer appId);
	
	@Query("SELECT t FROM Ticket t WHERE t.status=:status ")
	List<Ticket> findTicketByStatus(@Param("status") String status);
	
	

}
