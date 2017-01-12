package edu.msg.bookland.repository;

import java.util.List;

import edu.msg.bookland.model.User;

public interface UserDAO {
	List<User> getAllUsers()throws RepositoryException;
	void insertUser(User user) throws RepositoryException;
	void updateUser(User user) throws RepositoryException;
	void deleteUser(User user) throws RepositoryException;
	void updateUserWithoutPassword(User user) throws RepositoryException;
}
