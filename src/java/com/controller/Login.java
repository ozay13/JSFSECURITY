/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ozaytunctan13
 */
@ManagedBean
@SessionScoped
public class Login implements Serializable {

    private String username;
    private String password;
    @ManagedProperty(value = "#{redirectPage}")
    RedirectPage redirectPage;
    private boolean logeddIn = false;

    public boolean isLogeddIn() {
        return logeddIn;
    }

    public void setLogeddIn(boolean logeddIn) {
        this.logeddIn = logeddIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RedirectPage getRedirectPage() {
        return redirectPage;
    }

    public void setRedirectPage(RedirectPage redirectPage) {
        this.redirectPage = redirectPage;
    }

    public String doLogin() {
        List<Users> users = new ArrayList<>();
        users.add(new Users("ozay", "1234", Boolean.TRUE));
        users.add(new Users("alican", "12345", Boolean.TRUE));
        String error_msg = "";
        if (username.isEmpty() || password.isEmpty()) {
            if (username.isEmpty()) {
                error_msg += "Kullanıcı adı alanı";
            }
            if (password.isEmpty()) {
                error_msg += " Şifre alanı";
            }
            error_msg+=" boş geçilemez!!!";
            FacesMessage msg = new FacesMessage(error_msg, "ERROR MSG");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return redirectPage.toLogin();
        } else {
            for (Users user : users) {
                //succes username ve password
                if (user.getUsername().equals(username)
                        && user.getPassword().equals(password)) {
                    logeddIn = true;
                    //Yetki verildi
                    return redirectPage.redirectToWelcome();
                }
            }
        }
        //Erorr
        FacesMessage msg = new FacesMessage("Giriş Hatalı \n Lütfen bilgileri doğru giriniz. !", "ERROR MSG");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // To login Page
        return redirectPage.toLogin();
    }

    public String doLogout() {
        //Kullanıcı oturumu kapattı
        logeddIn = false;
        //Kullanıcı bir mesaj vermek için 
        FacesMessage msg = new FacesMessage("Logout Succes", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return redirectPage.toLogin();
    }

}
