import logo from './foodpanda.png';
import './App.css';
import React, {Component, useState} from "react";
import {NavLink, useNavigate} from "react-router-dom";
import Login from "./Login";

class App extends Component {

    render() {
            return (
                <div className="App">
                    <header className="App-header">
                        <img src={logo} className="App-logo" alt="logo"/>
                        <h1>
                            Food Panda v2
                        </h1>
                        <Login/>
                        <NavLink to={"/signup"}><button className="form-button"> SignUp </button></NavLink>
                    </header>
                </div>
            );
    }
}

export default App;
