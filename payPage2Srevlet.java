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

@WebServlet("/payPage2")
public class payPage2Srevlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if(username == null){
            response.sendRedirect("/login.vm");
            return;
        }else {
            String  id = request.getParameter("id");
            String total = request.getParameter("total");
            JdbcHelper jh = new JdbcHelper();
            String SQL2 = "SELECT * FROM things WHERE id = ?";
            List<Map<String,Object>>list = jh.query(SQL2,id);
            jh.destory();
            JdbcHelper jh2 = new JdbcHelper();
            String SQL3 ="INSERT INTO myorder (tid,sname,photo,color,price,flag,username) VALUES (?,?,?,?,?,?,?)";

                int tid = (Integer) list.get(0).get("id");
                String sname = (String) list.get(0).get("sname");
                String photo = (String)list.get(0).get("photo");
                String color = (String)list.get(0).get("color");
                int price = (Integer)list.get(0).get("price");
                session.setAttribute("totalPrice",total);
                String flag = "已支付";
                jh2.insert(SQL3,tid,sname,photo,color,price,flag,username);

                jh2.destory();
            response.sendRedirect("/pay2.vm");
        }
    }
}
