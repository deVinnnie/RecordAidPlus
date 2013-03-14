/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.khleuven.recordaid.servlet;

import be.khleuven.eindwerk.ui.RecordAidDomainFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Maxime
 */
public class Handler_Logout extends Handler{

    public Handler_Logout(RecordAidDomainFacade domainFacade) {
        super(domainFacade);
    }

    
    
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        
        super.destination="home.jsp";
    }
    
}