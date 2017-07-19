import React from "react";
import { render } from "react-dom";

import { Logo } from "./Logo";
import { HeaderLoggedInMenu} from "./HeaderLoggedInMenu"
import { HeaderNotLoggedInMenu} from "./HeaderNotLoggedInMenu"
import HashStore from "../stores/HashStore";

export class Header extends React.Component {
    constructor(props) {
        super(props);
        
        this.getLoginInfoFromStore = this.getLoginInfoFromStore.bind(this);
        this.state = { loginInfo: HashStore.getValue("loginInfo") }
    };
 
    render() {
        return (
            <div className="header_section">
                <Logo />
                {this.state.loginInfo != null 
                    ? <HeaderLoggedInMenu />
                    : <HeaderNotLoggedInMenu />
                }
            </div>
        );
    };
    
    componentWillMount() {
        HashStore.on("loginInfo", this.getLoginInfoFromStore);
    };
    
    componentWillUnmount() {
        HashStore.removeListener("loginInfo", this.getLoginInfoFromStore);
    }
    
    getLoginInfoFromStore() {
        this.setState({loginInfo: HashStore.getValue("loginInfo")});
    };
    
}