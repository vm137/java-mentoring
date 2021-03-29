package com.epam.tickets.facade;

import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.model.dao.EventDao;
import com.epam.tickets.model.dao.TicketDao;
import com.epam.tickets.model.dao.UserDao;
import com.epam.tickets.model.dto.Event;
import com.epam.tickets.model.dto.Ticket;
import com.epam.tickets.model.dto.Ticket.Category;
import com.epam.tickets.model.dto.User;
import com.epam.tickets.services.EventServiceImpl;
import com.epam.tickets.services.TicketServiceImpl;
import com.epam.tickets.services.UserServiceImpl;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookingFacadeImpl implements BookingFacade {

  UserDao userDao;
  EventDao eventDao;
  TicketDao ticketDao;
  UserServiceImpl userServiceImpl;
  EventServiceImpl eventServiceImpl;
  TicketServiceImpl ticketServiceImpl;

  private static final Logger logger = LogManager.getLogger(BookingFacadeImpl.class);

  public BookingFacadeImpl() {
  }

  public BookingFacadeImpl(UserDao userDao, EventDao eventDao, TicketDao ticketDao,
      UserServiceImpl userServiceImpl, EventServiceImpl eventServiceImpl, TicketServiceImpl ticketServiceImpl) {
    this.userDao = userDao;
    this.eventDao = eventDao;
    this.ticketDao = ticketDao;
    this.userServiceImpl = userServiceImpl;
    this.eventServiceImpl = eventServiceImpl;
    this.ticketServiceImpl = ticketServiceImpl;
  }

  // Users

  @Override
  public User createUser(User user) {
    String msg = String.format("Create user: %s", user.getName());
    logger.debug(msg);
    return userDao.create(user);
  }

  @Override
  public User getUserById(Long userId) {
    return userDao.read(userId);
  }

  @Override
  public User getUserByEmail(String email) {
    try {
      return userServiceImpl.getUserByEmail(email);
    } catch (InvalidUserException e) {
      String msg = "User with email: " + email + " is not found.";
      logger.warn(msg);
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<User> getUsersByName(String name, int pageSize, int pageNum) {
    return userServiceImpl.getUsersByName(name, pageSize, pageNum);
  }

  @Override
  public User updateUser(User user) {
    return userDao.update(user);
  }

  @Override
  public boolean deleteUser(Long userId) {
    return userDao.delete(userId);
  }

  // Events

  @Override
  public Event getEventById(Long eventId) {
    return eventDao.getEvent(eventId);
  }

  @Override
  public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
    return eventServiceImpl.getEventsByTitle(title, pageSize, pageNum);
  }

  @Override
  public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
    return eventServiceImpl.getEventsForDay(day, pageSize, pageNum);
  }

  @Override
  public Event createEvent(Event event) {
    return eventDao.createEvent(event);
  }

  @Override
  public Event updateEvent(Event event) {
    return eventDao.updateEvent(event);
  }

  @Override
  public boolean deleteEvent(Long eventId) {
    return eventDao.deleteEvent(eventId);
  }

  // Tickets

  @Override
  public Ticket bookTicket(Long userId, Long eventId, int place, Category category) {
    return ticketServiceImpl.createTicket(userId, eventId, place, category);
  }

  @Override
  public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
    return ticketServiceImpl.getBookedTickets(user, pageSize, pageNum);
  }

  @Override
  public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
    return ticketServiceImpl.getBookedTickets(event, pageSize, pageNum);
  }

  @Override
  public boolean cancelTicket(Long ticketId) {
    return ticketDao.deleteTicket(ticketId);
  }
}
