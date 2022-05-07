import React, {Component, useEffect, useState} from "react";
import '../index.css';
import {useLocation, useNavigate} from "react-router-dom";
import GetCurrent from "../GetCurrent";

function Cart() {

    const [auth, setAuth] = useState(false)
    const [user, setUser] = useState({id: -1, type: ''})

    const [foods, setFoods] = useState()
    const [total, setTotal] = useState()

    const navigate = useNavigate()

    useEffect(() => {
        const getFoods = async () => {
            fetch('http://localhost:8082/cart/all', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                credentials: "include"
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
                },
                credentials: "include"
            })
                .then(data => data.json())
                .then(data => {
                    setTotal(data)
                })
        }

        GetCurrent(setAuth, setUser, navigate);
        getFoods();
        getTotal();
    }, [])

    const order = async () => {
        const address = prompt("Enter address")
        const details = prompt("Additional details")

        let id = user.id
        let o = {
            id,
            address,
            details
        }
        fetch('http://localhost:8082/order/order', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(id),
                credentials: "include"
            })
                .then(() => {
                    alert("Order of: " + total.toString() + " placed!")
                    setFoods(null)
                    navigate('/orders')
                })
        fetch('http://localhost:8082/order/email', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(o),
            credentials: "include"
        })
    }

    if (auth && user.type === 'Customer') return (
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