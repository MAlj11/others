package xatu.shixi;

import Utils.JdbcHelper;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/list")
public class PhoneListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JdbcHelper jh = new JdbcHelper();
        String SQL = "select * from things";
        List<Map<String,Object>> phoneList = jh.query(SQL);
        jh.destory();

        ServletContext Application = request.getServletContext();
        Application.setAttribute("things",phoneList);

         request.getRequestDispatcher( "/list.vm" ).forward(request,response);


         }
}
