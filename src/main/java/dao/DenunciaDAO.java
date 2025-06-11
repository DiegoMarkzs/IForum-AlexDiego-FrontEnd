package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import model.Denuncia;

public class DenunciaDAO extends DAO {

	public void save(Denuncia denuncia) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(denuncia);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar a denuncia.", pe);
		} finally {
			em.close();
		}
	}

	public Denuncia update(Denuncia denuncia) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Denuncia resultado = denuncia;
		try {
			resultado = em.merge(denuncia);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar a denuncia.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Denuncia denuncia) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			denuncia = em.find(Denuncia.class, denuncia.GetID());
			em.remove(denuncia);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover a denuncia.", pe);
		} finally {
			em.close();
		}
	}

	public Denuncia getByID(int denunciaID) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Denuncia resultado = null;
		try {
			resultado = em.find(Denuncia.class, denunciaID);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar a denuncia com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Denuncia> getAll() throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Denuncia> resultado = null;
		try {
			TypedQuery<Denuncia> query = em.createQuery("SELECT d FROM Denuncia d", Denuncia.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar as denuncias.", pe);
		} finally {
			em.close();
		}
		
		return resultado;
	}
}
