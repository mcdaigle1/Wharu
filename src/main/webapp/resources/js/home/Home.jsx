import React from "react";
import { render } from "react-dom";
import { Link } from "react-router-dom";

export class Home extends React.Component {
    
    render() {
        return (       
            <div> 
                <div>Welcome Home!</div> 
                <Link to={"/mapSection"}>mapSection</Link>
            </div>       
        )
    };
}