package de.lineas.croqueorder.repository;

import de.lineas.croqueorder.domain.Article;
import de.lineas.croqueorder.domain.User;
import de.lineas.croqueorder.repository.simple.SimpleUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Ordering tests
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:simple-repository-context.xml")
@Transactional
public class OrderTest {

	@Autowired
	private SimpleUserRepository repository;

    private User user;

	@Before
	public void setUp() {
		user = new User();
		user.setUsername("iWoz");
		user.setFirstname("Steve");
		user.setLastname("Wozniak");

        user = repository.save(user);
	}

	/**
	 * Tests making an order
	 */
	@Test
	public void testMakeAnOrder() {
        assertTrue("should not contain an order at first", user.getMealOrders().isEmpty());
        user.order(3, Article.Croque);
		assertTrue("should have some order in its list of orders", !user.getMealOrders().isEmpty());
        assertEquals(3, repository.findOne(user.getId()).getMealOrders().get(0).getAmount());
	}

    @Test
    public void testFindOrderAfterCreating() throws Exception {
        user.order(3, Article.Croque);





    }
}
