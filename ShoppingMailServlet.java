package xatu.shixi;

import Utils.JdbcHelper;
import Utils.StringHelper;

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

@WebServlet("/addtoCart")
public class ShoppingMailServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if(username == null){
            response.sendRedirect("/login.vm");
            return;
        }else {
            String tid = request.getParameter("name");
            int id = Integer.parseInt(tid);
            JdbcHelper jh = new JdbcHelper();
            String SQL = "SELECT * FROM things WHERE id = ?";
            List<Map<String, Object>> list = jh.query(SQL, id);
            jh.destory();


            Map<String, Object> map = list.get(0);

            String name2 = (String) map.get("sname");
            String photo = (String) map.get("photo");
            String color = (String) map.get("color");
            int price = (Integer) map.get("price");
            String memory = (String)map.get("MEMORY");
            JdbcHelper jh2 = new JdbcHelper();
           // System.out.println("插入");
            String SQL2 = "INSERT INTO my_shoppingmail (tid,sname,things,color,photo,number,price,is_pay,memory,username) VALUES (?,?,?,?,?,?,?,?,?,?)";
            int i = jh2.insert(SQL2, id,name2, "手机", color, photo, 1, price, 0,memory,username);

            //System.out.println(i);
            jh2.destory();
            request.getRequestDispatcher("addToCartSuccess.html").forward(request, response);
            }

    }
}
