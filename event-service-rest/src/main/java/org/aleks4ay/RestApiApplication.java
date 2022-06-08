package org.aleks4ay;

import org.aleks4ay.dto.Event;
import org.aleks4ay.impl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class RestApiApplication implements CommandLineRunner {

	@Autowired
	private EventServiceImpl eventService;

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createEvents();
	}

	/**
	 * Initialize events
	 */
	private void createEvents(){
		eventService.createEvent(new Event("title 1", "place one", "Anatoly", "Lecture",
				LocalDateTime.of(2022, 6 , 2, 12, 0 ,1)));
		eventService.createEvent(new Event("title 2", "place two", "Anatoly", "Lecture",
				LocalDateTime.of(2022, 6 , 3, 12, 0 ,1)));
		eventService.createEvent(new Event("title 2", "place two", "Dmitry", "Dans",
				LocalDateTime.of(2022, 6 , 4, 22, 10 ,0)));
	}
}
