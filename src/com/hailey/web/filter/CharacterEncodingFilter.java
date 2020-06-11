package com.hailey.web.filter;

import javax.servlet.*;
import java.io.IOException;

// annotation(주석)으로 하려면 web.xml 건들지 않고 여기에 @WebFilter("/*") 해주면 된다.
public class CharacterEncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("before filter");
        // 요청이 올 때마다 실행되기도 하지만 톰캣이 실행될 때에도 실행되기 때문에 필터 설정하고 처음 구동하면 두 번 출력된다.
        // <url-pattern>/*</url-pattern> 이기 때문에 모든 url에 대해서 위의 코드가 실행
        // console에 위의 코드가 찍히고 웹브라우저에 아무것도 안뜬다. filterChain 이 흐름을 다음으로 넘어줄 것인가 결정하기 때문

        request.setCharacterEncoding("UTF-8");
        //모든 servlet은 인코딩 필터가 적용된 환경을 가지게 된다.

        chain.doFilter(request, response);
        // 요청이 오면 다음 filter, servlet이 실행되게 하고 그 후의 결과가 response로 돌아오면 그 후에 밑의 코드가 실행
        // 여기서 조건 검사에서 다음 실행을 어디로 갈지 결정할 수 있다.

        System.out.println("after filter");
    }
}
