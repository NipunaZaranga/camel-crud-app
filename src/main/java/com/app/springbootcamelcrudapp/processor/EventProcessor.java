package com.app.springbootcamelcrudapp.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.springbootcamelcrudapp.model.Event;
import com.app.springbootcamelcrudapp.service.EventService;

@Component
public class EventProcessor implements Processor {

	@Autowired
	private EventService eventService;

	
	@Override
	public void process(Exchange exchange) throws Exception {

		eventService.add(exchange.getIn().getBody(Event.class));

	}
}
