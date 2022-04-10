import React, {useEffect, useState} from 'react';
import '../index.css';
import {Link, useLocation} from "react-router-dom";
import Restaurants from "./Restaurants";
import Menu from "./Menu";


function CustomerView() {

    const {state} = useLocation();
    const {id, name, type, restaurant} = state;

    const [menu, setMenu] = useState()

    return (
        <div>
            <div className="split left">
               <Restaurants menu={menu} setMenu={setMenu}/>
            </div>

            <div className="split right">
                <Menu menu={menu} setMenu={setMenu}/>
            </div>
        </div>)
}

export default CustomerView;