package org.lynda.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.lynda.data.entity.Room;

@Component
@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

	Room findByNumber(String number);
}
