package com.eirlss.bangerandco.Service;


import com.eirlss.bangerandco.DTO.BookingDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BookingService
{

    boolean saveBooking(@RequestParam(value = "equipmentID", required = false) List<Long> equipmentList,
                        BookingDTO bookingDTO);
}
