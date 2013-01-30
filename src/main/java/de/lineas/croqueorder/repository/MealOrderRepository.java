package de.lineas.croqueorder.repository;

import de.lineas.croqueorder.domain.Article;
import de.lineas.croqueorder.domain.MealOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Repository for accessing orders directly
 */
public interface MealOrderRepository extends PagingAndSortingRepository<MealOrder, Long > {

    public List<MealOrder> findByItem(Article item);

    public List<MealOrder> findByItemAndDateBetween(Article item, Date from, Date to);

    @Query("select sum(m.amount) from MealOrder m where m.item = :item")
    public long getAmountOfMeals(@Param("item") Article item);

    Page<MealOrder> findAll(Pageable pageable);

    List<MealOrder> findAll(Sort sort);
}
