package com.app.springbootcamelcrudapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.springbootcamelcrudapp.model.Event;
import com.app.springbootcamelcrudapp.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepo;

	public Event add(Event event) {
		Event addedEvent = eventRepo.save(event);
		return addedEvent;

	}

	public List<Event> listAll() {
		List<Event> allEvents = eventRepo.findAll();
		return allEvents;
	}

	public Event listEventById(UUID eventId) {
		Event event = eventRepo.findById(eventId).get();
		return event;
	}

	public Event UpdateEventById(UUID eventId, Event dataToUpdate) {
		Event event = eventRepo.findById(eventId).get();

		event.setTransId(dataToUpdate.getTransId());
		event.setTransTms(dataToUpdate.getTransTms());
		event.setRcNum(dataToUpdate.getRcNum());
		Event updatedEvent = eventRepo.save(event);
		return updatedEvent;
	}

	public Map<String, Boolean> deleteEventById(UUID eventId) {
		Event event = eventRepo.findById(eventId).get();

		eventRepo.delete(event);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@SuppressWarnings("unchecked")
	public void addAll(Map<String, Object> request) {

		List<Object> records = new ArrayList<>();
		records = (List<Object>) request.get("records");
		int count = 0;
		for (Object record : records) {
			List<Object> events = new ArrayList<>();
			events = (List<Object>) ((Map<String, Object>) record).get("event");

			for (Object event : events) {
				Event eventObj = new Event();
				eventObj.setTransId((String) ((Map<String, Object>) record).get("transId"));
				eventObj.setTransTms((String) ((Map<String, Object>) record).get("transTms"));
				eventObj.setRcNum((String) ((Map<String, Object>) record).get("rcNum"));
				eventObj.setClientId((String) ((Map<String, Object>) record).get("clientId"));
				eventObj.setEventCnt((Integer) ((Map<String, Object>) event).get("eventCnt"));
				eventObj.setLocationCd((String) ((Map<String, Object>) event).get("locationCd"));
				eventObj.setLocationId1((String) ((Map<String, Object>) event).get("locationId1"));
				eventObj.setLocationId2((String) ((Map<String, Object>) event).get("locationId2"));
				eventObj.setAddrNbr((String) ((Map<String, Object>) event).get("addrNbr"));

				eventRepo.save(eventObj);
				count++;
			}
		}
		System.out.println(count + "events has been inserted");
	}

}
