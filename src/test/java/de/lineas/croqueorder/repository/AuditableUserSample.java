package de.lineas.croqueorder.repository;

import de.lineas.croqueorder.auditing.AuditableUser;
import de.lineas.croqueorder.auditing.AuditorAwareness;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * @author Oliver Gierke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:auditing-context.xml" })
@Transactional
public class AuditableUserSample {

	@Autowired
	private AuditableUserRepository repository;

	@Autowired
	private AuditorAwareness auditorAware;

	@Test
	public void testname() throws Exception {

		AuditableUser user = new AuditableUser();
		user.setUsername("username");

		auditorAware.setAuditor(user);

		user = repository.save(user);
		user = repository.save(user);

		assertEquals(user, user.getCreatedBy());
		assertEquals(user, user.getLastModifiedBy());
	}
}
