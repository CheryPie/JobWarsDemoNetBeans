package model;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

/**
 * The persistent class for the JOB_SEEKER_POST database table.
 *
 */
@Entity
@Table(name = "JOB_SEEKER_POST")
@NamedQuery(name = "JobSeekerPost.findAll", query = "SELECT j FROM JobSeekerPost j")
public class JobSeekerPost implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "JOB_SEEKER_POST_ID")
    @Expose
    private Long jobSeekerPostId;

    //bi-directional many-to-one association to JobPost
    @Expose
    @ManyToOne
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
