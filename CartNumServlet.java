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

@WebServlet("/cartNum")
public class CartNumServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String username = (String)session.getAttribute("username");
        if(username != null) {
            JdbcHelper jh = new JdbcHelper();
            String SQL = "select * from my_shoppingmail where username = ?";
            List<Map<String, Object>> mapList = jh.query(SQL, username);
            jh.destory();
            int number = mapList.size();
            resp.getWriter().print(number);
        }
    }
}
