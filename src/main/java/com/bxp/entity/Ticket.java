package com.bxp.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String sourceStation;
	private String destinationStation;
	private int price;
	private int usageCount;
	private LocalDateTime expiryTime;

	public boolean isExpired() {
		return LocalDateTime.now().isAfter(expiryTime);
	}

	public void incrementUsageCount() {
		this.usageCount++;
	}

}
