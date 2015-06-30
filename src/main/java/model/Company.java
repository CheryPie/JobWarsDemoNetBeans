package model;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import javax.persistence.FetchType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Expose
    @Column(name = "COMPANY_ID")
    private Long companyId;
    
    @Expose
    @Column(name = "NAME")
    private String name;

    @Expose
    @Column(name = "EMAIL")
    private String email;

    @Expose
    @Column(name = "WEBSITE")
    private String website;

    @Expose
    @Column(name = "DESCRIPTION")
    private String description;

    @Expose
    @Column(name = "BULSTAT")
    private String bulstat;

    @Expose
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private List<JobPost> jobPosts;

    @OneToMany(mappedBy = "company")
    private List<LoginUser> loginUsers;

    public Company() {
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<JobPost> getJobPosts() {
        return this.jobPosts;
    }

    public void setJobPosts(List<JobPost> jobPosts) {
        this.jobPosts = jobPosts;
    }

    @XmlTransient
    public List<LoginUser> getLoginUsers() {
        return this.loginUsers;
    }

    public void setLoginUsers(List<LoginUser> loginUsers) {
        this.loginUsers = loginUsers;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBulstat() {
        return bulstat;
    }

    public void setBulstat(String bulstat) {
        this.bulstat = bulstat;
    }

}
