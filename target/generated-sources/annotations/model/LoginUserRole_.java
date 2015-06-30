package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.LoginUser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-07-01T02:23:15")
@StaticMetamodel(LoginUserRole.class)
public class LoginUserRole_ { 

    public static volatile SingularAttribute<LoginUserRole, String> name;
    public static volatile ListAttribute<LoginUserRole, LoginUser> loginUsers;
    public static volatile SingularAttribute<LoginUserRole, Long> loginUserRoleId;

}