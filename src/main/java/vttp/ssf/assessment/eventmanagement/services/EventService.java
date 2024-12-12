package vttp.ssf.assessment.eventmanagement.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;

@Service
public class EventService {
    
    @Autowired
    private RedisRepository redisRepo;

    public List<Event> getAllEvents() {
        
        Long numberOfEvents = redisRepo.getNumberOfEvents();

        List<Event> events = new ArrayList<>();

        for (int i = 0; i < numberOfEvents; i++) {
            Event event = redisRepo.getEvent(i);

            events.add(event);
        }

        //List<Event> sortedByIdEvents = events.stream()
         //   .sorted((e1, e2) -> Integer.compare(e2.getEventId(), e1.getEventId()))
           // .collect(Collectors.toList());

        return events;
    }

    public Event getEvent(Integer eventIndex) {

        Event event = redisRepo.getEvent(eventIndex);

        return event;
    }

    public void addParticipants(int numberOfTickets, int eventIndex) {
        
        Event event = redisRepo.getEvent(eventIndex);

        System.out.printf("Event before adding: %s", event);

        event.setParticipants(event.getParticipants() + numberOfTickets);

        System.out.printf("Event after adding: %s", event);

        redisRepo.updateEvent(eventIndex, event);
    }
}
