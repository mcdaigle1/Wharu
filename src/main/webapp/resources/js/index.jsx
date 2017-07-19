import React from "react";
import { render } from "react-dom";
import { BrowserRouter as Router, Route, Switch, browserHistory } from "react-router-dom";

import { Header } from "./header/Header"
import { Home } from "./home/Home";
import { MapSection } from "./map/MapSection";
import { UserLogin } from "./user/UserLogin";
import { Events } from "./event/Events";
import HashStore from "./stores/HashStore";

class App extends React.Component {
    
    constructor(props) {
        super(props);
        
        HashStore.recover();
    }
    
    render() {
        return (      
           <Router>
                <div className="main_app_section">    
                    <Header />  
                    <Switch>
                        <Route exact path={"/"} component={Home} />
                        <Route path={"/home"} component={Home} />
                        <Route path={"/userLogin"} component={UserLogin} />
                        <Route path={"/mapSection"} component={MapSection} />
                        <Route path={"/events"} component={Events} />
                        <Route component={Home}/>
                    </Switch>
                </div>
           </Router>
        );
    };
};

// new UserLogin().setAuthorizationToken();

ReactDOM.render(<App />, document.getElementById('app_section'));

