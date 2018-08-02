package xatu.shixi;

import Utils.EncryptHelper;
import Utils.EncryptType;
import Utils.JdbcHelper;
import Utils.StringHelper;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = request.getParameter("account");
        String password = request.getParameter("password");
        String identity = request.getParameter( "identity" );

        if( StringHelper.empty( username )  ||  StringHelper.empty( password )) {
            response.sendRedirect("/nocanEmpty.html");
            return;
        }

        if( StringHelper.empty( identity ) ){ // 如果 验证码 是空的
            // 就给出提示信息
            session.setAttribute( "message" , "验证码没填" ); // session.setAttribute( 名称 , 取值 );
            // 然后将请求 "重定向" ( redirect ) 到 注册页面
            response.sendRedirect( "/registeriderror.html" );
            return ;
        }

        /**************** 比较验证码 ************/
        String code = (String)session.getAttribute( "code" ); // 从 会话 对象 中获取已经保存的 验证码字符串
        // 注意这里有 感叹号
        if( ! StringHelper.equals( identity , code , true , true )){ // 如果验证码输入错误
            // 就给出提示信息
            session.setAttribute( "message" , "验证码输入错误" ); // session.setAttribute( 名称 , 取值 );
            // 然后将请求 "重定向" ( redirect ) 到 注册页面
            response.sendRedirect( "/registeriderror.html" );
            return ;
        }


        session.setAttribute( "username" , username );
        password = EncryptHelper.encrypt( password.trim() , EncryptType.SHA1 );

        JdbcHelper helper = new JdbcHelper();
        String SQL = "insert into user (sname,password) values (?,?)";
        int i = helper.insert(SQL,username,password);
        helper.destory();
        response.sendRedirect( "/login.vm" );
    }
}
