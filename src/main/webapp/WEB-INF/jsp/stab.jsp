<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<div>
    <%--для сброса не замапенных запросов--%>
    ${message}
    <br/>
    <br/>
    <br/>
    <form action="logout" method="post">
        <input type="submit" value="Logout">
    </form>
</div>
</body>
</html>
