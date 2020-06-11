<%@ page language="java"
         contentType="text/html;charset=UTF-8" <%-- 브라우저가 인식하게 하는 헤더 --%>
         pageEncoding="UTF-8" %> <%-- 브라우저에 출력할 때 UTF-8로 출력하겠다. --%>

<%
    /* 밑의 html 코드는 모두 출력하지만 이 안에 적은것은 그냥 코드이기 때문에 출력 하지 말라고 알려준다. */
    int x = 3;
    int y = 4;
%>
내가 만든 코드블럭 외에 제스퍼가 만든 코드블럭이 서블릿에 있다.

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        input {
            width: 50px;
            height: 50px;
        }
        .output {
            height: 50px;
            background-color: #e9e9e9;
            font-size: 24px;
            font-weight: bold;
            text-align: right;
            padding: 0px 5px; /* 상하 0px, 좌우 5px */
        }
    </style>
</head>
<body>
  <div>
      <form action="page">
          <table>
              <tr>
                  <td class="output" colspan="4">${3+4}</td> // html은 정적, jsp는 동적
              </tr>
              <tr>
                  <td><input type="submit" name="operator" value="CE"></td>
                  <td><input type="submit" name="operator" value="C"></td>
                  <td><input type="submit" name="operator" value="BS"></td>
                  <td><input type="submit" name="operator" value="/"></td>
              </tr>
              <tr>
                  <td><input type="submit" name="value" value="7"></td>
                  <td><input type="submit" name="value" value="8"></td>
                  <td><input type="submit" name="value" value="9"></td>
                  <td><input type="submit" name="operator" value="x"></td>
              </tr>
              <tr>
                  <td><input type="submit" name="value" value="4"></td>
                  <td><input type="submit" name="value" value="5"></td>
                  <td><input type="submit" name="value" value="6"></td>
                  <td><input type="submit" name="operator" value="-"></td>
              </tr>
              <tr>
                  <td><input type="submit" name="value" value="1"></td>
                  <td><input type="submit" name="value" value="2"></td>
                  <td><input type="submit" name="value" value="3"></td>
                  <td><input type="submit" name="operator" value="+"></td>
              </tr>
              <tr>
                  <td></td>
                  <td><input type="submit" name="value" value="0"></td>
                  <td><input type="submit" name="dot" value="."></td>
                  <td><input type="submit" name="operator" value="="></td>
              </tr>
          </table>
      </form>
  </div>
</body>
</html>
