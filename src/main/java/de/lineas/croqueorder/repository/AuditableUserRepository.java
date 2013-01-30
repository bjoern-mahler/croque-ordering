package de.lineas.croqueorder.repository;

import de.lineas.croqueorder.auditing.AuditableUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Simple Repository for AuditableUsers
 */
public interface AuditableUserRepository extends CrudRepository<AuditableUser, Long> {

    List<AuditableUser> findByUsername(String username);

}
