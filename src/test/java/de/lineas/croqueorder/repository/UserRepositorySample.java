package de.lineas.croqueorder.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import de.lineas.croqueorder.domain.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Intergration test showing the basic usage of {@link de.lineas.croqueorder.repository.UserRepository}.
 * 
 * @author Oliver Gierke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:repository-context.xml")
@Transactional
public class UserRepositorySample {

	@Autowired
    UserRepository repository;

	/**
	 * Tests inserting a user and asserts it can be loaded again.
	 */
	@Test
	public void testInsert() {

		User user = new User();
		user.setUsername("username");

		user = repository.save(user);

		assertEquals(user, repository.findOne(user.getId()));
	}


	/**
	 * Test invocation of custom method.
	 */
	@Test
	public void testCustomMethod() {

		User user = new User();
		user.setUsername("username");

		user = repository.save(user);

		List<User> users = repository.myCustomBatchOperation();

		assertNotNull(users);
		assertTrue("should contain the user",users.contains(user));
	}
}
