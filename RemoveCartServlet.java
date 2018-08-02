package xatu.shixi;

import Utils.JdbcHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeCart")
public class RemoveCartServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        JdbcHelper jh = new JdbcHelper();
        String SQL = "DELETE FROM my_shoppingmail WHERE id= ?";
        int i = jh.update(SQL,id);
        jh.destory();
        request.getRequestDispatcher("/removeCart.html").forward(request,response);
    }
}
