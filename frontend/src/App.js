import logo from './foodpanda.png';
import './App.css';
import React, {Component, useState} from "react";
import Login from "./Login";
import {NavLink} from "react-router-dom";


function setToken(userToken) {
    sessionStorage.setItem('token', JSON.stringify(userToken));
}

function getToken() {
    const tokenString = sessionStorage.getItem('token');
    const userToken = JSON.parse(tokenString);
    return userToken?.token
}

class App extends Component {
    render() {
        const token = getToken();

        if (!token) {
            return (
                <div className="App">
                    <header className="App-header">
                        <img src={logo} className="App-logo" alt="logo"/>
                        <h1>
                            Food Panda v2
                        </h1>
                        <Login setToken={setToken}/>
                        <NavLink to={"/signup"}><button className="form-button"> SignUp </button></NavLink>
                    </header>
                </div>
            );
        }

        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <h1>
                        Food Panda v2
                    </h1>
                </header>
            </div>
        );
    }
}

export default App;
