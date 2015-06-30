package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Company;
import model.JobPostSkillRel;
import model.JobSeekerPost;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-30T21:49:07")
@StaticMetamodel(JobPost.class)
public class JobPost_ { 

    public static volatile ListAttribute<JobPost, JobPostSkillRel> jobPostSkillRels;
    public static volatile SingularAttribute<JobPost, Long> jobPostId;
    public static volatile SingularAttribute<JobPost, String> header;
    public static volatile SingularAttribute<JobPost, String> description;
    public static volatile SingularAttribute<JobPost, Company> company;
    public static volatile ListAttribute<JobPost, JobSeekerPost> jobSeekerPosts;

}