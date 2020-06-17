package example;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/array")
public class Array extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        // UTF-8로 인코딩해서 보냈지만 해석하는 쪽이 다른 인코딩방식으로 해석하면 한글이 깨진다.
        response.setContentType("text/html; charset=UTF-8");
        // 브라우저에게 문자를 자의적으로 해석하지 말고 또한 UTF-8로 해석하라고 알려주는 정보를 보낸다.

        request.setCharacterEncoding("UTF-8");
        // form에서 post로 보낸 한글을 서버에서 UTF-8 읽어라.

        PrintWriter out = response.getWriter();

        String[] numArr = request.getParameterValues("num");
        // 쿼리스트링으로 요청해서 getParameter로 들어오는 값은 무조건 String type

        int result = 0;
        for(int i=0; i<numArr.length; i++){

            if(!numArr[i].equals("")) {
                int num = Integer.parseInt(numArr[i]);
                // 연산은 반복되지만 선언은 반복될 일이 없다.
                result += num;
            }
        }
            out.println(result);

    }
}

