<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <%--<meta name="_csrf" content="${_csrf.token}"/>--%>
    <%--<meta name="_csrf" content="qwerty"/>--%>
    <%--<meta name="_csrf_header" content="${_csrf.headerName}"/>--%>
</head>
<body>
<>
    <form action="spring_security_check" method="post">
        <input type="text" name="username"/>
        <input type="password" name="password"/>
        <input type="submit" value="Submit">
    </form>
    <div>
        error: ${error}
    </div>
</div>
</body>
</html>
