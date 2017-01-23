package edu.msg.bookland.server.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.common.model.ServiceException;
import edu.msg.bookland.common.model.UserDTO;
import edu.msg.bookland.common.model.UserType;
import edu.msg.bookland.common.rmi.UserServiceRmi;
import edu.msg.bookland.server.business_logic.BusinesLogicException;
import edu.msg.bookland.server.business_logic.UserBL;
import edu.msg.bookland.server.model.User;

/**
 * Implement methods of UserServiceRmi. Call methods of Business layer
 * 
 * @author Jozsef Solomon
 * @author Terez Sipos
 */
public class UserService extends UnicastRemoteObject implements UserServiceRmi {

	private static final long serialVersionUID = -2602148302307548346L;

	private static final Logger LOGGER = Logger.getLogger(UserService.class);
	private UserBL userBL;

	public UserService() throws RemoteException {
		userBL = UserBL.getInstance();
	}

	@Override
	public List<UserDTO> getAllUsers() throws RemoteException, ServiceException {
		List<User> users = null;
		try {
			users = userBL.getAllUsers();
			LOGGER.info("Sucessfully received all users");
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return MappingService.usersToDTO(users);

	}

	@Override
	public void insertUser(UserDTO userDTO) throws RemoteException, ServiceException {

		StringBuilder validationMessage = ValidationService.checkUser(userDTO);
		if (validationMessage.length() > 0) {
			throw new ServiceException(validationMessage.toString());
		}
		try {
			userBL.insertUser(MappingService.DTOToUser(userDTO));
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());

		}
	}

	@Override
	public void updateUser(UserDTO userDTO) throws RemoteException, ServiceException {

		StringBuilder validationMessage = ValidationService.checkUser(userDTO);
		if (validationMessage.length() > 0) {
			throw new ServiceException(validationMessage.toString());
		}
		try {
			userBL.updateUser(MappingService.DTOToUser(userDTO));
			LOGGER.info("Updated user");
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void deleteUser(String userID) throws RemoteException, ServiceException {
		int id;

		try {
			id = Integer.parseInt(userID);
		} catch (NumberFormatException e) {
			throw new ServiceException("Id is not a number");
		}

		if (id <= 0) {
			throw new ServiceException("id most be a positive number");
		}

		try {
			userBL.deleteUser(userID);
			LOGGER.info("Deleted user with id");
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<UserDTO> searchUser(String name) throws RemoteException, ServiceException {

		List<User> users = null;
		try {
			users = userBL.searchUserByName(name);
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

		return MappingService.usersToDTO(users);

	}

	@Override
	public UserType login(String name, String password) throws RemoteException, ServiceException {

		StringBuilder validationMessage = ValidationService.checkNameAndPassword(name, password);
		if (validationMessage.length() > 0) {
			throw new ServiceException(validationMessage.toString());
		}

		try {
			return userBL.login(name, password);
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());

		}
	}

}
