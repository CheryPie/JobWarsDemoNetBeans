package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.JobPostSkillRel;
import model.JobSeekerSkillRel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-30T21:49:07")
@StaticMetamodel(Skill.class)
public class Skill_ { 

    public static volatile SingularAttribute<Skill, Long> skillId;
    public static volatile ListAttribute<Skill, JobPostSkillRel> jobPostSkillRels;
    public static volatile ListAttribute<Skill, JobSeekerSkillRel> jobSeekerSkillRels;
    public static volatile SingularAttribute<Skill, String> name;

}