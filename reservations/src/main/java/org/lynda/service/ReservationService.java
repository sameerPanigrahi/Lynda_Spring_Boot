package org.lynda.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lynda.business.domain.RoomReservation;
import org.lynda.data.entity.Guest;
import org.lynda.data.entity.Reservation;
import org.lynda.data.entity.Room;
import org.lynda.repository.GuestRepository;
import org.lynda.repository.ReservationRepository;
import org.lynda.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

	private RoomRepository roomRepository;
	private GuestRepository guestRepository;
	private ReservationRepository reservationRepository;

	@Autowired
	public ReservationService(RoomRepository roomRepository, GuestRepository guestRepositpry,
			ReservationRepository reservationRepository) {
		super();
		this.roomRepository = roomRepository;
		this.guestRepository = guestRepositpry;
		this.reservationRepository = reservationRepository;
	}

	public List<RoomReservation> getAllRoomReservationsByDate(Date date) {

		Map<Long, RoomReservation> roomReservationMap = new HashMap<>();

		Iterable<Room> rooms = this.roomRepository.findAll();
		rooms.forEach(roomTuple -> {
			RoomReservation roomReservation = new RoomReservation();
			roomReservation.setRoomId(roomTuple.getId());
			roomReservation.setRoomName(roomTuple.getName());
			roomReservation.setRoomNumber(roomTuple.getNumber());
			roomReservationMap.put(roomReservation.getRoomId(), roomReservation);
		});

		Iterable<Reservation> reservations = this.reservationRepository.findByDate(new java.sql.Date(date.getTime()));
		if (reservations != null) {
			reservations.forEach(reservationTuple -> {
				Guest guest = this.guestRepository.findOne(reservationTuple.getGuestId());
				if (guest != null) {
					RoomReservation roomReservation = roomReservationMap.get(reservationTuple.getRoomId());
					roomReservation.setGuestId(guest.getId());
					roomReservation.setFirstName(guest.getFirstName());
					roomReservation.setLastName(guest.getLastName());
					roomReservation.setDate(reservationTuple.getDate());
				}

			});
		}

		List<RoomReservation> allRoomReservations = new ArrayList<RoomReservation>();

		for (Long roomId : roomReservationMap.keySet()) {
			allRoomReservations.add(roomReservationMap.get(roomId));
		}

		return allRoomReservations;
	}

}
