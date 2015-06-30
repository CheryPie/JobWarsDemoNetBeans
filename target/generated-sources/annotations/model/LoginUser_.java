package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Company;
import model.JobSeeker;
import model.LoginUserRole;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-30T22:56:51")
@StaticMetamodel(LoginUser.class)
public class LoginUser_ { 

    public static volatile SingularAttribute<LoginUser, String> password;
    public static volatile SingularAttribute<LoginUser, LoginUserRole> role;
    public static volatile SingularAttribute<LoginUser, Long> loginUserId;
    public static volatile SingularAttribute<LoginUser, Company> company;
    public static volatile SingularAttribute<LoginUser, String> userName;
    public static volatile SingularAttribute<LoginUser, JobSeeker> jobSeeker;

}