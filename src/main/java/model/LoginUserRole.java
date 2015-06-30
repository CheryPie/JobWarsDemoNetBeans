package model;

import com.google.gson.annotations.Expose;
import java.io.Serializable;

import javax.persistence.*;


import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonBackReference;


/**
 * The persistent class for the LOGIN_USER_ROLE database table.
 * 
 */
@Entity
@Table(name="LOGIN_USER_ROLE")
@XmlRootElement
@NamedQuery(name="LoginUserRole.findAll", query="SELECT l FROM LoginUserRole l")
public class LoginUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="LOGIN_USER_ROLE_ID")
	@Expose
	private Long loginUserRoleId;

	@Expose
	private String name;

	//bi-directional many-to-one association to LoginUser
	@OneToMany(mappedBy="role")
	private List<LoginUser> loginUsers;

	public LoginUserRole() {
	}

	public Long getLoginUserRoleId() {
		return this.loginUserRoleId;
	}

	public void setLoginUserRoleId(Long loginUserRoleId) {
		this.loginUserRoleId = loginUserRoleId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<LoginUser> getLoginUsers() {
		return this.loginUsers;
	}

	public void setLoginUsers(List<LoginUser> loginUsers) {
		this.loginUsers = loginUsers;
	}

}