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


@WebServlet("/showCart")
public class showCartServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcHelper jh = new JdbcHelper();
        String SQL = "select * from my_shoppingmail where username = ?";
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        List<Map<String,Object>> mapList = jh.query(SQL,username);
        jh.destory();
        session.setAttribute("cartList",mapList);
        request.getRequestDispatcher("/cart.vm").forward(request,response);
    }
}
