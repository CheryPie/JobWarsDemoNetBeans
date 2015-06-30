package model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * The persistent class for the JOB_SEEKER_SKILL_REL database table.
 *
 */
@Entity
@Table(name = "JOB_SEEKER_SKILL_REL")
@XmlRootElement
@NamedQuery(name = "JobSeekerSkillRel.findAll", query = "SELECT j FROM JobSeekerSkillRel j")
public class JobSeekerSkillRel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Expose
    @Column(name = "JOB_SEEKER_SKILL_REL_ID")
    private Long jobSeekerSkillRelId;

    //bi-directional many-to-one association to JobSeeker
    @ManyToOne
    @JoinColumn(name = "JOB_SEEKER_ID")
    private JobSeeker jobSeeker;

    @ManyToOne
    @Expose
    @JoinColumn(name = "SKILL_ID")
    private Skill skill;

    public JobSeekerSkillRel() {
    }

    public JobSeekerSkillRel(JobSeeker seeker, Skill skill) {
        this.jobSeeker = seeker;
        this.skill = skill;
    }

    public Long getJobSeekerSkillRelId() {
        return this.jobSeekerSkillRelId;
    }

    public void setJobSeekerSkillRelId(Long jobSeekerSkillRelId) {
        this.jobSeekerSkillRelId = jobSeekerSkillRelId;
    }

    public JobSeeker getJobSeeker() {
        return this.jobSeeker;
    }

    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public Skill getSkill() {
        return this.skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

}
