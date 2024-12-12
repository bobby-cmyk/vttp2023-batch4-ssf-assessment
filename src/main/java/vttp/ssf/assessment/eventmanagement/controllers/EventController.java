package vttp.ssf.assessment.eventmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.models.RegisterForm;
import vttp.ssf.assessment.eventmanagement.services.EventService;

@Controller
@RequestMapping("/events")
public class EventController {

	@Autowired
	private EventService eventSvc;
	
	@GetMapping("/listing")
	public ModelAndView displayEvents() {

		ModelAndView mav = new ModelAndView();

		List<Event> events = eventSvc.getAllEvents();

		mav.addObject("events", events);
		mav.setViewName("listing");

		return mav;
	}

	@GetMapping("/register/{eventIndex}")
	public ModelAndView getRegisterForm(
		// We use this so that we can have access to the eventIndex in the server side
		@PathVariable(name="eventIndex") String eventIndex
	) 
	{
		ModelAndView mav = new ModelAndView();

		RegisterForm registerForm = new RegisterForm();

		Event event = eventSvc.getEvent(Integer.valueOf(eventIndex));

		mav.addObject("registerForm", registerForm);

		System.out.printf("Event index: %s\n".formatted(eventIndex));

		mav.addObject("eventIndex", eventIndex);
		mav.addObject("event", event);
		mav.setViewName("eventregister");
		return mav;
	}

}
