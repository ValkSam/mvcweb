<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<p>${Attribute}</p>
<br/>
<br/>
<br/>

<form action="logout" method="post">
    <input type="submit" value="Logout">
</form>

<input type="button" value="SEND REST" onclick="sendAjax()">

<div>
    <p id="data"></p>
</div>

</body>

<script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"></script>
<script>
    function sendAjax() {
        $.ajax({
            type: "GET",
            url: 'rest/user/100000',
            data: '',
            success: function (data) {
                $("#data").text(data.id+" "+data.name);
            }
        });
    }
</script>




</html>
