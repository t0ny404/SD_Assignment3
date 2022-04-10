import React, { useState } from 'react';
import './AdminView.css';
import AddRestaurant from "./AddRestaurant";
import AddFood from "./AddFood";
import Menu from "./Menu";
import {useLocation} from "react-router-dom";


function AdminView() {

    const {state} = useLocation();
    const {id, name, type, restaurant} = state;

    return (
        <div>
            <div className="split left">
                {!restaurant && <div><AddRestaurant admin={id}/></div>}
                <AddFood admin={id}/>
                <Menu/>
            </div>

            <div className="split right">

            </div>
        </div>);
}

export default AdminView;