package com.bxp.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bxp.entity.Ticket;
import com.bxp.repository.TicketRepository;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	public Ticket generateTicket(String sourceStation, String destinationStation, int price, LocalDateTime expiryTime) {
		Ticket ticket = new Ticket();
		ticket.setSourceStation(sourceStation);
		ticket.setDestinationStation(destinationStation);
		ticket.setPrice(price);
		ticket.setExpiryTime(expiryTime);
		ticket.setUsageCount(0);
		Ticket ticket2 = ticketRepository.save(ticket);
		return ticket2;
	}

	public boolean validateTicket(int ticketId) {
		Ticket ticket = ticketRepository.findById(ticketId);

		if (ticket != null && !ticket.isExpired()) {
			ticket.incrementUsageCount();
			ticketRepository.save(ticket);
			return true;
		}
		return false;
	}

}
