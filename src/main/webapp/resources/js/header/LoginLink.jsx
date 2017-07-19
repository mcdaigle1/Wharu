import React from "react";
import { render } from "react-dom";
import { Link } from "react-router-dom";

import HashStore from "../stores/HashStore";

export class LoginLink extends React.Component {
    constructor(props) {
        super(props);
        
        this.getLoginPageFromStore = this.getLoginPageFromStore.bind(this);
        this.state = {onLoginPage: false};
    }
    
    render() {
        return(
            <div className="header_account_menu_item">
                { this.state.onLoginPage
                    ? <span>Log In</span>
                    : <Link to={"/userLogin"}>Log In</Link>
                }
            </div>
        );
    }
    
    componentWillMount() {
        HashStore.on("loginPage", this.getLoginPageFromStore);
    };
    
    componentWillUnmount() {
        HashStore.removeListener("loginPage", this.getLoginPageFromStore);
    }
    
    getLoginPageFromStore() {
        this.setState({loginInfo: HashStore.getValue("loginPage")});
    }
}