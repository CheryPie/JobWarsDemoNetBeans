package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.JobSeekerPost;
import model.JobSeekerSkillRel;
import model.LoginUser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-30T22:56:51")
@StaticMetamodel(JobSeeker.class)
public class JobSeeker_ { 

    public static volatile ListAttribute<JobSeeker, JobSeekerSkillRel> jobSeekerSkillRels;
    public static volatile ListAttribute<JobSeeker, LoginUser> loginUsers;
    public static volatile SingularAttribute<JobSeeker, String> fullName;
    public static volatile SingularAttribute<JobSeeker, String> position;
    public static volatile ListAttribute<JobSeeker, JobSeekerPost> jobSeekerPosts;
    public static volatile SingularAttribute<JobSeeker, Long> jobSeekerId;

}