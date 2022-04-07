import React, { useState } from 'react';
import PropTypes from 'prop-types';
import './SignUp.css';
import {Link, NavLink, useNavigate} from "react-router-dom";

async function registerUser(credentials) {
    return fetch('http://localhost:8082/user/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials)
    })
        .then(data => data.json())
        .then(data => {
        if (data.severity === "FAILURE") {
            alert(data.message)
        } else {
            alert("Success!")
            useNavigate()('/')
        }
    })
}

function SignUp() {
    const [username, setUserName] = useState();
    const [password, setPassword] = useState();
    const [checkpswd, setCheckpswd] = useState();
    const [name, setName] = useState();
    const [email, setEmail] = useState();
    const [age, setAge] = useState();

    let navigate = useNavigate()

    const handleRegister = async e => {
        e.preventDefault();
        const token = await registerUser({
            username,
            password,
            checkpswd,
            name,
            email,
            age
        });
    }

    return(
        <div className="register">
            <form className="register-form" onSubmit={handleRegister}>
                <label>
                    <p>Username</p>
                    <input className="form-input" type="text" onChange={e => setUserName(e.target.value)}/>
                </label>
                <label>
                    <p>Password</p>
                    <input className="form-input" type="password" onChange={e => setPassword(e.target.value)}/>
                </label>
                <label>
                    <p>Check Password</p>
                    <input className="form-input" type="password" onChange={e => setCheckpswd(e.target.value)}/>
                </label>
                <label>
                    <p>Name</p>
                    <input className="form-input" type="text" onChange={e => setName(e.target.value)}/>
                </label>
                <label>
                    <p>E-mail</p>
                    <input className="form-input" type="text" onChange={e => setEmail(e.target.value)}/>
                </label>
                <label>
                    <p>Age</p>
                    <input className="form-input" type="number" onChange={e => setAge(e.target.value)}/>
                </label>
                <div>
                    <button className="form-button" type="submit">Register</button>
                </div>
            </form>
        </div>
    )
}

export default SignUp;