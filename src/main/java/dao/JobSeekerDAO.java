package dao;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.JobSeeker;
import model.JobSeekerSkillRel;
import model.Skill;

@Stateless
public class JobSeekerDAO {

    @PersistenceContext(unitName = "JOBWARS")
    private EntityManager em;

    public void create(JobSeeker jobSeeker) {
        em.persist(jobSeeker);
    }

    public JobSeeker find(Long id) {
        return em.find(JobSeeker.class, id);
    }

    public List<JobSeeker> findByPost(String postId) {
        try {
            String txtQuery = "SELECT * from JOB_SEEKER where JOB_SEEKER_ID IN (SELECT JOB_SEEKER_ID FROM JOB_SEEKER_POST WHERE JOB_POST_ID=?1)";
            Query query = em.createNativeQuery(txtQuery, JobSeeker.class);
            query.setParameter(1, new Long(postId));
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @EJB
    private SkillDAO skillDAO;

    public void edit(JobSeeker seeker, String name) {
        if (name != null && !name.equals("")) {
            Skill skill = skillDAO.findSingleByName(name);
            if (skill == null) {
                String idStr = em.createNativeQuery("select SKILL_SEQ.NEXTVAL FROM DUAL").getSingleResult().toString();
                skill = new Skill(new Long(idStr), name);
                em.persist(skill);
            }
            if (!hasSkill(skill, seeker.getJobSeekerSkillRels())) {
                em.persist(new JobSeekerSkillRel(seeker, skill));
            }
        }
        em.merge(seeker);
    }

    private boolean hasSkill(Skill skill, List<JobSeekerSkillRel> rels) {
        for (JobSeekerSkillRel rel : rels) {
            if (rel.getSkill().getSkillId().equals(skill.getSkillId())) {
                return true;
            }
        }
        return false;
    }
}
