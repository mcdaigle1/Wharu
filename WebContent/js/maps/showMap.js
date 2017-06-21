$(document).ready(function() {
	initUserEvents();
	initMap();
	showUserLegend();
	
	$('#map_user_legend').on('click', '.map_user_legend_row', function(){
	    userRowId = this.id;
	    userId = userRowId.split("_")[3];
	    toggleUserDisplay(userId);
	});
	
});

var mapEvent = null;
var usersShown = {};

function initUserEvents() {
	eventJson = decodeURIComponent($("#eventJson").attr("data-json")).replace(/\+/g, " ");
	mapEvent = JSON.parse(eventJson);
	
	for (i = 0; i < mapEvent.userEvents.length; i++) {
		usersShown[mapEvent.userEvents[i].id.toString()] = "true";
	}
}

function initMap(latLongArray) {

	var map = new google.maps.Map($('#map').get(0), {});
	
	// Loop through our array of markers & place each one on the map  
	var bounds = new google.maps.LatLngBounds();

	alert("Event: " + JSON.stringify(mapEvent));
	for (i = 0; i < mapEvent.userEvents.length; i++) {
		lineCoordinates = [];
		// this user's info is not shown on the map
		if (usersShown[mapEvent.userEvents[i].id.toString()] == "false") { continue };
		
		user_event_color = mapEvent.userEvents[i].color
		
		mapCoords = mapEvent.userEvents[i].mapCoordinates;
		for (j = 0; j <  mapCoords.length; j++) {
			splitTime = mapCoords[j]["arrivalTime"].split(" ");
			labelTime = splitTime[3] + " " + splitTime[4];
			
			var position = new google.maps.LatLng(mapCoords[j]["latitude"], mapCoords[j]["longitude"]);
			bounds.extend(position);
	
			var markerUrl = "http://localhost:8080/Wharu/images/dot_" + user_event_color + ".png";
			var markerSize = 16;
			if (j == mapCoords.length - 1) {
	 			markerUrl = "http://localhost:8080/Wharu/images/dot_target_" + user_event_color + ".png"
	 			markerSize = 18;	
			}
			
			var icon = new google.maps.MarkerImage(
					markerUrl, null, null,
					new google.maps.Point(markerSize / 2, markerSize / 2), 
					new google.maps.Size(markerSize, markerSize));
	
			marker = new MarkerWithLabel({
				position: position,
				map: map,
				icon: icon,
			    labelContent: labelTime,
			    labelAnchor: new google.maps.Point(-15, 7),
			    labelClass: "map_labels", // the CSS class for the label
			    labelInBackground: false,
			    labelStyle: {color: "#" + user_event_color}
			});
			
			lineCoordinates.push({lat: mapCoords[j]["latitude"], lng: mapCoords[j]["longitude"]}); 
			
	//		// Allow each marker to have an info window    
	//		google.maps.event.addListener(marker, 'click', (function(marker, i) {
	//			return function() {
	//				infoWindow.setContent(infoWindowContent[i][0]);
	//				infoWindow.open(map, marker);
	//			}
	//		})(marker, i));
		}
		
		var linePath = new google.maps.Polyline({
			path: lineCoordinates,
			geodesic: true,
			strokeColor: "#" + user_event_color,
			strokeOpacity: 1.0,
			strokeWeight: 2
		});
		linePath.setMap(map);

	};
	
//	var linePath = new google.maps.Polyline({
//		path: lineCoordinates,
//		geodesic: true,
//		strokeColor: '#FF0000',
//		strokeOpacity: 1.0,
//		strokeWeight: 2
//	});
//	linePath.setMap(map);

	map.fitBounds(bounds);
}

function showUserLegend() {
	userRowHtml = "";
	for (i = 0; i < mapEvent.userEvents.length; i++) {
		user = mapEvent.userEvents[i].user;
		userRowHtml += "<div class='map_user_legend_row' id='user_legend_row_" + user.id + "'>";
		userRowHtml += "<div class='map_user_legend_checkbox'><input type='checkbox' id='user_checkbox_" + user.id + "' checked></div>"
		userRowHtml += "<div class='map_user_legend_name' style='color:#" + mapEvent.userEvents[i].color + "'>" + user.displayName + "</div>";
		userRowHtml += "</div>";
	}
	
	$("#map_user_legend").html(userRowHtml);
}

function toggleUserDisplay(userId) {
	if(usersShown[i] == "true") {
		usersShown[i] = "false";
		$("#user_checkbox_" + userId).prop('checked', false);		
	} else {
		usersShown[i] = "true";
		$("#user_checkbox_" + userId).prop('checked', true);	
	}
	initMap();
}

