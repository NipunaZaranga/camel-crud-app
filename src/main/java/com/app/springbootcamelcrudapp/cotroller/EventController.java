package com.app.springbootcamelcrudapp.cotroller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.springbootcamelcrudapp.model.Event;
import com.app.springbootcamelcrudapp.service.EventService;

@RestController
@RequestMapping("rest/events")
public class EventController {

	@Autowired
	private EventService eventService;
	
	@GetMapping("/list")
	public List<Event> getAllEvents() {
		return eventService.listAll();
	}

	
	@GetMapping("/list/{id}")
	public Event getEventbyId(@PathVariable(value = "id") UUID eventId) {

		return eventService.listEventById(eventId);
	}

	@PutMapping("/{id}")
	public Event updateEvent(@PathVariable(value = "id") UUID eventId, @RequestBody Event dataToUpdate) {
		return eventService.UpdateEventById(eventId, dataToUpdate);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteEvent(@PathVariable(value = "id") UUID eventId) {
		return eventService.deleteEventById(eventId);
	}
}
