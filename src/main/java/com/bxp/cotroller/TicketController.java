package com.bxp.cotroller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bxp.entity.Ticket;
import com.bxp.helper.TicketRequest;
import com.bxp.helper.ValidateTicketRequest;
import com.bxp.service.TicketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Yogesh  :  Ticket Booking Api", description = "Ticket Booking Api Information")
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@Operation(summary = "generate ticket", description = "Rest Api use to generate the metro ticket")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfull operation") })
	@PostMapping("/generate")
	public ResponseEntity<?> generateTicket(@RequestBody TicketRequest ticketRequest) {

		String sourceStation = ticketRequest.getSourceStation();
		String destinationStation = ticketRequest.getDestinationStation();
		int price = TicketRequest.calculateTicketPrice(sourceStation, destinationStation); // You need to implement this
																							// method
		LocalDateTime expiryTime = LocalDateTime.now().plusHours(18);
		Ticket generatedTicket = ticketService.generateTicket(sourceStation, destinationStation, price, expiryTime);
		return ResponseEntity.ok(generatedTicket);
	}

	@Operation(summary = "validate ticket", description = "Rest Api use to validate the metro ticket is valid or invalid")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfull operation") })
	@PostMapping("/validate")
	public ResponseEntity<?> validateTicket(@RequestBody ValidateTicketRequest validateTicketRequest) {
		int ticketId = validateTicketRequest.getTicketId();
		boolean isValid = ticketService.validateTicket(ticketId);
		if (isValid) {
			return ResponseEntity.ok("Ticket is valid");
		} else {
			return ResponseEntity.badRequest().body("Invalid or expired ticket");
		}
	}

}
