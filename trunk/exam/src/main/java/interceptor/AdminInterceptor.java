package interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

public class AdminInterceptor extends HandlerInterceptorAdapter {
    private static List<String> adminAuthViewNameList = new ArrayList<String>();

    static {
        adminAuthViewNameList.add("signUpPersonInfoSearch");
        adminAuthViewNameList.add("verify");
    }

    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler, ModelAndView mv)
            throws Exception {
        if (adminAuthViewNameList.contains(mv.getViewName())) {
            // TODO login page is needed.
            System.out.println("todo login!");
            mv.setView(new RedirectView("view/Regflow.html"));
        }
    }

    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        
        return true;
    }
}
