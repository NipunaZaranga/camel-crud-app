package com.app.springbootcamelcrudapp.route;

import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.app.springbootcamelcrudapp.model.Event;
import com.app.springbootcamelcrudapp.processor.EventProcessor;
import com.app.springbootcamelcrudapp.processor.EventUpdateProcessor;
import com.app.springbootcamelcrudapp.service.EventService;

@Component
public class EventRoutes extends RouteBuilder {
	
	@BeanInject
	private EventProcessor eventProcessor;
	
	@BeanInject
	private EventUpdateProcessor eventUpdateProcessor;

    private final Environment env;

    public EventRoutes(Environment env) {
        this.env = env;
    }

    public void configure() throws Exception {

        restConfiguration()
             .contextPath(env.getProperty("camel.component.servlet.mapping.contextPath", "/rest/*"))
             .apiContextPath("/api-doc")
             .apiProperty("api.title", "Spring Boot Camel Postgres Rest API.")
             .apiProperty("api.version", "1.0")
             .apiProperty("cors", "true")
             .apiContextRouteId("doc-api")
             .port(env.getProperty("server.port", "8080"))
             .bindingMode(RestBindingMode.json);

        rest().get("/hello").produces(MediaType.APPLICATION_JSON_VALUE).route().setBody(constant("hello stupid"));
        
		rest("/events").consumes(MediaType.APPLICATION_JSON_VALUE).produces(MediaType.APPLICATION_JSON_VALUE)
		
		.get("/list").route().to("{{route.listAllEvents}}").endRest()
		.get("/list/{eventId}").route().to("{{route.listBookById}}").endRest()
		.post("/add").type(Event.class).outType(Event.class).route().process(eventProcessor).to("{{route.addEvent}}").endRest()
		.post("/addAll").route().to("{{route.addEvents}}").endRest()
		.put("/{eventId}").type(Event.class).outType(Event.class).route().process(eventUpdateProcessor).to("{{route.updateEvent}}").endRest()
		.delete("/{eventId}").route().to("{{route.deleteEvent}}").end();
        
        from("{{route.addEvent}}").log("add Event Method invoked").bean(EventService.class, "add").end();
        from("{{route.addEvents}}").log("addAll Events Method invoked").bean(EventService.class, "addAll"); 
		from("{{route.listAllEvents}}").log("List All Events Method invoked").bean(EventService.class, "listAll");
		from("{{route.listBookById}}").log("List EventbyId Method invoked").bean(EventService.class, "listEventById(${header.eventId})");
		from("{{route.deleteEvent}}").log("Delete EventbyId Method invoked").bean(EventService.class, "deleteEventById(${header.eventId})");
		from("{{route.updateEvent}}").log("update EventbyId Method invoked").bean(EventService.class, "UpdateEventById").end();
        
    }

}
