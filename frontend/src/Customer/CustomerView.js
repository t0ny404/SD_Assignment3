import React, {useEffect, useState} from 'react';
import '../index.css';
import '../Admin/AdminView.css';
import {Link, useLocation, useNavigate} from "react-router-dom";
import Restaurants from "./Restaurants";
import Menu from "./Menu";


function CustomerView() {

    const {state} = useLocation();
    const {id, name, type, restaurant} = state;

    const [menu, setMenu] = useState()
    const [restaurantC, setRestaurant] = useState()

    const navigate = useNavigate()

    return (
        <div>
            <div className="split left">
               <Restaurants setRestaurant={setRestaurant} setMenu={setMenu}/>
            </div>

            <div className="split right">
                <button className="form-button" onClick={() => navigate('/cart', {state: {user: id}})}> Cart </button>
                <button className="form-button" onClick={() => navigate('/orders', {state: {user: id}})}> Orders </button>
                <Menu restaurant={restaurantC} menu={menu}/>
            </div>
        </div>)
}

export default CustomerView;