import React from 'react';
import axios from 'axios';

import { MapUserLegend } from "./MapUserLegend";
import HashStore from "../stores/HashStore";

export class Map extends React.Component { 
    constructor(props) {
        super(props);
        
        this.getEventFromStore = this.getEventFromStore.bind(this);
        this.state = {event: null};
    };
    
    render() {
        return(
            <div id={"map"} className={"map"} />
        );
    }
    
    componentWillMount() {
        HashStore.on("event", this.getEventFromStore);
    };
    
    componentWillUnmount() {
        HashStore.removeListener("event", this.getEventFromStore);
    };
    
    componentDidUpdate() {
        if(this.state.event != null) 
            this.renderMap();
    };
    
    getEventFromStore() {
        this.setState({event: HashStore.getValue("event")});
    };
    
    renderMap() {
        let event = this.state.event;
        var map = new google.maps.Map(document.getElementById("map"), {});
        
        // Loop through our array of markers & place each one on the map  
        let bounds = new google.maps.LatLngBounds();
        
        for (let i = 0; i < event.userEvents.length; i++) {
            let lineCoordinates = [];
            // this user's info is not shown on the map
            if (event.userEvents[i].shown == false) { continue };
            
            let user_event_color = event.userEvents[i].color
            
            let mapCoords = event.userEvents[i].mapCoordinates;
            for (let j = 0; j <  mapCoords.length; j++) {
                let splitTime = mapCoords[j]["arrivalTime"].split(" ");
                let labelTime = splitTime[3] + " " + splitTime[4];
                
                let position = new google.maps.LatLng(mapCoords[j]["latitude"], mapCoords[j]["longitude"]);
                bounds.extend(position);
        
                let markerUrl = "http://localhost/images/dot_" + user_event_color + ".png";
                let markerSize = 16;
                if (j == mapCoords.length - 1) {
                    markerUrl = "http://localhost/images/dot_target_" + user_event_color + ".png"
                    markerSize = 18;    
                }
                
                let icon = new google.maps.MarkerImage(
                        markerUrl, null, null,
                        new google.maps.Point(markerSize / 2, markerSize / 2), 
                        new google.maps.Size(markerSize, markerSize));
        
                let marker = new MarkerWithLabel({
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
            }
            
            let linePath = new google.maps.Polyline({
                path: lineCoordinates,
                geodesic: true,
                strokeColor: "#" + user_event_color,
                strokeOpacity: 1.0,
                strokeWeight: 2
            });
            linePath.setMap(map);

        };

        map.fitBounds(bounds);
    } 
}