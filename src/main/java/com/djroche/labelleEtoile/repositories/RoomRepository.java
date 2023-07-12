package com.djroche.labelleEtoile.repositories;

import com.djroche.labelleEtoile.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository <Room, Long> {

}
