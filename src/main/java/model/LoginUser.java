package model;

import com.google.gson.annotations.Expose;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the LOGIN_USER database table.
 * 
 */
@Entity
@Table(name="LOGIN_USER")
@XmlRootElement
@NamedQuery(name="LoginUser.findAll", query="SELECT l FROM LoginUser l")
public class LoginUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @Expose
	@Column(name="LOGIN_USER_ID")
	private Long loginUserId;

        @Expose
	private String password;

	@Column(name="USER_NAME")
	private String userName;

	//bi-directional many-to-one association to Company
	@ManyToOne
        @Expose
	@JoinColumn(name="COMPANY_ID")
	private Company company;

	//bi-directional many-to-one association to JobSeeker
	@ManyToOne
        @Expose
	@JoinColumn(name="JOB_SEEKER_ID")
	private JobSeeker jobSeeker;

	//bi-directional many-to-one association to LoginUserRole
	@ManyToOne
        @Expose
	@JoinColumn(name="LOGIN_USER_ROLE_ID")
	private LoginUserRole role;

	public LoginUser() {
	}

	public Long getLoginUserId() {
		return this.loginUserId;
	}

	public void setLoginUserId(Long loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public JobSeeker getJobSeeker() {
		return this.jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	public LoginUserRole getRole() {
		return this.role;
	}

	public void setRole(LoginUserRole loginUserRole) {
		this.role = loginUserRole;
	}

}