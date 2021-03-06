package davimuri.app.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  http://www.technicaladvices.com/2012/07/08/the-effective-java-logout-servlet-code/
 *
 * The servlet must be put into <security-constraint> <web-resource-collection> in web.xml, if not, request.getUserPrincipal() will be null!
 *
 * Created by davidmurillomatallana on 27/11/16.
 */
public class LogoutServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(LogoutServlet.class);

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        //********************** print JAAS info **********************//
        boolean printJaasInfo = false;
        if(printJaasInfo){
            try{
                //logger.info("LogoutServlet>request.getServletPath():"+request.getServletPath());//faces
                //logger.info("LogoutServlet>request.getClass().getName():"+r1.getClass().getName());//org.apache.catalina.connector.RequestFacade
                //logger.info("LogoutServlet>isAdministrator:"+request.isUserInRole(KnownJaasRoles.ADMINISTRATOR));//logged in with ybxiang:true
                //logger.info("LogoutServlet>remoteUser:"+request.getRemoteUser());//ybxiang
                logger.info("LogoutServlet>userPrincipalName:"+(request.getUserPrincipal()==null?"null":request.getUserPrincipal().getName()));//ybxiang
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        //********************** log out(clean something) **********************//
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", new java.util.Date().toString());//http://www.coderanch.com/t/541412/Servlets/java/Logout-servlet-button
        //response.setHeader("Expires", "0")//http://www.coderanch.com/t/541412/Servlets/java/Logout-servlet-button
        //
        if(request.getSession(false)!=null){
            request.getSession(false).invalidate();//remove session.
        }
        if(request.getSession()!=null){
            request.getSession().invalidate();//remove session.
        }

        request.logout();//JAAS log out (from servlet specification)! It is a MUST!

        //********************** print JAAS info again **********************//
        if(printJaasInfo){
            try{
                //logger.info("LogoutServlet>request.getServletPath():"+request.getServletPath());//faces
                //logger.info("LogoutServlet>request.getClass().getName():"+r1.getClass().getName());//org.apache.catalina.connector.RequestFacade
                //logger.info("LogoutServlet>isAdministrator:"+request.isUserInRole(KnownJaasRoles.ADMINISTRATOR));//logged in with ybxiang:true
                //logger.info("LogoutServlet>remoteUser:"+request.getRemoteUser());//ybxiang
                logger.info("LogoutServlet>userPrincipalName:"+(request.getUserPrincipal()==null?"null":request.getUserPrincipal().getName()));//ybxiang
            }catch(Exception e){
                e.printStackTrace();
            }
        }


        //********************** redirect **********************//
        /**
         * Here, if we redirect response to a secured page (example:request.getContextPath()+"/faces/console.xhtml"),
         * then
         * (a)<auth-method>BASIC</auth-method> will redirect secured page to login page and login automatically with username and password that are cached in web browser.
         * (b)<auth-method>FORM</auth-method> will redirect secured page to login page too, but will NOT login automatically with username and password that are cached in web browser.
         *
         * Here, if we redirect response to a non-secured page, then the non-secured page is displayed (normal case).
         */
        response.sendRedirect(request.getContextPath());
    }
}
