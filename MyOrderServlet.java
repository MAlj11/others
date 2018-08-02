package xatu.shixi;

import Utils.JdbcHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/myOrder")
public class MyOrderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        JdbcHelper jh = new JdbcHelper();
        String SQL = "select * from myorder where username = ?";
        List<Map<String,Object>> list = jh.query(SQL,username);
        jh.destory();
        session.setAttribute("orders",list);
        response.sendRedirect("/order.vm");
    }
}
