package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Skill;

@Stateless
public class SkillDAO {

    @PersistenceContext(unitName = "JOBWARS")
    private EntityManager em;

    public void create(Skill skill) {
        em.persist(skill);
    }

    public List<Skill> findByJobSeeker(Long jobSeekerId) {
        String txtQuery = "select s from Skill s where s.skillId in "
                + " ( select r.skill.skillId from JobSeekerSkillRel r where r.jobSeeker.jobSeekerId=:jobSeekerId)";
        TypedQuery<Skill> query = em.createQuery(txtQuery, Skill.class);
        query.setParameter("jobSeekerId", jobSeekerId);
        return query.getResultList();
    }

    public List<Skill> findAll() {
        String txtQuery = "select s from Skill s";
        TypedQuery<Skill> query = em.createQuery(txtQuery, Skill.class);
        return query.getResultList();
    }

    public Skill find(Long id) {
        return em.find(Skill.class, id);
    }

    public List<Skill> findByName(String filter) {
        String txtQuery = "select s from Skill s where LOWER(s.name) like LOWER(:name)";
        TypedQuery<Skill> query = em.createQuery(txtQuery, Skill.class);
        query.setParameter("name", filter.toLowerCase() + "%");
        return query.setFirstResult(0).setMaxResults(5).getResultList();
    }

    public Skill findSingleByName(String name) {
        try {
            String txtQuery = "select s from Skill s where LOWER(s.name)=:name";
            TypedQuery<Skill> query = em.createQuery(txtQuery, Skill.class);
            query.setParameter("name", name.toLowerCase());
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
