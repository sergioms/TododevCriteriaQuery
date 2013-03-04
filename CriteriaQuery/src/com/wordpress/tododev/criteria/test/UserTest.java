package com.wordpress.tododev.criteria.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Metamodel;

import org.junit.Test;

import com.wordpress.tododev.criteria.entities.User;
import com.wordpress.tododev.criteria.entities.User_;

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
	
	@Test
	public void testUserCriteriaMetaModel(){

		EntityManagerFactory emf = null;
		EntityManager em = null;
	    try {
			emf = Persistence.createEntityManagerFactory("criteria");
			em = emf.createEntityManager();

			final CriteriaBuilder cb = em.getCriteriaBuilder();
			final CriteriaQuery<User> q = cb.createQuery(User.class);
			final Metamodel m = em.getMetamodel();
			final Root<User> user = q.from(m.entity(User.class));
			final Predicate condition = cb.equal(user.get(User_.privilegeLevel), 5);
			q.select(user).where(condition).orderBy(cb.asc(user.get("userId")));
				
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
