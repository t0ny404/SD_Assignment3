import React, {Component, useEffect, useState} from "react";
import '../index.css';
import {useLocation, useNavigate} from "react-router-dom";

function Cart() {

    const {state} = useLocation();
    const {user} = state

    const [foods, setFoods] = useState()
    const [total, setTotal] = useState()

    useEffect(() => {
        const getFoods = async () => {
            fetch('http://localhost:8082/cart/all', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(data => data.json())
                .then(data => {
                    setFoods(data)
                })
        }
        const getTotal = async () => {
            fetch('http://localhost:8082/cart/total', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(data => data.json())
                .then(data => {
                    setTotal(data)
                })
        }
        getFoods();
        getTotal();
    }, [])

    const navigate = useNavigate()

    const order = async () => {
        fetch('http://localhost:8082/order/order', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(user)
            })
                .then(() => {
                    alert("Order of: " + total.toString() + " placed!")
                    setFoods(null)
                    navigate('/orders', {state: {user}})
                })
    }

    return (
        <div>
            {
             foods?.map((f, i) => (
                 <div key={i}>
                     <h2> {f.category}: {f.name} </h2>
                     <p> {f.description} </p>
                     <h3> price: {f.price}, quantity: {f.quantity} </h3>
                 </div>
             ))
            }
            <div>
                <h1> TOTAL:  {total} </h1>
                <button className="form-button" onClick={order}> Order </button>
            </div>
        </div>
    )

}

export default Cart;