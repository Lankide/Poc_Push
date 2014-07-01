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
    <title>Push Notifications</title>
</head>

<body>
<div class="container">
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-left">
                    <li><a href="<s:url value="/home"/>">Invoice history</a></li>
                    <li><a href="<s:url value="/device/manage"/>">Manage Devices</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="<s:url value="/login"/>">Logout</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="col-md-offset-1 col-md-10" id="content">
          <t:insertAttribute name="content"/>
    </div>
</div>
</body>
</html>