import React from 'react';
import { render } from 'react-dom';
import { Link } from "react-router-dom";

import '../../css/header.css';

export class Logo extends React.Component {
    render() {
        return (
            <div className="header_logo_section">    
                <Link to={"/home"}><img className={"header_logo"} src={"images/wharu_logo.png"} /></Link>
            </div>
        );
    }
}