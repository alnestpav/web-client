<%--
  Created by IntelliJ IDEA.
  User: alexander
  Date: 17.01.2017
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Starter Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/index.css" rel="stylesheet">

</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container">
    <form action="./search" method="post">
        <label for="stringInput">String</label>
        <input type="text" class="form-control" id="stringInput" name="string" placeholder="String or regular expression">

        <div class="row">
            <div class="col-md-6" id="dateFromDiv">
                <label for="dateFrom">Date from</label>
                <input type="text" class="form-control" id="dateFrom" name="dateFrom" placeholder="XMLGregorianCalendar">
            </div>
            <div class="col-md-6" id="dateToDiv">
                <label for="dateTo">Date to</label>
                <input type="text" class="form-control" id="dateTo" name="dateTo" placeholder="XMLGregorianCalendar">
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <button type="button" class="btn btn-default" id="addDateIntervalButton">Add date interval</button>
            </div>
        </div>

        <label for="locationInput">Location for search</label>
        <input type="text" class="form-control" id="locationInput" name="location" placeholder="Domain, cluster or server">

        <label for="FileFormatSelect">File format</label>

        <!-- Переписать, чтобы форматы брались из перечисления FileFormat -->
        <select class="form-control" id="FileFormatSelect" name="fileFormat">
            <option>no</option>
            <option>doc</option>
            <option>html</option>
            <option>log</option>
            <option>pdf</option>
            <option>rtf</option>
            <option>xml</option>
        </select>
        <button type="submit" class="btn btn-default" id="startLogSearchButton">Search</button>
    </form>
    <div>
        <c:choose>
            <c:when test="${not empty logMessages}">
                <c:forEach items="${logMessages}" var="logMessage">
                    <div class="log-message">
                        <p><c:out value="${logMessage.message}" escapeXml="true"></c:out></p>
                    </div>
                </c:forEach>
            </c:when>
            <c:when test="${logMessages != null && empty logMessages}">
                <div class="jumbotron">
                    <p class="lead">Нет записей</p>
                </div>
            </c:when>
        </c:choose>

        <c:if test="${fileLink != null}">
            <div class="jumbotron">
                <p class="lead">Файл успешно создан!</p>
                <a class="btn btn-lg btn-success" href="${fileLink}" role="button">Скачать</a>
            </div>
        </c:if>


    </div>

</div><!-- /.container -->

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<!-- Скрипт должен выполняться после того, как загрузилась страница -->
<script src="js/index.js"></script>

</body>
</html>
