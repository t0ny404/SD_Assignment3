import React, { useState } from 'react';
import './index.css';
import {useNavigate} from "react-router-dom";


function Login() {
    const [username, setUserName] = useState();
    const [password, setPassword] = useState();

    let navigate = useNavigate()

    const handleLogIn = async e => {
        e.preventDefault();
        let credentials = {
            username,
            password
        }
        fetch('http://localhost:8082/user/login', {
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
                    if (data.userDTO.type === "Admin") {
                        navigate('/admin', {state: data.userDTO})
                    } else navigate('/customer', {state: data.userDTO})
                }
            })
    }

    return(
        <div className="init">
            <form onSubmit={handleLogIn}>
                <label>
                    <p>Username</p>
                    <input className="form-input" type="text" onChange={e => setUserName(e.target.value)}/>
                </label>
                <label>
                    <p>Password</p>
                    <input className="form-input" type="password" onChange={e => setPassword(e.target.value)}/>
                </label>
                <div>
                    <button className="form-button" type="submit"> LogIn </button>
                </div>
            </form>
        </div>
    )
}

export default Login;