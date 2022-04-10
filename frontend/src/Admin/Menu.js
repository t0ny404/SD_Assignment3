import React, {Component, useState} from "react";
import '../index.css';

function Menu() {
    const [menu, setMenu] = useState()

    const getFood = async e => {
        e.preventDefault()
        fetch('http://localhost:8082/menu/all', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(data => data.json())
            .then(data => {
                setMenu(data)
            })
    }

    if (menu) return (
            <div>
                <button className="form-button" onClick={getFood}> View Menu </button>
                <h1> Menu </h1>
                {
                    Object.keys(menu).map((c, m) => (
                        <div key={m}>
                        <h2> {c} </h2>
                            {
                                menu[c].map(f => (
                                    <div>
                                        <h3> {f} </h3>
                                    </div>
                                ))
                            }
                        </div>
                    ))
                }
            </div>
        )
    else return (
        <div>
            <button className="form-button" onClick={getFood}> View Menu </button>
        </div>
    )
}

export default Menu;
