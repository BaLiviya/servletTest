import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/get")
public class ServletDownload extends HttpServlet {
    private static final int BYTES_DOWNLOAD = 1024;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setHeader("Content-Disposition", "attachment;filename=testing.txt");
        ServletContext ctx = getServletContext();
        InputStream is = ctx.getResourceAsStream("/testing.txt");
        int read = 0;
        byte[] bytes = new byte[BYTES_DOWNLOAD];
        OutputStream os = resp.getOutputStream();
        while ((read = is.read(bytes)) != -1) {
            os.write(bytes, 0, read);
        }
        os.flush();
        os.close();
    }
}
