import React from 'react';
import { render } from 'react-dom';
import axios from 'axios';
import jwt from 'jsonwebtoken';

import { UserPasswordLogin } from "./UserPasswordLogin";
import { UserFacebookLogin } from "./UserFacebookLogin";
import HashStore from '../stores/HashStore';

export class UserLogin extends React.Component {
	constructor(props) {
	    super(props);    
	};
    
    render() {
	    return (
	        <div className="user_login_section">  
	            <UserFacebookLogin />
	            <div className="user_login_or">- or -</div>
	            <UserPasswordLogin />
	        </div>
	    );
	};
	
	onSubmitLogin() {
	    alert("onSubmitLogin Clicked!");
	};
	
    componentWillMount() {
        HashStore.on("loginInfo", this.getUserRecord);
    };
	
	componentDidMount() {
	    HashStore.addValue("loginPage", true);
	};
	
    componentWillUnmount() {
        HashStore.addValue("loginPage", false); 
        HashStore.removeListener("loginInfo", this.getUserRecord);
    };
    
    getUserRecord() {
        let userEmail = HashStore.getValue("loginInfo").email;
        
        axios.get("http://localhost/api/user_by_email?user_email=" + userEmail)
        .then(res => { 
            let jsonObj = JSON.parse(res.data.entity);
            // MCD TODO make a call to handle the response status
            HashStore.addValue("user", jsonObj.payload);
        }).catch(e => {
            console.error(e);
            console.log(JSON.stringify(e));
            // MCD TODO Handle error - coordinate with servlet to get better error object
        });
    }
}


