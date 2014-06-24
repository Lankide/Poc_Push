<%--
  Created by IntelliJ IDEA.
  User: vladyslavprytula
  Date: 4/18/14
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div>
    <div class="alert alert-info alert-dismissable" id="status" style="display:none;">
        <button type="button" class="close" onclick="$('#status').hide()" aria-hidden="true">&times;</button>
        <div id="status-message"></div>
    </div>
    <table class="table payment-table table-fixedheader">
        <thead>
        <tr>
            <th>Invoice</th>
            <th>Submitted</th>
            <th>Completed</th>
            <th>Payment ID</th>
            <th>Amount</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="invoice" items="${user.invoiceList}">
            <tr>
                <td class="invoice-cell">${invoice.invoiceName}</td>
                <td class="invoice-cell"><fmt:formatDate pattern="dd-MM-yyyy"  value="${invoice.invoiceSubmittedTS}"/></td>
                <td class="invoice-cell"><fmt:formatDate pattern="dd-MM-yyyy"  value="${invoice.invoiceCompletedTS}"/></td>
                <td class="invoice-cell"></td>
                <td class="invoice-cell"></td>
            </tr>
            <c:forEach var="payment" items="${invoice.invoicePaymentList}">
                <tr>
                    <td class="payment-cell"></td>
                    <td class="payment-cell"></td>
                    <td class="payment-cell"></td>
                    <td class="payment-cell">${payment.paymentId}</td>
                    <td class="payment-cell">${payment.amount}</td>
                </tr>
            </c:forEach>
        </c:forEach>

        </tbody>
    </table>

    <div class="pull-right">
        <input class="btn btn-custom" id="predict" value="Predict & Send">
    </div>
</div>

<script>
    $('#predict').click(function () {
        $('#status').hide();
        $.ajax({
                    type: "POST",
                    url: "<s:url value="/predictAndSend"/>",
                    success: function (result) {
                        $("#status-message").html(result.sentStatus);
                        $('#status').show();
                    },
                    error: function () {
                        $("#statusmessage").html("Server is unavailable");
                        $('#status').show();
                    }
                }
        )
    })
</script>