package example;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/*
    application
            사용범위 : 전역범위,
            생명주기 : WAS가 시작해서 종료시
            저장위치 : WAS 서버의 메모리
    session
            사용범위 : 세션범위,
            생명주기 : WAS가 시작해서 종료시
            저장위치 : WAS 서버의 메모리
    cookie
            사용범위 : Web Brower 별 지정한 path 범주   공간(특정 url)
            생명주기 : Brower에 전달한 시간부터 만료시간까지
            저장위치 : Web Brower의 메모리 또는 파일
*/

@WebServlet("/cookie")
public class Cookies extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        javax.servlet.http.Cookie[] cookiesArr = request.getCookies();
        // 쿠키 온 거 읽기

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String v_ = request.getParameter("v");
        String op = request.getParameter("operator");

        int v = 0;
        if(!v_.equals(""))  v = Integer.parseInt(v_);

            if(op.equals("=")){ // 계산하기

                int x = 0;
                for(javax.servlet.http.Cookie c : cookiesArr)
                    if (c.getName().equals("value")) {
                        x = Integer.parseInt(c.getValue());
                        break;
                    }

                int y = v;
                String operator = "";
                for(javax.servlet.http.Cookie c : cookiesArr)
                    if (c.getName().equals("op")) {
                        operator = c.getValue();
                        break;
                    }

                int result = 0;
                if(operator.equals("+"))
                    result = x+y;
                else
                    result = x-y;

                response.getWriter().printf("result is %d", result);

            }
            else { // 저장하기

                Cookie valueCookie = new Cookie("value", String.valueOf(v));
                // Cookie는 String만 저장가능
                Cookie opCookie = new Cookie("op", op);

                valueCookie.setPath("/cookie");
                opCookie.setPath("/cookie");
                // path option 주기. 이 url으로만 쿠키를 가져간다.

                valueCookie.setMaxAge(60*60*24); // 초 단위
                // 기본적으로 cookie는 IN-MEMORY에 있다가 쿠키 기한을 설정하면 부라우저 닫히는거와 상관없이
                // 기한만큼 영구저장소인 외부파일 IN-FILE에 저장된다.

                response.addCookie(valueCookie);
                response.addCookie(opCookie);
                // 클라이언트의 header에 심어지는 쿠키 전달

                response.sendRedirect("/06_cookie.html");
                // 서버측에서 이 페이지를 돌려준다.
            }


    }
}

