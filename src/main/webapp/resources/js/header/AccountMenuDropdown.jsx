import React from "react";
import {render} from "react-dom";
import { browserHistory } from "react-router-dom";

import HashStore from "../stores/HashStore";

export class AccountMenuDropdown extends React.Component {
    constructor(props) {
        super(props);
         
        var timeoutId = null;
    }
    
    render() {
        return (
            <div className="header_account_info_dropdown_menu">
                <div className="header_account_info_dropdown_row" onClick={this.logUserOut.bind(this)}>log out</div>
            </div>
        )
    }
    
    logUserOut() {
        this.setState({dropdownShown: false});
        HashStore.removeValue("loginInfo");
        this.context.router.history.push("/home");
    }
}

AccountMenuDropdown.contextTypes = {
    router: React.PropTypes.func.isRequired,
}
