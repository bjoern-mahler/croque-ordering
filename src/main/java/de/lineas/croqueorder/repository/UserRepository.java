package de.lineas.croqueorder.repository;

import de.lineas.croqueorder.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository for User with added custom repository functionality
 */
public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom {

    /**
     * Should find Users by the amount of their meals...
     * @param amount how much meals one have ordered
     * @return list of users
     */
    List<User> findByMealOrdersAmount(int amount);

}

