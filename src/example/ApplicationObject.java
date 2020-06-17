package example;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/application")
public class ApplicationObject extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ServletContext application = request.getServletContext();
        // ServletContext이 application에 저장된다.
         HttpSession session = request.getSession();
        // session은 개별 사용자(브라우저)를 의미.

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String v_ = request.getParameter("v");
        String op = request.getParameter("operator");

        int v = 0;
        if(!v_.equals(""))  v = Integer.parseInt(v_);

            if(op.equals("=")){ // 계산하기

                //int x = (int) application.getAttribute("value"); // 원래는 object
                int x = (int) session.getAttribute("value"); // 원래는 object

                int y = v;
                //String operator  = (String) application.getAttribute("op");
                String operator  = (String) session.getAttribute("op");

                int result = 0;
                if(operator.equals("+"))
                    result = x+y;
                else
                    result = x-y;

                response.getWriter().printf("result is %d", result);

            }
            else { // 저장하기

                // application.setAttribute("value", v);
                session.setAttribute("value", v);
                // application.setAttribute("op", op);
                session.setAttribute("op", op);

            }


    }
}

