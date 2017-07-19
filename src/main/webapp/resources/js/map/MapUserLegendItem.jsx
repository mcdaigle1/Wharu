import React from "react";

import HashStore from "../stores/HashStore";

export class MapUserLegendItem extends React.Component {
    constructor(props) {
        super(props);
        
        let userEvent = this.getUserEventFromStoredEvent();
        if (userEvent.shown == null) 
            userEvent.shown = true;
        this.state = {userEvent: userEvent, forceEvent: 0}
    }
    
    render() {
        let userEvent = this.state.userEvent;
        let checkedValue = this.state.userEvent.shown ? "checked" : "";
        let fontStyle = {
            color: "#" + userEvent.color,
        };
             
        return(
            <div className={"main_list_row"} 
                    id={"user_legend_item_" + userEvent.id}
                    onClick={this.toggleUserShown.bind(this)}  >
                <div className={"main_list_field_small"}>
                    <img src={userEvent.shown ? "images/checkbox_selected.png" : "images/checkbox_unselected.png"} />
                </div>
                <div className={"main_list_field_medium"} style={fontStyle}>{userEvent.user.displayName}</div>
            </div>
        )
    }
    
    toggleUserShown() {
        let event = HashStore.getValue("event");
        if (event == null) return null;
        let matchedUserEvent = this.getUserEventFromStoredEvent();
        let shownUserEvents = this.getShownUserEventsFromStoredEvent();
        if (this.state.userEvent.shown == true && shownUserEvents.length == 1) {
            alert("You must have at least one participant shown.");
            this.setState({forceEvent: this.state.forceEvent++});
        } else {
            matchedUserEvent.shown = !this.state.userEvent.shown;
            HashStore.addValue("event", event);
        }
    }
    
    getUserEventFromStoredEvent() {
        let event = HashStore.getValue("event");
        if (event == null) return null;
        
        let matchingUserEvents = event.userEvents.filter(userEvent => { 
            if (userEvent.id == this.props.userEventId) 
                return userEvent
         });
        return matchingUserEvents[0];
    }
    
    getShownUserEventsFromStoredEvent() {
        let event = HashStore.getValue("event");
        if (event == null) return null;
        
        return event.userEvents.filter(userEvent => { 
            if (userEvent.shown) 
                return userEvent
         });
    }
}