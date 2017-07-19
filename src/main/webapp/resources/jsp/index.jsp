<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/main.css" rel="stylesheet" type="text/css">
	<link href="css/map.css" rel="stylesheet" type="text/css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.6/react.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.6/react-dom.js"></script>
	<!--  script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script -->
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCHr9F4YhtLItojVpgcr_9zas28XeQokxw"></script>
	<script src="https://cdn.rawgit.com/googlemaps/v3-utility-library/master/markerwithlabel/src/markerwithlabel.js"></script>
	<script async src="js/scripts.min.js"></script>
	
	<title>Wharu</title>
</head>
<body>
	<!-- div id="event_json" data-json="${requestScope.eventJson}"></div -->
	<div id="app_section"></div>
	<!-- div id="map_dummy" class="dummy"></div -->
</body>
</html>