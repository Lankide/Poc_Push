<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form class="form-horizontal" id="register-device" action="<s:url value="/device/register"/>" method="POST">
    <fieldset>

        <legend>Register device</legend>

        <div class="form-group">
            <label class="col-md-4 control-label" for="userIdRegister">Choose user:</label>

            <div class="col-md-8">
                <select class="form-control input-md" id="userIdRegister" name="userId">
                    <option value="${user.userId}">${user.userEmail}</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="deviceId">Enter ID:</label>

            <div class="col-md-8">
                <input class="form-control input-md" type="text" id="deviceId" name="deviceId">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="register"></label>

            <div class="col-md-8">
                <input class="btn btn-custom" id="register" type="submit" value="Register">
            </div>
        </div>
    </fieldset>
</form>

<form class="form-horizontal" id="unregister-device" action="<s:url value="/device/unregister"/>" method="POST">
    <fieldset>

        <legend>Unregister device</legend>

        <div class="form-group">
            <label class="col-md-4 control-label" for="userListUnregister">Choose user:</label>

            <div class="col-md-8">
                <select class="form-control input-md" id="userListUnregister" name="userId">
                    <option value="${user.userId}">${user.userEmail}</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="deviceList">Choose device:</label>

            <div class="col-md-8">
                <select class="form-control input-md" id="deviceList" name="deviceId">
                    <c:forEach var="device" items="${user.deviceList}">
                        <option value="${device.deviceId}">
                            <c:choose>
                                <c:when test="${fn:length(device.deviceId) > 20}">
                                    ...${fn:substring(device.deviceId, fn:length(device.deviceId)-21, fn:length(device.deviceId)-1)}
                                </c:when>
                                <c:otherwise>
                                    ${device.deviceId}
                                </c:otherwise>
                            </c:choose>
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="unregister"></label>

            <div class="col-md-8">
                <input class="btn btn-custom" id="unregister" type="submit" value="Unregister">
            </div>
        </div>
    </fieldset>
</form>
