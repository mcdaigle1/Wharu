import React from "react";

import { MapUserLegendItem } from "./MapUserLegendItem";
import HashStore from "../stores/HashStore";

export class MapUserLegend extends React.Component {
    constructor(props) {
        super(props); 
        
        this.getEventFromStore = this.getEventFromStore.bind(this);
        this.state = {event: this.props.event};
    };
    
    render() {
        if(this.state.event == null) {
            return null;
        } else {
            return(
                <div className={"map_user_legend"} id={"map_user_legend"}>
                    <div className={"main_list_title_medium"}>Participants</div>
                    {this.state.event.userEvents.map((userEvent, index) => (
                        <MapUserLegendItem key={index} userEventId={userEvent.id}/>
                    ))}
                </div>
            );
        }
    };  
    
    componentWillMount() {
        HashStore.on("event", this.getEventFromStore);
    };
    
    componentWillUnmount() {
        HashStore.removeListener("event", this.getEventFromStore);
    };
    
    getEventFromStore() {
        this.setState({event: HashStore.getValue("event")});
    };
    
    
};