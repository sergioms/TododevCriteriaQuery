package com.wordpress.tododev.criteria.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import static org.junit.Assert.*;

import com.wordpress.tododev.criteria.entities.User;

public class UserTest {
	
	@Test
	public void testUserCriteria(){

		EntityManagerFactory emf = null;
		EntityManager em = null;
	    try {
			emf = Persistence.createEntityManagerFactory("criteria");
			em = emf.createEntityManager();

			final CriteriaBuilder cb = em.getCriteriaBuilder();
			final CriteriaQuery<User> q = cb.createQuery(User.class);
			final Root<User> users = q.from(User.class);
			final Predicate condition = cb.equal(users.get("privilegeLevel"), 5);
			q.select(users).where(condition).orderBy(cb.asc(users.get("userId")));
				
			em.getTransaction().begin();
			List<User> result = em.createQuery(q).getResultList();
			em.getTransaction().commit();
			
			assertNotNull(result);
			assertEquals(2, result.size());
			
			assertEquals(1, (int)result.get(0).getUserId());
			assertEquals("Pepe", result.get(0).getName());
			
			assertEquals(3, (int)result.get(1).getUserId());
			assertEquals("Dolores", result.get(1).getName());

		} catch (Exception e) {
			fail("Unexpected Exception " + e.getMessage());
		} finally {
			if (em != null)
				em.close();
			if (emf != null)
				emf.close();
		}
	}
}
