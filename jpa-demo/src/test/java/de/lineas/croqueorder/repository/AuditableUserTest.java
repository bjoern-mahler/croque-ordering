package de.lineas.croqueorder.repository;

import de.lineas.croqueorder.auditing.AuditableUser;
import de.lineas.croqueorder.auditing.AuditorAwareness;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @author Oliver Gierke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:auditing-context.xml" })
@Transactional
public class AuditableUserTest {

	@Autowired
	private AuditableUserRepository repository;

	@Autowired
	private AuditorAwareness auditorAware;

	@Test
	public void testSavingAuditableUser() throws Exception {

		AuditableUser user = new AuditableUser();
		user.setUsername("username");
		auditorAware.setAuditor(user);

		user = repository.save(user);

        assertEquals(user, user.getCreatedBy());
        assertEquals(user, user.getLastModifiedBy());
	}

    @Test
    public void testGettingAuditableUsers() throws Exception {
        AuditableUser user1 = new AuditableUser();
        user1.setUsername("Heino");

        AuditableUser user2 = new AuditableUser();
        user2.setUsername("RodRodriguez");

        auditorAware.setAuditor(user1);

        user1 = repository.save(user1);
        repository.save(user2);

        assertEquals(user1, user1.getCreatedBy());
        assertEquals(user1, user2.getCreatedBy());


        final AuditableUser rodRodriguez = repository.findByUsername("RodRodriguez");
        assertNotNull("rod should exist", rodRodriguez);
    }
}
