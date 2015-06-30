package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.JobPost;
import model.Skill;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-30T22:56:51")
@StaticMetamodel(JobPostSkillRel.class)
public class JobPostSkillRel_ { 

    public static volatile SingularAttribute<JobPostSkillRel, JobPost> jobPost;
    public static volatile SingularAttribute<JobPostSkillRel, Long> jobPostSkillRelId;
    public static volatile SingularAttribute<JobPostSkillRel, Skill> skill;

}