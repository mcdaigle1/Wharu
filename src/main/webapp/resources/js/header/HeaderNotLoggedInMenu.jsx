import React from "react";
import { render } from "react-dom";

import { LoginLink} from "./LoginLink"

export class HeaderNotLoggedInMenu extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="header_menu"> 
                <LoginLink />
            </div>
        );
    };
}


