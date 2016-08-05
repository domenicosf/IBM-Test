package com.ibm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ibm.model.User;

public class UserRepository {

	private final EntityManagerFactory entityManagerFactory;
	private final EntityManager entityManager;

	public UserRepository() {
		// Criando EntityManagerFactory com propriedades do arquivo
		// persistence.xml
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit_db_user");

		this.entityManager = this.entityManagerFactory.createEntityManager();
	}

	/*
	 * Cria um novo registro de user no banco de dados
	 */
	public void save(User user) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(user);
		this.entityManager.getTransaction().commit();
	}

	/*
	 * Atualiza um registro de user no banco de dados
	 */
	public void update(User user) {
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(user);
		this.entityManager.getTransaction().commit();
	}

	/*
	 * Retorna todos usuários salvos no banco de dados
	 */
	@SuppressWarnings("unchecked")
	public List<User> listAll() {
		return this.entityManager.createQuery("SELECT u FROM User u ORDER BY u.name").getResultList();
	}

	/*
	 * Consulta um usuário cadastrado pelo ID
	 */
	public User getUser(Long id) {
		return this.entityManager.find(User.class, id);
	}

	/*
	 * Deletando um usuário pelo ID
	 */
	public void delete(Long id) {
		User user = this.getUser(id);

		this.entityManager.getTransaction().begin();
		this.entityManager.remove(user);
		this.entityManager.getTransaction().commit();
	}
}
