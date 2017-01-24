<%--
  Created by IntelliJ IDEA.
  User: alexander
  Date: 24.01.2017
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Security WebApp login page</title>
</head>
<body bgcolor="#cccccc">
<blockquote>
    <img src=BEA_Button_Final_web.gif align=right>
    <h2>Please enter your user name and password:</h2>
    ${pageContext.request.userPrincipal}
    <form>
        <table border=1>
            <tr>
                <td>Username:</td>
                <td><input type="text" name="j_username"></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="j_password"></td>
            </tr>
            <tr>
                <td colspan=2 align=right><input type=submit
                                                 value="Submit"></td>
            </tr>
        </table>
    </form>
</blockquote>
</body>
</html>