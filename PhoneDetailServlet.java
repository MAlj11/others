package xatu.shixi;

import Utils.JdbcHelper;
import com.mysql.jdbc.JDBC42Helper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/detail")
public class PhoneDetailServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        //List<String> phoneList =  new ArrayList<>();
        String tid = request.getParameter("name");
        JdbcHelper jh = new JdbcHelper();
        String SQL = "SELECT * FROM things WHERE id = ?";
        List<Map<String,Object>> list = jh.query(SQL , tid);
        jh.destory();


        ServletContext Application = request.getServletContext();
        Application.setAttribute("phoneList" , list);


        request.getRequestDispatcher("/detail.vm").forward(request,response);
    }
}
