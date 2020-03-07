package top.wsuo.web;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * 文件下载 servlet
 */
@WebServlet(urlPatterns = "/downloadServlet")
public class DownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、文件存放路径
        String path = "D:/fileUpload/";
        // 2、需要下载的文件名
        String fileName = "test.jpg";
        File file = new File(path + fileName);
        response.reset();
        response.setCharacterEncoding("UTF-8");
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        InputStream input = new FileInputStream(file);
        ServletOutputStream out = response.getOutputStream();
        byte[] buff = new byte[1024];
        int len;
        while ((len = input.read(buff)) != -1) {
            out.write(buff, 0, len);
            out.flush();
        }
        input.close();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
