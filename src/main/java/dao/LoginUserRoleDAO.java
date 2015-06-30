package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.LoginUserRole;

@Stateless
public class LoginUserRoleDAO {

	@PersistenceContext(unitName="JOBWARS")
	private EntityManager em;

//	public LoginUserRoleDAO() {
//		this.em = Persistence.createEntityManagerFactory("jobwars")
//				.createEntityManager();
//	}
	
	public LoginUserRole findCompanyRole(){
		return em.find(LoginUserRole.class,new Long(2));
	}
	
	public LoginUserRole findJobSeekerRole(){
		return em.find(LoginUserRole.class,new Long(1));
	}
}
