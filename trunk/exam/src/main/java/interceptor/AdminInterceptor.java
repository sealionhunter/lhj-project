package interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminInterceptor extends HandlerInterceptorAdapter {
    private static List<String> adminAuthViewNameList = new ArrayList<String>();

    static {
        adminAuthViewNameList.add("signUpPersonInfoSearch");
        adminAuthViewNameList.add("verify");
        adminAuthViewNameList.add("signUpDetailSearch");
        adminAuthViewNameList.add("adminInit");
        adminAuthViewNameList.add("adminIndex");
        adminAuthViewNameList.add("userPasswordReset");
    }

    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler, ModelAndView mv)
            throws Exception {
        if (adminAuthViewNameList.contains(mv.getViewName())) {
            if (request.getSession().getAttribute("AdminInfo") == null) {
                request.getRequestDispatcher("/adminLogin.action").forward(
                        request, response);
            }
        }
    }
}
