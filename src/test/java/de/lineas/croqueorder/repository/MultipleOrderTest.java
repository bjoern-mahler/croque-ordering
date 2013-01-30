package de.lineas.croqueorder.repository;

import de.lineas.croqueorder.domain.Article;
import de.lineas.croqueorder.domain.MealOrder;
import de.lineas.croqueorder.domain.User;
import org.joda.time.DateMidnight;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Some tests with multiple order objects configured in simple-data.json
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:repository-context.xml", "classpath:test-simple-data-context.xml"})
@Transactional
@DirtiesContext
public class MultipleOrderTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MealOrderRepository orderRepository;


    @Test
    public void testLoadedUsers() throws Exception {
        assertEquals(2, userRepository.count());

        Iterable<User> users = userRepository.findAll();

        for (User user : users) {
            assertNotNull(user);
        }
    }


    @Test
    public void testLoadedUsersWithOrders() throws Exception {
        User user = userRepository.findOne(1L);
        assertEquals(5, user.getMealOrders().size());
    }

    @Test
    public void testMealOrderRepositoryCountMeals() throws Exception {
        assertEquals(8, orderRepository.count());
    }

    @Test
    public void testFindByMeal() throws Exception {
        assertEquals(4, orderRepository.findByItem(Article.Croque).size());
    }


    @Test
    public void testFindByMealInRange() throws Exception {
        Date from = new DateMidnight(2012, 12, 23).toDate();
        Date to = new DateMidnight(2013, 1, 3).toDate(); //mind the utc time (see simple-data.json!)

        List<MealOrder> croquesInRange = orderRepository.findByItemAndDateBetween(Article.Croque, from, to);

        assertEquals(3, croquesInRange.size());
    }


    @Test
    public void testSumOfOrderedMealsOfSomeArticle() throws Exception {
        long amountOfMealsOfSalads = orderRepository.getAmountOfMeals(Article.Salad);
        assertEquals(2, amountOfMealsOfSalads); //sad sad... only two salads found...
    }



    @Test
    public void testFindUsersWithMealsByAmount() throws Exception {
        List<User> byMealOrdersAmount = userRepository.findByMealOrdersAmount(99);
        Set<User> setOfUsersFoundByOrdersWithAmountOne = new HashSet<User>(byMealOrdersAmount);
        assertEquals(1, setOfUsersFoundByOrdersWithAmountOne.size());

        byMealOrdersAmount = userRepository.findByMealOrdersAmount(1);
        setOfUsersFoundByOrdersWithAmountOne = new HashSet<User>(byMealOrdersAmount); //huah!
        assertEquals(2, setOfUsersFoundByOrdersWithAmountOne.size());   //not three...
    }
}
