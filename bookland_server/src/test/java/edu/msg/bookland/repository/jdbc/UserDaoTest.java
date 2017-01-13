package edu.msg.bookland.repository.jdbc;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import edu.msg.bookland.model.User;
import edu.msg.bookland.model.UserType;
import edu.msg.bookland.repository.RepositoryException;
import edu.msg.bookland.repository.UserDAO;
import edu.msg.bookland.util.PasswordEncrypting;

/**
 * 
 * Test the User data access object for CRUD operations.
 * 
 * @author Simo Zoltan
 * @author Sipos Terezke
 * @author Szocs Csilla
 *
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest {
<<<<<<< HEAD
	private JDBCUserDAO userDao = new JDBCUserDAO();
=======
	private UserDAO userDao = new JdbcUserDAO();
>>>>>>> refs/heads/Javitas

	private User createUser() {
		User u = new User("");
		u.setName("testUserDAO");
		u.setEmail("testUserDAO@email");
		u.setLoyaltyIndex(10);
		u.setUUID("37a97280-bb03-4b65-b84d-7602f6b6a86u");
		u.setPassword(PasswordEncrypting.encrypt("password", "salt"));
		u.setUserType(UserType.READER);
		return u;
	}

	/**
	 * C from CRUD. Test User insertion.
	 */
	@Test
	public void test0InsertUser() {
		try {
			userDao.insertUser(createUser());
			assertTrue(true);
		} catch (RepositoryException e) {
			Assert.fail("Could no insert user.");
		}
	}

	/**
	 * R from CRUD. Test select all users list is not empty.
	 */
	@Test
	public void test1SelectAllUser() {
		try {
			List<User> users = userDao.getAllUsers();
			assertTrue(!users.isEmpty());
		} catch (RepositoryException e) {
			Assert.fail("Could no get users.");
		}
	}

	/**
	 * R from CRUD. Test select Users with given name.
	 */
	@Test
	public void test2searchUserByName() {
		try {
			List<User> users = userDao.searchUserByName("UserDAO");
			assertTrue(!users.isEmpty());
			Assert.assertEquals(createUser().getUUID(), userDao.searchUserByName("UserDAO").get(0).getUUID());
		} catch (RepositoryException e) {
			Assert.fail("Could no search for users.");
		}
	}

	/**
	 * R from CRUD. Test select User with given name.
	 */
	@Test
	public void test3getUserByName() {
		try {
			Assert.assertEquals(createUser().getUUID(), userDao.getUserByName("UserDAO").getUUID());
		} catch (RepositoryException e) {
			Assert.fail("Could no search for user.");
		}

	}
	
	/**
	 * R from CRUD. Test select User with given id.
	 */
	@Test
	public void test4getUserById() {
		try {
			Assert.assertEquals(userDao.getUserById("37a97280-bb03-4b65-b84d-7602f6b6a86u").getName(),
					createUser().getName());
		} catch (RepositoryException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * U from CRUD. Test User update.
	 */
	@Test
	public void test5updateUser() {
		User u = createUser();
		u.setName("testUser");
		try {
			userDao.updateUser(u);
			assertTrue(true);
		} catch (RepositoryException e) {
			Assert.fail("Could not update User.");
		}
	}

	/**
	 * D from CRUD. Test User delete.
	 */
	@Test
	public void test6deleteUser() {
		User u = createUser();
		try {
			userDao.deleteUser(u);
			assertTrue(true);
		} catch (RepositoryException e) {
			Assert.fail("Could not delete User.");
		}
	}

}
