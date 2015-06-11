package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.JobSeeker;
import model.JobSeekerSkillRel;
import model.Skill;

@Stateless
public class JobSeekerSkillDAO {

	@PersistenceContext(unitName="JOBWARS")
	private EntityManager em;
	
//	public JobSeekerSkillDAO(){
//		this.em= Persistence.createEntityManagerFactory("jobwars").createEntityManager();
//	}
	
	public void create(JobSeekerSkillRel rel){
		//em.getTransaction().begin();
		em.persist(rel);
		//em.getTransaction().commit();
	}
	
	public void create(Long seekerId,Long skillId){
		//em.getTransaction().begin();
		JobSeeker seeker = em.find(JobSeeker.class,new Long(seekerId));
		Skill skill = em.find(Skill.class,new Long(skillId));
		JobSeekerSkillRel rel = new JobSeekerSkillRel();
		rel.setSkill(skill);
		rel.setJobSeeker(seeker);
		em.persist(rel);
//		em.flush();
//		em.getTransaction().commit();
	}
}
