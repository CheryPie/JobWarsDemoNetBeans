package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.JobPost;
import model.LoginUser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-30T21:49:07")
@StaticMetamodel(Company.class)
public class Company_ { 

    public static volatile ListAttribute<Company, JobPost> jobPosts;
    public static volatile SingularAttribute<Company, Long> companyId;
    public static volatile SingularAttribute<Company, String> website;
    public static volatile SingularAttribute<Company, String> name;
    public static volatile ListAttribute<Company, LoginUser> loginUsers;
    public static volatile SingularAttribute<Company, String> description;
    public static volatile SingularAttribute<Company, String> bulstat;
    public static volatile SingularAttribute<Company, String> email;

}