package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.JobSeekerPost;

@Stateless
public class JobSeekerPostDAO {

    @PersistenceContext(unitName = "JOBWARS")
    private EntityManager em;

    public void apply(JobSeekerPost rel) {
        em.persist(rel);
    }
}
