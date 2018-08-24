import React from "react";
import { render } from "react-dom";
import { Link } from "react-router-dom";
import axios from 'axios';

import HashStore from "../stores/HashStore";
import { EventItem } from "./EventItem";

/**
 * Events section component
 */
export class Events extends React.Component {
    constructor(props) {
        super(props);
        
        this.getUserEvents = this.getUserEvents.bind(this);
               
        this.state = {userEvents: []};
    }
    
    render() {
        console.log("UserEvents: " + JSON.stringify(this.state.userEvents));
        return (       
            <div className="events_section"> 
                <div>Your Events</div>
                    {this.state.userEvents.map((userEvent, index) => (
                        <EventItem key={index} userEvent={userEvent}/> 
                    ))}
            </div>       
        )
    }
    
    componentWillMount() {
        HashStore.on("user", this.getUserEvents);
    }
    
    componentWillUnmount() {
        HashStore.removeListener("user", this.getUserEvents);
    }
    
    componentDidMount() {
        if (HashStore.getValue("user") != null)
            this.getUserEvents();
    }
    
    getUserEvents() {
        let user = HashStore.getValue("user");
        axios.get("http://localhost/api/user_events_by_user?user_id=" + user.id)
        .then(res => { 
            let jsonObj = JSON.parse(res.data.entity);
            // MCD TODO make a call to handle the response status
            console.log("jsonObj: " + JSON.stringify(jsonObj.payload));
            this.setState({userEvents: jsonObj.payload});
        }).catch(e => {
            console.error(e);
            console.log(JSON.stringify(e));
            // MCD TODO Handle error - coordinate with servlet to get better error object
        });
    }
}