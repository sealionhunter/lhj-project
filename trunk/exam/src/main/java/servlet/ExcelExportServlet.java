package servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExcelExportServlet extends HttpServlet {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 851304338551690269L;

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
        String id = req.getParameter("id");
        String fileName = (String) req.getSession().getAttribute(id);
        if (fileName == null) {
            return;
        }
        File file = new File(fileName);
        resp.addHeader("Content-Disposition", "attachment;filename="
                + new String(file.getName().getBytes()));
        resp.addHeader("Content-Length", "" + file.length());
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(
                file));

        byte[] buffer = new byte[8192];
        int length = 0;
        while ((length = in.read(buffer, 0, 8192)) != -1) {
            out.write(buffer, 0, length);
        }
        out.flush();
        in.close();
        file.delete();
    }

}
