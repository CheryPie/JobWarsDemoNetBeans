package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.JobPost;
import model.JobSeeker;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-30T22:56:51")
@StaticMetamodel(JobSeekerPost.class)
public class JobSeekerPost_ { 

    public static volatile SingularAttribute<JobSeekerPost, JobPost> jobPost;
    public static volatile SingularAttribute<JobSeekerPost, Long> jobSeekerPostId;
    public static volatile SingularAttribute<JobSeekerPost, JobSeeker> jobSeeker;

}