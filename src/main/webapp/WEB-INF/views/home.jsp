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
    <c:forEach var="user" items="${users}">
        <table class="table payment-table header-fixed">
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
                    <td>${invoice.invoiceName}</td>
                    <td>${invoice.invoiceSubmittedTS}</td>
                    <td>${invoice.invoiceCompletedTS}</td>
                    <td></td>
                    <td></td>
                </tr>
                <c:forEach var="payment" items="${invoice.invoicePaymentList}">
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>${payment.paymentId}</td>
                        <td>${payment.amount}</td>
                    </tr>
                </c:forEach>
            </c:forEach>

            </tbody>
        </table>

        <form action="<s:url value="/invoices/${user.userId}"/>" method="GET">
            <div>
                <label for="predict"></label>

                <div>
                    <input class="btn btn-custom" id="predict" type="submit" value="Predict & Send">
                </div>
            </div>
        </form>
    </c:forEach>
</div>