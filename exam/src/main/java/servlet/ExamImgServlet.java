package servlet;

import java.io.BufferedOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExamImgServlet extends HttpServlet {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7378740499461036885L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        download(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        download(req, resp);
    }

    private void download(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/octet-stream");
        BufferedOutputStream out = new BufferedOutputStream(
                resp.getOutputStream());
        String userId = req.getParameter("userId");
        byte[] photoData = (byte[]) req.getSession().getAttribute(
                "photoData" + userId);
        out.write(photoData);
        out.flush();
    }

}
