import React from 'react';
import { render } from 'react-dom';
import axios from 'axios';

import { Map } from "./Map";
import { MapUserLegend } from "./MapUserLegend";
import HashStore from "../stores/HashStore";

export class MapSection extends React.Component {
    constructor(props) {
        super(props); 
        
        this.state = {event: null}
    };
    
    render() {
        return(
            <div> 
                <div className="map_section">
                    <Map />
                </div>
                <MapUserLegend />
            </div>
        );
    };  
    
   /**
    * Once the mapsection component mounts, populate the map event using an ajax call
    */
    componentDidMount() {
        let event = null;
        //axios.get(`http://localhost/api/event?event_id={this.props.eventId}`)
        axios.get(`http://localhost/api/event?event_id=1`)
        .then(res => { 
            let jsonObj = JSON.parse(res.data.entity);
            let event = jsonObj.payload;
            event.userEvents.map((userEvent) => {
                if (! userEvent.hasOwnProperty('shown'))
                    userEvent.shown = true;
            });
            // MCD TODO make a call to handle the response status
            HashStore.addValue("event", jsonObj.payload);
        }).catch(e => {
            console.error(e);
            console.log(JSON.stringify(e));
            // MCD TODO Handle error - coordinate with servlet to get better error object
        });
    };
};




