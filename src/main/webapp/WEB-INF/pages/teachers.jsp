<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page session="false" %>
<html>
<head>
    <title>Teachers Page</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            text-align: center;
            padding: 10px 5px;
            overflow: hidden;
            word-break: normal;
            border: 1px solid #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            overflow: hidden;
            word-break: normal;
            border: 1px solid #ccc;
            color: #333;
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>

<a href="../../index.jsp">Back to main menu</a>

<br/>
<br/>

<c:if test="${!empty listTeachers}">

    <h1>Teacher List</h1>

    <table class="tg">
        <tr>
            <th width="120">First Name</th>
            <th width="120">Last Name</th>
            <th width="60">Details</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listTeachers}" var="teacher">
            <tr>
                <td>${teacher.firstName}</td>
                <td>${teacher.lastName}</td>
                <td><a href="<c:url value='/schedule/${teacher.id}'/>">Timetable</a></td>
                <td><a href="<c:url value='/edit/${teacher.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/remove/${teacher.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<h1>Add a Teacher</h1>

<c:url var="addAction" value="/teachers/add"/>

<form:form action="${addAction}" commandName="teacher">
    <table>
        <c:if test="${!empty teacher.firstName}">
            <tr>
                <td>
                    <form:input type="hidden" path="id" readonly="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="firstName">
                    <spring:message text="First Name"/>
                </form:label>
            </td>
            <td>
                <form:input required="true" path="firstName" placeholder="First Name"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="lastName">
                    <spring:message text="Last Name"/>
                </form:label>
            </td>
            <td>
                <form:input required="true" path="lastName" placeholder="Last Name"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty teacher.firstName}">
                    <input type="submit"
                           value="<spring:message text="Edit Teacher"/>"/>
                </c:if>
                <c:if test="${empty teacher.firstName}">
                    <input type="submit"
                           value="<spring:message text="Add Teacher"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
