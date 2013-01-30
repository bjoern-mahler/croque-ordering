package de.lineas.croqueorder.repository;

import de.lineas.croqueorder.domain.MealOrder;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for accessing orders directly
 */
public interface OrderRepository extends CrudRepository<MealOrder, Long > {
}
