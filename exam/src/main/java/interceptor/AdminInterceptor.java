package interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminInterceptor extends HandlerInterceptorAdapter {
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle
     * (javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        if (adminUrls != null && adminUrls.size() > 0) {
            String reqUrl = request.getRequestURI();
            for (String url : adminUrls) {
                if (reqUrl.contains(url)) {
                    if (request.getSession().getAttribute("AdminInfo") == null) {
                        request.getRequestDispatcher("/adminLogin.action")
                                .forward(request, response);
                        return false;
                    }
                }
            }
        }
        return super.preHandle(request, response, handler);
    }

    private List<String> adminUrls;

    public List<String> getAdminUrls() {
        return adminUrls;
    }

    public void setAdminUrls(List<String> adminUrls) {
        this.adminUrls = adminUrls;
    }
}
