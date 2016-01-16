<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> users list </title>
</head>
<body>
<h1>User</h1>
${user}
<%! int i = 0; %>
<p>
Hi, now the servlet processing <%= ++i %>th request.
</p>

</body>
</html>
