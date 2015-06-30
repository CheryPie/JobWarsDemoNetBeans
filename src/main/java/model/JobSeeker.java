package model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * The persistent class for the JOB_SEEKER database table.
 *
 */
@Entity
@Table(name = "JOB_SEEKER")
@XmlRootElement
@NamedQuery(name = "JobSeeker.findAll", query = "SELECT j FROM JobSeeker j")
public class JobSeeker implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Expose
    @Column(name = "JOB_SEEKER_ID")
    private Long jobSeekerId;

    @Column(name = "FULL_NAME")
    @Expose
    private String fullName;

    @Column(name = "PREFERED_POSITION")
    @Expose
    private String position;

    @Expose
    @OneToMany(mappedBy = "jobSeeker", fetch = FetchType.EAGER)
    private List<JobSeekerPost> jobSeekerPosts;

    @Expose
    @OneToMany(mappedBy = "jobSeeker", fetch = FetchType.EAGER)
    private List<JobSeekerSkillRel> jobSeekerSkillRels;

    //bi-directional many-to-one association to LoginUser
    @OneToMany(mappedBy = "jobSeeker")
    private List<LoginUser> loginUsers;

    public JobSeeker() {
    }

    public Long getJobSeekerId() {
        return this.jobSeekerId;
    }

    public void setJobSeekerId(Long jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    @XmlTransient
    public List<JobSeekerPost> getJobSeekerPosts() {
        return this.jobSeekerPosts;
    }

    public void setJobSeekerPosts(List<JobSeekerPost> jobSeekerPosts) {
        this.jobSeekerPosts = jobSeekerPosts;
    }

    @XmlTransient
    public List<JobSeekerSkillRel> getJobSeekerSkillRels() {
        return this.jobSeekerSkillRels;
    }

    public void setJobSeekerSkillRels(List<JobSeekerSkillRel> jobSeekerSkillRels) {
        this.jobSeekerSkillRels = jobSeekerSkillRels;
    }

    @XmlTransient
    public List<LoginUser> getLoginUsers() {
        return this.loginUsers;
    }

    public void setLoginUsers(List<LoginUser> loginUsers) {
        this.loginUsers = loginUsers;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
