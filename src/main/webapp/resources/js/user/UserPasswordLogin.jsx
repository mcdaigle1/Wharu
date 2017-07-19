import React from 'react';
import { render } from 'react-dom';
import axios from 'axios'; 

import HashStore from '../stores/HashStore';

export class UserPasswordLogin extends React.Component {
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
            <div className="user_password_login_section"> 
                <div className="user_login_list_title">Log in with email / password</div>
                <div className="user_login_list_row">
                    <div className="main_list_field_medium_large">User Email: </div>
                    <input className="main_list_field_large" type="text" value={this.state.identifier} onChange={this.onLoginEmailChange} />
                </div>
                <div className="user_login_list_row">       
                    <div className="main_list_field_medium_large">User Password: </div>
                    <input className="main_list_field_large" type="password" onChange={this.onLoginPasswordChange} />
                </div>
                <div className="user_login_submit_button">
                    <button type="submit" onClick={this.onSubmitLogin}>Log In</button>
                </div>    
            </div>
        );
    }
}