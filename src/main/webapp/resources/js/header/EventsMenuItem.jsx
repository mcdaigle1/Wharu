import React from "react";
import {render} from "react-dom";

export class EventsMenuItem extends React.Component {
    constructor(props) {
        super(props);
    }
    
    render() {
        return (
            <div className="header_menu_item">
                <div onClick={this.goToEvents.bind(this)}>EVENTS</div>
            </div>
        )
    }
    
    goToEvents() {
        this.context.router.history.push("/events");
    }
}

EventsMenuItem.contextTypes = {
    router: React.PropTypes.func.isRequired,
}