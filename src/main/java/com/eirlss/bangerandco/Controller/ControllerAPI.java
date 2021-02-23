package com.eirlss.bangerandco.Controller;


import com.eirlss.bangerandco.Model.VehicleWBS;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ControllerAPI
{
    @RequestMapping(value = "/webScraping",method = RequestMethod.GET)
    public List<VehicleWBS> webScraping()
    {
        List<VehicleWBS>webScrapedVehicleList=new ArrayList<>();

        final String url="http://www.rentcarsrilanka.com/rent-a-car-srilanka-rates.php";

        try {
            final Document document = Jsoup.connect(url).get();
            for(Element row: document.select("table.table.selfdriverates tr")) {

                VehicleWBS vehicle = new VehicleWBS();

                final String vehicleName= row.select("td.text-left.percent-40").text();
                if(!vehicleName.contentEquals("")) {
                    vehicle.setCarName(vehicleName);
                }
                final String rates = row.select("td.text-center.percent-22").text();
                if(!rates.contentEquals("")) {

                    String[] priceList = rates.split(" ");

                    vehicle.setMonthlyRate(priceList[0]);
                    vehicle.setWeeklyRate(priceList[1]);
                    vehicle.setExcessMileage(priceList[2]);

                }

                if(vehicle.getCarName()!=null) {
                    webScrapedVehicleList.add(vehicle);
                }

            }

        }catch(Exception e) {
            e.getMessage();
        }

        return webScrapedVehicleList;
    }

    @RequestMapping(value="/VehicleRates/loadAll")
    String getVehicleRates(Model model) {
        model.addAttribute("ratesList",webScraping());
        return "vehicleRates";
    }
}
