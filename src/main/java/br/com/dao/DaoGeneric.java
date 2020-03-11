package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jpautil.JPAUtil;

public class DaoGeneric<E> {

	public void salvar(E entidade) { //apenas salva
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.merge(entidade); //persist ou merge para salvar
		
		entityTransaction.commit();
		
		entityManager.close();
	}
	
	public E merge(E entidade) { //salva e retorna o que foi inserido no banco
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		E retorno = entityManager.merge(entidade); //persist ou merge para salvar
		
		entityTransaction.commit();
		
		entityManager.close();
		
		return retorno;
	}
	
	public void delete(E entidade) { //apenas salva
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.remove(entidade); 
		
		entityTransaction.commit();
		
		entityManager.close();
	}
	
	public void deletePorId(E entidade) { //apenas salva
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		Object id =JPAUtil.getPrimaryKey(entidade);
		entityManager.createQuery("delete from " + entidade.getClass().getCanonicalName() + " where id = " + id).executeUpdate();
		
		entityTransaction.commit();
		
		entityManager.close();
	}
	
	public List<E> getListEntity(Class<E> entidade){
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		List<E> retorno = entityManager.createQuery("from " + entidade.getName()).getResultList();
		
		entityTransaction.commit();
		entityManager.close();
		
		return retorno;
	}
}
