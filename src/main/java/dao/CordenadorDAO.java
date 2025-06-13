package dao;
import model.Coordenador;
import model.Denuncia;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.eclipse.persistence.jpa.JpaHelper.getEntityManager;

public class CordenadorDAO extends DAO{


    public void save(Coordenador coordenador) throws PersistenciaDacException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(coordenador);
            transaction.commit();
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar o Coordenador.", persistenceException);
        } finally {
            em.close();
        }
    }

    public Coordenador update(Coordenador coordenador) throws PersistenciaDacException {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Coordenador coordenadorEditado = coordenador;
        try {
            coordenadorEditado = entityManager.merge(coordenador);
            transaction.commit();
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar a coordenador.", persistenceException);
        } finally {
            entityManager.close();
        }
        return coordenadorEditado;
    }

    public void delete(Coordenador coordenador) throws PersistenciaDacException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            coordenador = em.find(Coordenador.class, coordenador.getEmail());
            em.remove(coordenador);
            transaction.commit();
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover o Coordenador.", persistenceException);
        } finally {
            em.close();
        }
    }

    public Coordenador getByID(String coordenadorEmail) throws PersistenciaDacException {
        EntityManager entityManager = getEntityManager();
        Coordenador coordenador = null;
        try {
            coordenador = entityManager.find(Coordenador.class, coordenadorEmail);
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar o Coordenador com base no ID.", pe);
        } finally {
            entityManager.close();
        }

        return coordenador;
    }

    public List<Coordenador> getAll() throws PersistenciaDacException {
        System.out.println("Teste");
        EntityManager entityManager = getEntityManager();
        List<Coordenador> coordenadorList = null;
        try {

            TypedQuery<Coordenador> query = entityManager.createQuery("SELECT d FROM Denuncia d", Coordenador.class);
            coordenadorList = query.getResultList();

            System.out.println(coordenadorList);

        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar os coordenadores.", persistenceException);
        } finally {
            entityManager.close();
        }

        return coordenadorList;
    }
}
