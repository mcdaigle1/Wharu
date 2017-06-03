$(document).ready(function() {
	initMap();
});

function initMap(latLongArray) {
	topLatStr = document.getElementById("topLat").getAttribute("data-lat");
	bottomLatStr = document.getElementById("bottomLat").getAttribute("data-lat");
	leftLongStr = document.getElementById("leftLong").getAttribute("data-long");
	rightLongStr = document.getElementById("rightLong").getAttribute("data-long");
	
	topLat = parseFloat(topLatStr);
	bottomLat = parseFloat(bottomLatStr);
	leftLong = parseFloat(leftLongStr);
	rightLong = parseFloat(rightLongStr);
	
	latLongJson = document.getElementById("latLongJson");
	latLongArray = JSON.parse(latLongJson.getAttribute("data-json"));
	latLongArray.forEach(function(element) {
	    // set up the markers here
	});
	
	centerLat = (topLat + bottomLat) / 2;
	centerLong = (rightLong + leftLong) / 2;

	var centerLatLong = {
		lat : (topLat + bottomLat) / 2,
		lng : (rightLong + leftLong) / 2
	};
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom : 16,
		center : centerLatLong
	});
	var marker = new google.maps.Marker({
		position : centerLatLong,
		map : map
	});
}

function setMarkers(map, locations) {
	var image = new google.maps.MarkerImage('images/beachflag.png',
			new google.maps.Size(20, 32), new google.maps.Point(0, 0),
			new google.maps.Point(0, 32));
	var shadow = new google.maps.MarkerImage('images/beachflag_shadow.png',
			new google.maps.Size(37, 32), new google.maps.Point(0, 0),
			new google.maps.Point(0, 32));
	var shape = {
		coord : [ 1, 1, 1, 20, 18, 20, 18, 1 ],
		type : 'poly'
	};
	var bounds = new google.maps.LatLngBounds();
	for (var i = 0; i < locations.length; i++) {
		var beach = locations[i];
		var myLatLng = new google.maps.LatLng(beach[1], beach[2]);
		var marker = new google.maps.Marker({
			position : myLatLng,
			map : map,
			shadow : shadow,
			icon : image,
			shape : shape,
			title : beach[0],
			zIndex : beach[3]
		});
		bounds.extend(myLatLng);
	}
	map.fitBounds(bounds);
}