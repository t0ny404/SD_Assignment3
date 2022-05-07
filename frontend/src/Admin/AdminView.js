import React, {useEffect, useState} from 'react';
import './AdminView.css';
import AddRestaurant from "./AddRestaurant";
import AddFood from "./AddFood";
import Menu from "./Menu";
import {useLocation, useNavigate} from "react-router-dom";
import Orders from "./Orders";
import GetCurrent from "../GetCurrent";


function AdminView() {

    const [auth, setAuth] = useState(false)
    const [user, setUser] = useState({id: -1, type: '', restaurant: false})

    const navigate = useNavigate()

    useEffect(() => {
        GetCurrent(setAuth, setUser, navigate)
    }, [])

    if (auth && user.type === 'Admin') return (
        <div>
            <div className="split left">
                {(!user.restaurant) && <div><AddRestaurant admin={user.id}/></div>}
                <AddFood admin={user.id}/>
                <Menu restaurant={user.restaurant}/>
            </div>

            <div className="split right">
                <Orders restaurant={user.restaurant}/>
                <a href="http://localhost:8082/logout">
                    <button className="form-button"> LogOut </button>
                </a>
            </div>
        </div>)
}

export default AdminView;