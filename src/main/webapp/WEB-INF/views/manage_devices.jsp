<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form class="form-horizontal" id="register-device">
    <fieldset>

        <legend>Register device</legend>

        <div class="alert alert-info alert-dismissable" id="status-register" style="display:none;">
            <button type="button" class="close" onclick="$('#status-register').hide()"
                    aria-hidden="true">&times;</button>
            <div id="status-register-message"></div>
        </div>

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
                <input class="btn btn-custom" id="register" value="Register">
            </div>
        </div>
    </fieldset>
</form>

<form class="form-horizontal" id="unregister-device" action="<s:url value="/device/unregister"/>" method="POST">
    <fieldset>

        <legend>Unregister device</legend>

        <div class="alert alert-info alert-dismissable" id="status-unregister" style="display:none;">
            <button type="button" class="close" onclick="$('#status-unregister').hide()"
                    aria-hidden="true">&times;</button>
            <div id="status-unregister-message"></div>
        </div>

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
                        <option value="${device.deviceId}" id="device-list">
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
                <input class="btn btn-custom" id="unregister" value="Unregister">
            </div>
        </div>
    </fieldset>
</form>

<script>
    $('#register').click(function () {
        $('#status-register').hide();
        var postData = $('#register-device').serialize();
        var deviceId = $('#deviceId').val();
        $.ajax({
                    type: "POST",
                    url: "<s:url value="/device/register"/>",
                    data: postData,
                    success: function (result) {
                        $("#deviceList").append('<option value="' + deviceId + '">' + deviceId + '</option>');
                        $("#status-register-message").html(result.sentStatus);
                        $('#status-register').show();
                    },
                    error: function () {
                        $("#status-register-message").html("Server is unavailable");
                        $('#status-register').show();
                    }
                }
        )
    });

    $('#unregister').click(function () {
        $('#status-unregister').hide();
        var postData = $('#unregister-device').serialize();
        var deviceId = $('#deviceList').val();
        $.ajax({
                    type: "POST",
                    url: "<s:url value="/device/unregister"/>",
                    data: postData,
                    success: function (result) {
                        $('#deviceList').find('option[value="' + deviceId + '"]').remove();
                        $("#status-unregister-message").html(result.sentStatus);
                        $('#status-unregister').show();
                    },
                    error: function () {
                        $("#status-unregister-message").html("Server is unavailable");
                        $('#status-unregister').show();
                    }
                }
        )
    });
</script>
