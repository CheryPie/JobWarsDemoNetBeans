package model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.gson.annotations.Expose;
import java.io.Serializable;

import javax.persistence.*;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * The persistent class for the JOB_SEEKER_POST database table.
 *
 */
@Entity
@Table(name = "JOB_SEEKER_POST")
@XmlRootElement
@NamedQuery(name = "JobSeekerPost.findAll", query = "SELECT j FROM JobSeekerPost j")
public class JobSeekerPost implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Expose
    @Column(name = "JOB_SEEKER_POST_ID")
    private Long jobSeekerPostId;

    //bi-directional many-to-one association to JobPost
    @ManyToOne
    @Expose
    @JoinColumn(name = "JOB_POST_ID")
    private JobPost jobPost;

    //bi-directional many-to-one association to JobSeeker
    @ManyToOne
    @JoinColumn(name = "JOB_SEEKER_ID")
    private JobSeeker jobSeeker;

    public JobSeekerPost() {
    }

    public JobSeekerPost(JobSeeker jobSeeker, JobPost jobPost) {
        this.jobSeeker = jobSeeker;
        this.jobPost = jobPost;
    }

    public Long getJobSeekerPostId() {
        return this.jobSeekerPostId;
    }

    public void setJobSeekerPostId(Long jobSeekerPostId) {
        this.jobSeekerPostId = jobSeekerPostId;
    }

    public JobPost getJobPost() {
        return this.jobPost;
    }

    public void setJobPost(JobPost jobPost) {
        this.jobPost = jobPost;
    }

    public JobSeeker getJobSeeker() {
        return this.jobSeeker;
    }

    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

}
