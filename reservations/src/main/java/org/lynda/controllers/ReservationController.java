package org.lynda.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.lynda.business.domain.RoomReservation;
import org.lynda.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/reservations")
public class ReservationController {

	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private ReservationService $reservationService;

	@RequestMapping(method = RequestMethod.GET)
	// @ResponseBody
	public String getReservations(@RequestParam(value = "date", required = false) String dateString, Model model) {
		Date date;
		if (dateString != null) {
			try {
				date = dateFormat.parse(dateString);
			} catch (ParseException exe) {
				date = new Date();
			}

		} else {
			date = new Date();
		}

		List<RoomReservation> roomReservationList = $reservationService.getAllRoomReservationsByDate(date);
		
		model.addAttribute("roomReservations", roomReservationList);
		return "reservations";
	}

}
