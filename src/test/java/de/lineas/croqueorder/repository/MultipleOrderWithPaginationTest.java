package de.lineas.croqueorder.repository;

import de.lineas.croqueorder.domain.MealOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Some tests with multiple order objects configured in simple-data.json
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:repository-context.xml", "classpath:test-pagination-data-context.xml"})
@Transactional
@DirtiesContext
public class MultipleOrderWithPaginationTest {

    @Autowired
    private MealOrderRepository orderRepository;


    @Test
    public void testLoadedOrderedMeals() throws Exception {
        assertEquals(56, orderRepository.count());
    }

    @Test
    public void testLoadWithPaging() throws Exception {
        Pageable pageable = new PageRequest(1, 20, new Sort("item"));
        final Page<MealOrder> firstPage = orderRepository.findAll(pageable);
        assertEquals(20, firstPage.getContent().size());
    }


    @Test
    public void testLoadWithSorting() throws Exception {
        final List<MealOrder> sortedOrders= orderRepository.findAll(new Sort("amount"));
        assertEquals(56, sortedOrders.size());
    }
}
