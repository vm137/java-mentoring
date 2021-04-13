package com.epam.tickets.services;

import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.model.dto.User;
import java.util.List;

public interface UserService {

  User createUser(String name, String email) throws InvalidUserException;

  User getUserById(Long id) throws InvalidUserException;

  User getUserByEmail(String email) throws InvalidUserException;

  List<User> getUsersByName(String name, int pageSize, int pageNum);
}
