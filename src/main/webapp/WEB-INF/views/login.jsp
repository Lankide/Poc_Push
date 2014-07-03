<%--
  Created by IntelliJ IDEA.
  User: kostiantyn.boriak
  Date: 6/17/2014
  Time: 1:40 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div>
    <div class="col-md-offset-3 col-md-6">
        <form class="form-horizontal" id="loginForm" action="<s:url value="/home"/>" method="GET">
            <fieldset>

                <legend>Please sign in</legend>

                <c:if test="${param.error != null}">
                    <div class="alert alert-danger">
                        The email or password is incorrect
                    </div>
                </c:if>

                <c:if test="${param.logout != null}">
                    <div class="alert alert-success">
                        You have been logged out
                    </div>
                </c:if>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="email">Email</label>

                    <div class="col-md-6">
                        <input id="email" name="email" type="email" placeholder="Email address"
                               class="form-control input-md">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="password">Password</label>

                    <div class="col-md-6">
                        <input id="password" name="password" type="password" placeholder="Password"
                               class="form-control input-md">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="submit"></label>

                    <div class="col-md-4">
                        <button id="submit" name="submit" type="submit" class="btn btn-custom">Sign in</button>
                    </div>
                </div>

            </fieldset>
        </form>
    </div>
</div>
