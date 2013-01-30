package de.lineas.croqueorder.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import de.lineas.croqueorder.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for {@link de.lineas.croqueorder.domain.User} instances. Provides basic CRUD operations due to the extension of
 * {@link JpaRepository}. Includes custom implemented functionality by extending {@link UserRepositoryCustom}.
 * 
 * @author Oliver Gierke
 */
public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom {


    List<User> findByFirstnameOrderByFirstnameAsc(String firstname);

	/**
	 * Find all users with the given lastname. This method will be translated into a query by constructing it directly
	 * from the method name as there is no other query declared.
	 * 
	 * @param lastname
	 * @return
	 */
	List<User> findByLastname(String lastname);

	/**
	 * Returns all users with the given firstname. This method will be translated into a query using the one declared in
	 * the {@link Query} annotation declared one.
	 * 
	 * @param firstname
	 * @return
	 */
	@Query("select u from User u where u.firstname = ?1")
	List<User> findByFirstname(String firstname);



    /**
     *
     * @return users who has meals ordered in range
     */
    List<User> findByMealOrdersDateBetween(Date from, Date to);


    List<User> findByMealOrdersAmount(int amount);

}
