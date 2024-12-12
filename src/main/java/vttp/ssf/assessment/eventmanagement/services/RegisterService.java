package vttp.ssf.assessment.eventmanagement.services;

import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    
    public boolean is21AndAbove(LocalDate dob) {
        
        LocalDate currentDate = LocalDate.now(ZoneId.systemDefault());
        long years = java.time.temporal.ChronoUnit.YEARS.between(dob , currentDate);

        System.out.printf("Years old: %d\n", years);

        if (years >= 21) {
            return true;
        }

        else {
            return false;
        }
    }

    public boolean hasExceededSize(int numberOfTickets, int eventSize, int participants) {

        System.out.printf("number of tickets: %d\n", numberOfTickets);
        System.out.printf("event size: %d\n", eventSize);
        System.out.printf("participants: %d\n", participants);
        
        if (numberOfTickets + participants > eventSize) {
            return true;
        }

        else {
            return false;
        }
    }
}
