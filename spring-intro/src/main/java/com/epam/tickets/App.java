package com.epam.tickets;

import com.epam.tickets.facade.BookingFacadeImpl;
import com.epam.tickets.model.dto.Event;
import com.epam.tickets.model.dto.User;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

  private static final Logger logger = LogManager.getLogger(App.class);

  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
    BookingFacadeImpl facade = (BookingFacadeImpl) ctx.getBean("facade");

    logger.warn("== Ticket service started ==");

    User user = new User("Alex", "alex@gmail.com");
    User createdUser = facade.createUser(user);
    logger.info(createdUser);

    User userByEmail = facade.getUserByEmail("jhon@gmail.com");
    String userEmail = userByEmail.toString();
    logger.info(userEmail);

    Event event = new Event("Event 11", new Date());
    logger.info(event);
  }
}
