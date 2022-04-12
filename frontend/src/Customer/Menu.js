import React, {Component, useEffect, useState} from "react";
import '../index.css';
import {useNavigate} from "react-router-dom";

function Menu({restaurant, menu}) {

    const [hide, setHide] = useState()
    const [current, setCurrent] = useState()

    let total = 0;

    const addToCart = async (f) => {
        fetch('http://localhost:8082/cart/' + restaurant.toString() + '/add', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(f)
        })
            .then(() => {
                total += f.price
                alert("Total: " + total.toString())
                if (!hide) {
                    setHide(true)
                    setCurrent(restaurant)
                }
            })
    }

    return (<div>
            <h1> Menu </h1>
            {
                (!hide || current === restaurant) && <div>
                    {
                        menu?.map((f, i) => (
                            <div key={i}>
                                <label>
                                    <h2> {f.category}: {f.name} </h2>
                                    <p> {f.description} </p>
                                    <h3> {f.price}
                                        <button className="form-button" onClick={() => addToCart(f)}> Add to
                                            cart
                                        </button>
                                    </h3>
                                </label>
                            </div>
                        ))
                    } </div>
            }
        </div>
    )
}

export default Menu;