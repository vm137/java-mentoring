package com.epam.tickets.services;

import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.model.dao.UserDao;
import com.epam.tickets.model.dto.User;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  UserDao userDao;

  public UserService(UserDao userDao) {
    this.userDao = userDao;
  }

  public User getUserByEmail(String email) throws InvalidUserException {
    List<User> allUsers = userDao.getAllUsers();
    return allUsers.stream()
        .filter(user -> email.equalsIgnoreCase(user.getEmail()))
        .findFirst().orElseThrow(() -> new InvalidUserException("User not found"));
  }

  public List<User> getUsersByName(String name, int pageSize, int pageNum) {
    List<User> allUsers = userDao.getAllUsers();
    return allUsers.stream()
        .filter(user -> name.equalsIgnoreCase(user.getName()))
        .skip((long) pageSize * pageNum)
        .limit(pageSize)
        .collect(Collectors.toList());
  }
}
