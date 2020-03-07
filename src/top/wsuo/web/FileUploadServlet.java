package top.wsuo.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.jsp.HttpJspPage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "FileUploadServlet", urlPatterns = "/fileUpload")
@MultipartConfig(location = "D:\\", fileSizeThreshold = 1024)
public class FileUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = this.getServletContext().getRealPath("/");//返回web应用程序文档根目录
        String username = request.getParameter("username");
        Part part = request.getPart("filename");
        String message = "";

//        ServletConfig config = getServletConfig();
//        config.getInitParameter();
//        ServletInputStream input = request.getInputStream();
//        System.out.println(input);

        if (part.getSize() > 1024 * 1024) {
            part.delete();
            message = "文件大小不能超过1MB";
        } else {
            path += "\\user\\" + username;
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            String h = part.getHeader("content-disposition");
            String fname = getFileName(h);
            part.write(path + "\\" + fname);
            message = "文件上传成功";
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
//        response.setContentType("image/gif");
//        response.setHeader("", "");
//
//        ServletOutputStream os = response.getOutputStream();
//        InputStream is = this.getServletContext().getResourceAsStream("");
//        byte[] bytes = new byte[1024];
//        int b = 0;
//        while ((b = is.read(bytes)) != -1) {
//            os.write(bytes, 0, b);
//        }
//        os.flush();
//        is.close();

    }

    /**
     * 根据请求头解析出文件名
     * 请求头的格式：火狐和google浏览器下：form-data; name="file"; filename="snmp4j--api.zip"
     * IE浏览器下：form-data; name="file"; filename="D:\snmp4j--api.zip"
     * @param header 请求头
     * @return 文件名
     */
    public String getFileName(String header) {
        /**
         * String[] tempArr1 = header.split(";");
         * 代码执行完之后，在不同的浏览器下，tempArr1数组里面的内容稍有区别:
         * 火狐或者google浏览器下：tempArr1={form-data,name="file",filename="snmp4j--api.zip"}
         * IE浏览器下：tempArr1={form-data,name="file",filename="D:\snmp4j--api.zip"}
         */
        String[] tempArr1 = header.split(";");
        /**
         *火狐或者google浏览器下：tempArr2={filename,"snmp4j--api.zip"}
         *IE浏览器下：tempArr2={filename,"D:\snmp4j--api.zip"}
         */
        String[] tempArr2 = tempArr1[2].split("=");
        //获取文件名，兼容各种浏览器的写法
        String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\")+1).replaceAll("\"", "");
        return fileName;
    }

}
