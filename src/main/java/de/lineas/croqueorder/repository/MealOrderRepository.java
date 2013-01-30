package de.lineas.croqueorder.repository;

import de.lineas.croqueorder.domain.Article;
import de.lineas.croqueorder.domain.MealOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;
import java.util.Date;
import java.util.List;

/**
 * Repository for accessing orders directly
 */
public interface MealOrderRepository extends CrudRepository<MealOrder, Long > {

    public List<MealOrder> findByItem(Article item);

    public List<MealOrder> findByItemAndDateBetween(Article item, Date from, Date to);

    @Query("select sum(m.amount) from MealOrder m where m.item = :item")
    public long getAmountOfMeals(@Param("item") Article item);

}
