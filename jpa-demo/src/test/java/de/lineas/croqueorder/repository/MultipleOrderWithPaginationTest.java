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
import static org.junit.Assert.assertTrue;

/**
 * Some tests with paging and sorting of MealOrders
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:repository-context.xml", "classpath:test-pagination-data-context.xml"})
@Transactional
@DirtiesContext //cleans the dishes...
public class MultipleOrderWithPaginationTest {

    @Autowired
    private MealOrderRepository orderRepository;


    @Test
    public void testLoadedOrderedMeals() throws Exception {
        assertEquals(56, orderRepository.count());
    }

    @Test
    public void testLoadWithPaging() throws Exception {
        Pageable firstPageRequest = new PageRequest(0, 20);
        final Page<MealOrder> firstPage = orderRepository.findAll(firstPageRequest);
        assertTrue("should be the first page", firstPage.isFirstPage());
        assertEquals(20, firstPage.getContent().size());

        Pageable secondPageRequest = new PageRequest(1, 20);
        final Page<MealOrder> secondPage = orderRepository.findAll(secondPageRequest);
        assertEquals(20, secondPage.getContent().size());
        assertEquals(Long.valueOf(21L), secondPage.getContent().get(0).getId());

        assertEquals(56, secondPage.getTotalElements());
        assertEquals(3, secondPage.getTotalPages());
    }

    @Test
    public void testLoadWithSorting() throws Exception {
        final List<MealOrder> sortedOrders= orderRepository.findAll(new Sort("amount"));
        assertEquals(56, sortedOrders.size());
        assertEquals(1, sortedOrders.get(0).getAmount());
        assertEquals(99, sortedOrders.get(55).getAmount());
    }

    @Test
    public void testPagingWithSorting() throws Exception {
        Pageable pageRequest = new PageRequest(2, 20, Sort.Direction.ASC, "amount");
        final Page<MealOrder> lastPage = orderRepository.findAll(pageRequest);
        assertTrue("should be the last page", lastPage.isLastPage());
        assertEquals(56, lastPage.getTotalElements());
        assertEquals(16, lastPage.getContent().size());
        assertEquals(99, lastPage.getContent().get(56 - 2*20 - 1).getAmount());
    }
}
