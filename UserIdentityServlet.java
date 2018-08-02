package xatu.shixi;

import Utils.GraphicHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet( "/user/identity" )
public class UserIdentityServlet extends HttpServlet {

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获得 与当前请求 关联的 会话对象 ( HttpSession 类型 )
        HttpSession session = request.getSession();

        // 1、通过 HTTP 响应报头 告知 浏览器 本次发送的内容的类型
        response.setContentType( "image/jpeg" );

        // 2、通过 service 方法的第二个参数 ( HttpServletResponse ) 来获取可以向 客户端 输出 字节数据的 输出流
        OutputStream out = response.getOutputStream() ; // 将来产生的 验证码图片 就输出到这个流中
        // 3、使用工具类创建图片验证码并返回图片验证码上面的字符串 ( 输出到指定的 输出流 ( out ) 中 )
        String code = GraphicHelper.create( 4 , false , 150 , 50 , out , 0 );

        // 4、将产生的 验证码字符串 保存到 服务器上 的 当前用户对应的 会话对象中
        session.setAttribute( "code" , code );

    }

}
