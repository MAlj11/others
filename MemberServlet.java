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
import java.util.Objects;


@WebServlet("/member")
public class MemberServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        JdbcHelper jh = new JdbcHelper();
        String SQL = "select * from user where sname = ? ";
        List<Map<String,Object>> list = jh.query(SQL,username);
        jh.destory();
        Map<String,Object> map = list.get(0);
        session.setAttribute("user",map);
        response.sendRedirect("/member.vm");
    }
}
