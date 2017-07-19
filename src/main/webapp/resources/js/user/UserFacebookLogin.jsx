import React from 'react';
import { render } from 'react-dom';
import FacebookLogin from 'react-facebook-login';
import axios from 'axios';

import HashStore from '../stores/HashStore';

export class UserFacebookLogin extends React.Component {
    constructor(props) {
        super(props);
        
        this.state = {
                identifier: "",
                password: "",
                errors: {},
                isLoading: {}
        }
    }
    
    render() {
        return (
            <FacebookLogin
                appId="609861372517582"
                autoLoad={false}
                fields="name,email,picture"
                callback={this.facebookResponse.bind(this)}
                cssClass="my-facebook-button-class"
                icon="fa-facebook"
            />
        );
    }
    
    facebookResponse(response) {
        let loginValue = {
            loginType: "facebook",
            name: response.name,
            email: response.email,
            id: response.id,
            token: response.accessToken
        }
        axios.get("http://localhost/api/register_user?user_email=" + loginValue.email + "&user_name=" + loginValue.name)
        .then(res => { 
            let jsonObj = JSON.parse(res.data.entity);
            // MCD TODO make a call to handle the response status
            HashStore.addValue("user", jsonObj.payload);
            HashStore.addValue("loginInfo", loginValue);
            console.log("in fb login, user: " + JSON.stringify(HashStore.getValue("user")));
            this.context.router.history.push("/home");
        }).catch(e => {
            console.error(e);
            console.log(JSON.stringify(e));
            // MCD TODO Handle error - coordinate with servlet to get better error object
        });
    }
}

UserFacebookLogin.contextTypes = {
        router: React.PropTypes.func.isRequired,
}