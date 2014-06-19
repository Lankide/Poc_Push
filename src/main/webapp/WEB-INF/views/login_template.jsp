<%--
  Created by IntelliJ IDEA.
  User: vladyslavprytula
  Date: 4/18/14
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<head>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet">
    <title>Login</title>
</head>

<body>
<div id="container">
    <div id="content">
        <t:insertAttribute name="content"/> <!--<co id="co_tile_content" />-->
    </div>
</div>
</body>
</html>
