<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form class="form-horizontal" id="register-device" action="" method="POST">
    <fieldset>

        <legend>Register device</legend>

        <div class="form-group">
            <label class="col-md-4 control-label" for="userListRegister">Choose user:</label>

            <div class="col-md-8">
                <select class="form-control input-md" id="userListRegister">
                    <option value="${user.userId}">${user.userEmail}</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="deviceID">Enter ID:</label>

            <div class="col-md-8">
                <input class="form-control input-md" type="text" id="deviceID" name="deviceID">
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

<form class="form-horizontal" id="unregister-device" action="" method="POST">
    <fieldset>

        <legend>Unregister device</legend>

        <div class="form-group">
            <label class="col-md-4 control-label" for="userListUnregister">Choose user:</label>

            <div class="col-md-8">
                <select class="form-control input-md" id="userListUnregister">
                    <option value="${user.userId}">${user.userEmail}</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="deviceList">Choose device:</label>

            <div class="col-md-8">
                <select class="form-control input-md" id="deviceList">
                    <c:forEach var="device" items="${user.deviceList}">
                        <option value="${device.deviceId}">
                            ...${fn:substring(device.deviceId, fn:length(device.deviceId)-21, fn:length(device.deviceId)-1)}</option>
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
