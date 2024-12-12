package vttp.ssf.assessment.eventmanagement.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.models.RegisterForm;
import vttp.ssf.assessment.eventmanagement.services.EventService;
import vttp.ssf.assessment.eventmanagement.services.RegisterService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private EventService eventSvc;

    @Autowired
	private RegisterService registerSvc;
    
    @PostMapping("/register")
    public ModelAndView processRegistration(
    @Valid RegisterForm registerForm,
    BindingResult bindings,
    @RequestBody MultiValueMap<String, String> form
    ) 
    {
        ModelAndView mav = new ModelAndView();

         // Extract the eventIndex from the form
         String eventIndex = form.getFirst("eventIndex");
         Event event = eventSvc.getEvent(Integer.valueOf(eventIndex));

         System.out.printf("Event %s:\n", event);

        if (bindings.hasErrors()) {

            mav.addObject("event", event);
            mav.addObject("eventIndex", eventIndex);
            mav.setViewName("eventregister");
            return mav;
        }

        // Check user age -> User can only register for the event if they are 21 yuear old and above
        LocalDate dob = registerForm.getDob();

        if (!registerSvc.is21AndAbove(dob)) {

            // Create global error
            // ObjectError err = new ObjectError("globalError", "Participants required to be 21 year old and above!");
            // bindings.addError(err);
            
            mav.addObject("error",  "Participants required to be 21 year old and above!");

            mav.setViewName("errorregistration");
            return mav;
        }

        int numberOfTickets = registerForm.getNumberOfTickets();

        if (registerSvc.hasExceededSize(numberOfTickets, event.getEventSize(), event.getParticipants())) {
            
            // Create global error
            // ObjectError err = new ObjectError("globalError", "Requested tickets have exceeded maximum size of event!");
            // bindings.addError(err);

            mav.addObject("error",  "Requested tickets have exceeded maximum size of event!");

            mav.setViewName("errorregistration");
            return mav;
        }

        // If validated
        eventSvc.addParticipants(numberOfTickets, Integer.valueOf(eventIndex));

        mav.setViewName("redirect:/events/listing");

        return mav;
    }

}
