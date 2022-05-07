import React, {useEffect, useState} from 'react';
import '../index.css';
import '../Admin/AdminView.css';
import {Link, useLocation, useNavigate} from "react-router-dom";
import Restaurants from "./Restaurants";
import Menu from "./Menu";


function CustomerView() {

    const navigate = useNavigate()

    const [menu, setMenu] = useState()
    const [restaurantC, setRestaurant] = useState()

    return (
        <div>
            <div className="split left">
                <Restaurants setRestaurant={setRestaurant} setMenu={setMenu}/>
            </div>

            <div className="split right">
                <button className="form-button" onClick={() => navigate('/cart')}> Cart </button>
                <button className="form-button" onClick={() => navigate('/orders')}> Orders </button>
                <a href="http://localhost:8082/logout">
                    <button className="form-button"> LogOut </button>
                </a>
                <Menu restaurant={restaurantC} menu={menu}/>
            </div>
        </div>)
}

export default CustomerView;