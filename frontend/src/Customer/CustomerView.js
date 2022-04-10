import React, {useEffect, useState} from 'react';
import '../index.css';
import {Link, useLocation} from "react-router-dom";
import Restaurants from "./Restaurants";
import Menu from "./Menu";


function CustomerView() {

    const {state} = useLocation();
    const {id, name, type, restaurant} = state;

    const [menu, setMenu] = useState()
    const [restaurantC, setRestaurant] = useState()

    return (
        <div>
            <div className="split left">
               <Restaurants setRestaurant={setRestaurant} setMenu={setMenu}/>
            </div>

            <div className="split right">
                <Menu restaurant={restaurantC} menu={menu}/>
            </div>
        </div>)
}

export default CustomerView;