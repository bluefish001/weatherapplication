<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>weather application</title>  

     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  </head>

  <body ng-app="myApp" class="ng-cloak">
  
   <div ng-controller="WeatherController">
	<select>
		<option ng-repeat="city in cities">{{city}}</option>

   </select>
       
    </div>

</div>
       
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/static/js/app.js'/>"> </script>
      <script src="<c:url value='/static/js/service/weather_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/weather_controller.js' />"></script>
  </body>
</html>