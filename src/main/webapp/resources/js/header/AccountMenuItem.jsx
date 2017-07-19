import React from "react";
import {render} from "react-dom";

import { AccountMenuDropdown } from "./AccountMenuDropdown";
import HashStore from "../stores/HashStore";

export class AccountMenuItem extends React.Component {
    constructor(props) {
        super(props);
         
        this.getLoginInfoFromStore = this.getLoginInfoFromStore.bind(this);
        this.state = { 
            loginInfo: HashStore.getValue("loginInfo"),
            dropdownShown: false
        }
    }
    
    render() {
        return (
            <div className="header_account_menu_item" 
                    onClick={this.toggleDropdown.bind(this)}>
                <div>{this.state.loginInfo.email}</div>
                {this.state.dropdownShown
                    ? <AccountMenuDropdown />
                    : null
                }
            </div>
        )
    }
       
    getLoginInfoFromStore() {
        this.setState({loginInfo: HashStore.getValue("loginInfo")});
    }
    
    toggleDropdown() {
        this.setState({dropdownShown: !this.state.dropdownShown});
    }
    
    showDropdown() {
        if (!this.timeoutId) {
            this.timeoutId = window.setTimeout(() => {
                this.timeoutId = null;
                this.setState({dropdownShown: true});
           }, 1000);
        }
    }
    
    hideDropdown() {
        if (this.timeoutId) {
            window.clearTimeout(this.timeoutId);
            this.timeoutId = null;
        } else {
            this.setState({dropdownShown: false});
        }
    }
}