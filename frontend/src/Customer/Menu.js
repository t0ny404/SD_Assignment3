import React, {Component, useEffect, useState} from "react";
import '../index.css';

function Menu({restaurant, menu}) {

    const [hide, setHide] = useState()
    const [current, setCurrent] = useState()

    const addToCart = async (id) => {
        fetch('http://localhost:8082/cart/' + restaurant.toString() + '/add/' + id.toString(), {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(() => {
                setHide(true)
                setCurrent(restaurant)
            })
    }

    return ((!hide || current === restaurant) && <div>
            <button className="form-button"> Cart </button>
            <h1> Menu </h1>
            {
                menu?.map((f, i) => (
                    <div key={i}>
                        <label>
                            <h2> {f.category}: {f.name} </h2>
                            <p> {f.description} </p>
                            <h3> {f.price} <button className="form-button" onClick={() => addToCart(f.id)}> Add to cart </button> </h3>
                        </label>
                    </div>
                ))
            }
        </div>
    )
}

export default Menu;