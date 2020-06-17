package example;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/page") //calc3
public class ServerPage extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 쿠키 온 거 읽기
        Cookie[] cookiesArr = request.getCookies();

        // 사용자가 입력한 내용
        String value = request.getParameter("value");
        String operator = request.getParameter("operator");
        String dot = request.getParameter("dot");

        // 기존에 만들었던 exp
        String exp = "";
        if(cookiesArr != null) {
            for (Cookie c : cookiesArr)
                if (c.getName().equals("exp")) {
                    exp = c.getValue();
                    break;
                }
        }

        if(operator != null && operator.equals("=")){

            // 자바스크립트로 계산해보기
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            try {
                exp = String.valueOf(engine.eval(exp));
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }
/*
        else if(operator != null && operator.equals("C")) {

            exp=""; // 쿠키가 완전히 사라진 것이 아니고 그냥 빈 문자열이 된다.
        }
*/
        else { // 기존에 만든 exp에 사용자가 입력한 cookie 가져와서 덧붙힌다.

            exp += (value == null) ? "" : value;
            exp += (operator == null) ? "" : operator;
            exp += (dot == null) ? "" : dot;
        }

        // 그것을 쿠키로 저장
        Cookie expCookie = new Cookie("exp", exp);

        if(operator != null && operator.equals("C"))
            expCookie.setMaxAge(0); // 쿠키가 브라우저로 가서 바로 사라짐.

        response.addCookie(expCookie);
        expCookie.setPath("/");
        // 패스설정은 1개만 설정할 수 있기 때문에 하나의 url 또는 전역만 설정가능하다.
        // 지금 /page와 /result에서 쓰고 싶기 때문에 전역사용으로 설정했다.

        response.sendRedirect("result"); // 경로가 같을 때에는 / 안붙여도 된다.
        // 서버측에서 이 페이지를 돌려준다.

    }
}

