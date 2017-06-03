<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/map.css" rel="stylesheet" type="text/css">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCHr9F4YhtLItojVpgcr_9zas28XeQokxw"></script>
	<script src="js/maps/showMap.js"></script>
	
	<title>Map</title>
</head>
<body>
	<div id="latLongJson" data-json="${requestScope.latLongJson}"></div>
	<div id="topLat" data-lat="${requestScope.topLat}"></div>
	<div id="bottomLat" data-lat="${requestScope.bottomLat}"></div>
	<div id="leftLong" data-long="${requestScope.leftLong}"></div>
	<div id="rightLong" data-long="${requestScope.rightLong}"></div>
	<div id="map"></div>
</body>
</html>