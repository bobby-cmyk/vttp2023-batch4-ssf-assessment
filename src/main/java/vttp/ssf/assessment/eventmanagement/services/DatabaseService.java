package vttp.ssf.assessment.eventmanagement.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.ssf.assessment.eventmanagement.models.Event;

@Service
public class DatabaseService{
    
    public List<Event> readFile(String fileName) throws FileNotFoundException, IOException{

        List<Event> events = new LinkedList<>();

        FileReader fr = new FileReader(fileName);

        BufferedReader br = new BufferedReader(fr);

        // Initialise a string for read lines
        String eventsString = "";

        // Loop through the lines and read them until end of document
        while (true) {
            String line  = br.readLine();

            if (line == null) {
                break;
            }
            
            eventsString += line;
        }

        // Close the readers
        br.close();
        fr.close();

        // Use JsonReader to read the String file
        JsonReader reader = Json.createReader(new StringReader(eventsString));

        JsonArray eventsArr = reader.readArray();

        for (int i = 0; i < eventsArr.size(); i++) {
            JsonObject eventObj = eventsArr.getJsonObject(i);

            Event event = new Event();

            event.setEventId(eventObj.getInt("eventId"));

            event.setEventName(eventObj.getString("eventName"));

            event.setEventSize(eventObj.getInt("eventSize"));

            event.setEventDate(eventObj.getJsonNumber("eventDate").longValue());

            event.setParticipants(eventObj.getInt("participants"));

            System.out.println(event);

            events.add(event);
        }

        return events;
    }
}
