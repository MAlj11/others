package xatu.shixi;

import Utils.JdbcHelper;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/pay")
public class payPageSrevlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if(username == null){
            response.sendRedirect("/login.vm");
            return;
        }else {
            String count = request.getParameter("total");
            float countInt = Float.parseFloat(count);
            if(countInt == 0.00){
               response.sendRedirect("/EmptyCart.html");
               return;
            }
            Map<String,String> map2= new HashMap<>();
            String para = request.getQueryString();
            String[] paraArray = para.split("&");
                   for(int i = 0 ; i < paraArray.length ; i++){
                       String[] arr = null;
                       arr = paraArray[i].split("=");
                       map2.put(arr[0],arr[1]);
           }
            System.out.println(map2.get("1"));
            System.out.println(map2.get("2"));
            session.setAttribute("totalPrice", count);
            JdbcHelper jh = new JdbcHelper();
            String SQL = "DELETE FROM my_shoppingmail WHERE username = ?";
            String SQL2 = "SELECT * FROM my_shoppingmail WHERE username = ?";
            List<Map<String,Object>>list = jh.query(SQL2,username);
            int i = jh.update(SQL, username);
            jh.destory();
            JdbcHelper jh2 = new JdbcHelper();
            String SQL3 ="INSERT INTO myorder (tid,sname,photo,color,price,flag,username,num) VALUES (?,?,?,?,?,?,?,?)";
            for(int j = 0; j < list.size();j++){
                int tid = (Integer) list.get(j).get("tid");
                String sname = (String) list.get(j).get("sname");
                String photo = (String)list.get(j).get("photo");
                String color = (String)list.get(j).get("color");
                int price = (Integer)list.get(j).get("price");
                String flag = "已支付";
                String sid = String.valueOf(tid);
                String num = map2.get(sid);
                //int numInt = Integer.parseInt(num);
                jh2.insert(SQL3,tid,sname,photo,color,price,flag,username,num);
                }
                jh2.destory();
            response.sendRedirect("/pay.vm");
        }
    }
}
