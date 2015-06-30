package model;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import javax.persistence.FetchType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The persistent class for the JOB_POST database table.
 *
 */
@Entity
@Table(name = "JOB_POST")
@NamedQuery(name = "JobPost.findAll", query = "SELECT j FROM JobPost j")
@XmlRootElement
public class JobPost implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Expose
    @Column(name = "JOB_POST_ID")
    private Long jobPostId;

    @Expose
    private String description;

    //bi-directional many-to-one association to Company
    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @Expose
    @Column(name = "HEADER")
    private String header;

    @Expose
    @OneToMany(mappedBy = "jobPost", fetch = FetchType.EAGER)
    private List<JobPostSkillRel> jobPostSkillRels;

    @OneToMany(mappedBy = "jobPost")
    private List<JobSeekerPost> jobSeekerPosts;

    public JobPost() {
    }

    public Long getJobPostId() {
        return this.jobPostId;
    }

    public void setJobPostId(Long jobPostId) {
        this.jobPostId = jobPostId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @XmlTransient
    public List<JobPostSkillRel> getJobPostSkillRels() {
        return this.jobPostSkillRels;
    }

    public void setJobPostSkillRels(List<JobPostSkillRel> jobPostSkillRels) {
        this.jobPostSkillRels = jobPostSkillRels;
    }

    @XmlTransient
    public List<JobSeekerPost> getJobSeekerPosts() {
        return this.jobSeekerPosts;
    }

    public void setJobSeekerPosts(List<JobSeekerPost> jobSeekerPosts) {
        this.jobSeekerPosts = jobSeekerPosts;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

}
