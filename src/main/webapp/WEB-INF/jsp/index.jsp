<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty message}">
    ${message}</br>
</c:if>

Login:
<form action="/user/login" method="post">
    username: <input type="text" name="username"></br>
    pasword: <input type="password" name="password"></br>
    <input type="submit" value="Login">
</form>

New User?
<form action="/user/register" method="post">
    username: <input type="text" name="username"></br>
    password: <input type="password" name="password"></br>
    <input type="submit" value="Register">
</form>