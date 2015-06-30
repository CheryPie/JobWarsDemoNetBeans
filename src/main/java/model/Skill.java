package model;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonBackReference;

/**
 * The persistent class for the SKILL database table.
 *
 */
@Entity
@XmlRootElement
@NamedQuery(name = "Skill.findAll", query = "SELECT s FROM Skill s")
public class Skill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SKILL_ID")
    @Expose
    private Long skillId;

    @Expose
    private String name;

    //bi-directional many-to-one association to JobPostSkillRel
    @OneToMany(mappedBy = "skill")
    private List<JobPostSkillRel> jobPostSkillRels;

    //bi-directional many-to-one association to JobSeekerSkillRel
    @OneToMany(mappedBy = "skill")
    private List<JobSeekerSkillRel> jobSeekerSkillRels;

    public Skill() {
    }

    public Skill(Long id,String name) {
        this.skillId = id;
        this.name = name;
    }

    public Long getSkillId() {
        return this.skillId;
    }

    public Skill(String name) {
        this.name = name;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JobPostSkillRel> getJobPostSkillRels() {
        return this.jobPostSkillRels;
    }

    public void setJobPostSkillRels(List<JobPostSkillRel> jobPostSkillRels) {
        this.jobPostSkillRels = jobPostSkillRels;
    }

    public JobPostSkillRel addJobPostSkillRel(JobPostSkillRel jobPostSkillRel) {
        getJobPostSkillRels().add(jobPostSkillRel);
        jobPostSkillRel.setSkill(this);

        return jobPostSkillRel;
    }

    public JobPostSkillRel removeJobPostSkillRel(JobPostSkillRel jobPostSkillRel) {
        getJobPostSkillRels().remove(jobPostSkillRel);
        jobPostSkillRel.setSkill(null);

        return jobPostSkillRel;
    }

    public List<JobSeekerSkillRel> getJobSeekerSkillRels() {
        return this.jobSeekerSkillRels;
    }

    public void setJobSeekerSkillRels(List<JobSeekerSkillRel> jobSeekerSkillRels) {
        this.jobSeekerSkillRels = jobSeekerSkillRels;
    }

    public JobSeekerSkillRel addJobSeekerSkillRel(JobSeekerSkillRel jobSeekerSkillRel) {
        getJobSeekerSkillRels().add(jobSeekerSkillRel);
        jobSeekerSkillRel.setSkill(this);

        return jobSeekerSkillRel;
    }

    public JobSeekerSkillRel removeJobSeekerSkillRel(JobSeekerSkillRel jobSeekerSkillRel) {
        getJobSeekerSkillRels().remove(jobSeekerSkillRel);
        jobSeekerSkillRel.setSkill(null);

        return jobSeekerSkillRel;
    }

}
