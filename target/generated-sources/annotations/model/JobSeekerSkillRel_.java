package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.JobSeeker;
import model.Skill;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-07-01T08:07:13")
@StaticMetamodel(JobSeekerSkillRel.class)
public class JobSeekerSkillRel_ { 

    public static volatile SingularAttribute<JobSeekerSkillRel, Long> jobSeekerSkillRelId;
    public static volatile SingularAttribute<JobSeekerSkillRel, Skill> skill;
    public static volatile SingularAttribute<JobSeekerSkillRel, JobSeeker> jobSeeker;

}