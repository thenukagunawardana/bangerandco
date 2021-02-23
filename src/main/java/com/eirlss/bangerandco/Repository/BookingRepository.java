package com.eirlss.bangerandco.Repository;

import com.eirlss.bangerandco.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer>
{

}
