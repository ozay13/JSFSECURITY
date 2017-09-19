/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ozaytunctan13
 */

@ManagedBean
@SessionScoped
public class RedirectPage implements Serializable{
   private static final long serialVersionUID=123544448448L;
   
    public String redirectToLogin() {
        return "/login.xhtml?faces-redirect=true";
    }

    public String redirectToWelcome() {
        return "/secured/securedPage.xhtml?faces-redirect=true";
    }

    /**
     * Guvenli sayfaya erişmek için 
     *
     * @return
     */
    public String toWelcome() {
        return "/secured/securedPage.xhtml";
    }

    public String toIndex() {
        return "/index.xhtml";
    }

    public String redirectToIndex() {
        return "/index.xhtml?faces-redirect=true";
    }

    public String toLogin() {
        return "/login.xhtml";
    }
}
