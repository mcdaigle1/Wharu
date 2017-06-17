<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/main.css" rel="stylesheet" type="text/css">
	<link href="css/map.css" rel="stylesheet" type="text/css">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCHr9F4YhtLItojVpgcr_9zas28XeQokxw"></script>
	<script src="https://cdn.rawgit.com/googlemaps/v3-utility-library/master/markerwithlabel/src/markerwithlabel.js"></script>
	<script src="js/maps/showMap.js"></script>
	
	<title>Map</title>
</head>
<body>
	<div id="eventJson" data-json="${requestScope.eventJson}"></div>
	<div id="map" class="map"></div>
	<div id="map_user_legend" class="map_user_legend"></div>
</body>
</html>