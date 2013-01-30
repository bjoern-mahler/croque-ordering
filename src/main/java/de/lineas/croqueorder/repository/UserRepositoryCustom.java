package de.lineas.croqueorder.repository;

import java.util.List;

import de.lineas.croqueorder.domain.User;

/**
 * Interface for repository functionality that ought to be implemented manually.
 * 
 * @author Oliver Gierke
 */
interface UserRepositoryCustom {

	/**
	 * Custom repository operation.
	 * 
	 * @return
	 */
	List<User> myCustomBatchOperation();
}
