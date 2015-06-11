package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Company;

@Stateless
public class CompanyDAO {
	
	@PersistenceContext(unitName="JOBWARS")
	private EntityManager em;
	

	public void create(Company company){
		em.persist(company);
	}
	
	public Company find(Long id){
		return em.find(Company.class, id);
	}
        
        public void edit(Company company){
		em.merge(company);
	}
}
