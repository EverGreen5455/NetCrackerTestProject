<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page session="false" %>
<html>
<head>
    <title>Lessons Page</title>

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

<a href="/teachers">Back to Teacher List</a>

<br/>
<br/>

<c:if test="${!empty listLessons}">

    <h1>Lesson List</h1>

    <table class="tg">
        <tr>
            <th width="120">Lesson Name</th>
            <th width="120">Lesson Date</th>
            <th width="120">Lesson Time</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listLessons}" var="lesson">
            <tr>
                <td>${lesson.lessonName}</td>
                <td>${lesson.lessonDate}</td>
                <td>${lesson.lessonTime}</td>
                <td><a href="<c:url value='/lessons/edit/${lesson.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/lessons/remove/${lesson.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<h1>Add a Lesson</h1>

<c:url var="addAction" value="/lessons/add/${teacherId}"/>

<form:form action="${addAction}" commandName="lesson">
    <table>
        <c:if test="${!empty lesson.lessonName}">
            <tr>
                <td>
                    <form:input type="hidden" path="id" readonly="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="lessonName">
                    <spring:message text="Lesson Name"/>
                </form:label>
            </td>
            <td>
                <form:input required="true" path="lessonName" placeholder="Lesson Name"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="lessonDate">
                    <spring:message text="Lesson Date"/>
                </form:label>
            </td>
            <td>
                <form:input required="true" type="date" path="lessonDate" min="2000-01-01" max="2099-12-31"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="lessonTime">
                    <spring:message text="Lesson Time"/>
                </form:label>
            </td>
            <td>
                <form:input required="true" type="time" path="lessonTime"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty lesson.lessonName}">
                    <input type="submit"
                           value="<spring:message text="Edit Lesson"/>"/>
                </c:if>
                <c:if test="${empty lesson.lessonName}">
                    <input type="submit"
                           value="<spring:message text="Add Lesson"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
