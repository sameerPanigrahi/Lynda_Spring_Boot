package org.lynda.repository;

import java.util.Date;
import java.util.List;

import org.lynda.data.entity.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

	List<Reservation> findByDate(Date date);

}
