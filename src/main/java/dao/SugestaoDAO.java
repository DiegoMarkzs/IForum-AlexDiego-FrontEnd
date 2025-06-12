package dao;

import java.util.List;

import javax.persistence.*;

import model.Sugestao;

public class SugestaoDAO extends DAO {

    public void save(Sugestao sugestao) throws PersistenciaDacException {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(sugestao);
            tx.commit();
        } catch (PersistenceException e) {
            if (tx.isActive()) tx.rollback();
            throw new PersistenciaDacException("Erro ao salvar sugestão", e);
        } finally {
            em.close();
        }
    }

    public List<Sugestao> getAll() throws PersistenciaDacException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Sugestao> query = em.createQuery("SELECT s FROM Sugestao s", Sugestao.class);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaDacException("Erro ao buscar sugestões", e);
        } finally {
            em.close();
        }
    }

    public List<Sugestao> getByUsuario(String email) throws PersistenciaDacException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Sugestao> query = em.createQuery(
                "SELECT s FROM Sugestao s WHERE s.usuario.email = :email", Sugestao.class);
            query.setParameter("email", email);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaDacException("Erro ao buscar sugestões do usuário", e);
        } finally {
            em.close();
        }
    }

    public Sugestao getById(Long id) throws PersistenciaDacException {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sugestao.class, id);
        } catch (PersistenceException e) {
            throw new PersistenciaDacException("Erro ao buscar sugestão por ID", e);
        } finally {
            em.close();
        }
    }

    public void update(Sugestao sugestao) throws PersistenciaDacException {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(sugestao);
            tx.commit();
        } catch (PersistenceException e) {
            if (tx.isActive()) tx.rollback();
            throw new PersistenciaDacException("Erro ao atualizar sugestão", e);
        } finally {
            em.close();
        }
    }
}
