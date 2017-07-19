import React from "react";
import { render } from "react-dom";

/**
 * Event line item component
 */
export class EventItem extends React.Component {
    constructor(props) {
        super(props);
        
        this.state = {userEvent: this.props.userEvent};
    };
    
    render() {
        let event = this.state.event;
        return (       
            <div className="event_item"> 
                <div className="main_column_medium">{this.state.userEvent.event.name}</div>
                <div className="main_column_medium">{this.state.userEvent.event.description}</div>
                <div className="main_column_medium">{this.state.userEvent.event.start_time}</div>
                <div className="main_column_medium">{this.state.userEvent.event.end_time}</div>
                <div className="main_column_medium">{this.state.userEvent.event.status}</div>
            </div>       
        );
    };
}