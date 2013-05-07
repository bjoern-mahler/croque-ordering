package de.lineas.croqueorder.repository;

import de.lineas.croqueorder.auditing.AuditableUser;
import org.springframework.data.repository.CrudRepository;

/**
 * Simple Repository for AuditableUsers
 */
public interface AuditableUserRepository extends CrudRepository<AuditableUser, Long> {


    /**
     * @param username the unique username of a user
     * @return a user with the given username
     */
    AuditableUser findByUsername(String username);

}
