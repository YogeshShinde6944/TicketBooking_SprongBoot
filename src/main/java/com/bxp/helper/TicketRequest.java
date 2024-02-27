package com.bxp.helper;

import java.util.HashMap;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {

	@Schema(description = "Source station name", example = "A")
	private String sourceStation;

	@Schema(description = "Destination station name", example = "B")
	private String destinationStation;

	private static Map<String, Integer> stationPrices;

	public static int calculateTicketPrice(String sourceStation, String destinationStation) {
		stationPrices = new HashMap<String, Integer>();
		stationPrices.put("A", 0);
		stationPrices.put("B", 5);
		stationPrices.put("C", 10);
		stationPrices.put("D", 15);
		stationPrices.put("E", 20);
		stationPrices.put("F", 25);
		stationPrices.put("G", 30);
		stationPrices.put("H", 35);
		stationPrices.put("I", 40);
		stationPrices.put("J", 45);
		stationPrices.put("K", 50);
		stationPrices.put("L", 55);
		stationPrices.put("M", 60);
		stationPrices.put("N", 65);
		stationPrices.put("O", 70);

		if (stationPrices.containsKey(sourceStation) && stationPrices.containsKey(destinationStation)) {
			int sourcePrice = stationPrices.get(sourceStation);
			int destinationPrice = stationPrices.get(destinationStation);

			return Math.abs(destinationPrice - sourcePrice);
		} else {
			throw new IllegalArgumentException("Invalid source or destination station");
		}
	}

}