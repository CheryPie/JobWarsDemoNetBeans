/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dao.LoginUserDAO;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import model.LoginUser;

/**
 *
 * @author Presko
 */
@SessionScoped
public class LoginSessionBean implements Serializable {

    @EJB
    private LoginUserDAO loginDAO;

    private static final long serialVersionUID = 1L;

    private LoginUser currentLoginUser = new LoginUser();

    public LoginUser getCurrentLoginUser() {
        return currentLoginUser;
    }

    public void setCurrentLoginUser(LoginUser currentLoginUser) {
        this.currentLoginUser = currentLoginUser;
    }

    public void reloadUser() {
        String userId = this.currentLoginUser.getLoginUserId().toString();
        this.currentLoginUser = loginDAO.find(new Long(userId));
    }
}
