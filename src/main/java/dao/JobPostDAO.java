package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Company;
import model.JobPost;
import model.JobPostSkillRel;
import model.Skill;

@Stateless
public class JobPostDAO {

    @PersistenceContext(unitName = "JOBWARS")
    private EntityManager em;

//	public JobPostDAO() {
//		this.em = Persistence.createEntityManagerFactory("jobwars")
//				.createEntityManager();
//	}
//    public void create(JobPost jobPost, List<Skill> skills) {
//        String idStr = em
//                .createNativeQuery("select job_post_seq.nextval from dual")
//                .getSingleResult().toString();
//        Long idLong = new Long(idStr.substring(1, idStr.length() - 1));
//        jobPost.setJobPostId(idLong);
//        em.persist(jobPost);
//        for (Skill skill : skills) {
//            JobPostSkillRel rel = new JobPostSkillRel();
//            rel.setJobPost(jobPost);
//            rel.setSkill(skill);
//            em.persist(rel);
//        }
//    }

    public void create(String companyId, String description, List<String> skills) {
        Company company = em.find(Company.class, new Long(companyId));
        JobPost post = new JobPost();
        String idStr = em
                .createNativeQuery("select job_post_seq.nextval from dual")
                .getSingleResult().toString();
        post.setJobPostId(new Long(idStr));
       // post.setCompany(company);
        post.setDescription(description);
        em.persist(post);
        if (skills != null) {
            for (String skill : skills) {
                em.createNativeQuery("Insert Into Job_Post_Skill_Rel(job_post_id,skill_id) values (" + idStr + "," + skill + ")").executeUpdate();
            }
        }
    }

    public void create(JobPost post, List<String> skills) {
        String idStr = em
                .createNativeQuery("select job_post_seq.nextval from dual")
                .getSingleResult().toString();
        post.setJobPostId(new Long(idStr));
        em.persist(post);
        if (skills != null) {
            for (String skill : skills) {
                em.createNativeQuery("Insert Into Job_Post_Skill_Rel(job_post_id,skill_id) values (" + idStr + "," + skill + ")").executeUpdate();
            }
        }
    }

    public List<JobPost> findByCompany(String companyId) {
        String txtQuery = "select p from JobPost p where p.company.companyId=:companyId";
        TypedQuery<JobPost> query = em.createQuery(txtQuery, JobPost.class);
        query.setParameter("companyId", new Long(companyId));
        return query.getResultList();
    }

    public List<JobPost> findByUser(String jobSeekerId) {
        String txtQuery = "select p from JobPost p where p.jobPostId in"
                + " (select distinct r.jobPost.jobPostId from JobPostSkillRel r "
                + " where r.skill.skillId in "
                + " (select s.skill.skillId from JobSeekerSkillRel s where s.jobSeeker.jobSeekerId=:jobSeekerId))";
        TypedQuery<JobPost> query = em.createQuery(txtQuery, JobPost.class);
        query.setParameter("jobSeekerId", new Long(jobSeekerId));
        return query.getResultList();
    }

    public JobPost find(Long id) {
        return em.find(JobPost.class, id);
    }
    
    public List<JobPost> findAll(){
        return em.createNamedQuery("JobPost.findAll").getResultList();
    }
}
