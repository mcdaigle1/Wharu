import React from "react";
import { render } from "react-dom";

import { EventsMenuItem} from "./EventsMenuItem"
import { AccountMenuItem} from "./AccountMenuItem"

export class HeaderLoggedInMenu extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="header_menu">    
                <EventsMenuItem />
                <AccountMenuItem />
            </div>
        );
    };
}
                        