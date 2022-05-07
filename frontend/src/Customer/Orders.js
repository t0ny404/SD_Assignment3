import React, {Component, useEffect, useState} from "react";
import '../index.css';
import {useLocation, useNavigate} from "react-router-dom";
import GetCurrent from "../GetCurrent";

function Orders() {

    const [auth, setAuth] = useState(false)
    const [user, setUser] = useState({id: -1, type: ''})

    const [history, setHistory] = useState()
    const [pending, setPending] = useState()

    const navigate = useNavigate()

    useEffect(() => {
        const getHistory = (id) => {
            fetch('http://localhost:8082/order/history/' + id, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                credentials: "include"
            })
                .then(data => data.json())
                .then(data => {
                    setHistory(data)
                })
        }
        const getPending = (id) => {
            fetch('http://localhost:8082/order/pending/' + id , {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                credentials: "include"
            })
                .then(data => data.json())
                .then(data => {
                    setPending(data)
                })
        }
        GetCurrent(setAuth, setUser, navigate)
            .then(user => {
            getPending(user.id);
            getHistory(user.id);
        })
    }, [])


    if (auth && user.type === 'Customer') return (
        <div>
            <div className="split left">
                <h1> Pending </h1>
                {
                pending?.map((f, i) => (
                    <div key={i}>
                        <h2> Restaurant: {f.rName} </h2>
                        <p> {f.date} --- {f.status} </p>
                        <h3> price: {f.price} </h3>
                    </div>
                ))
                }
            </div>
            <div className="split right">
                <h1> History </h1>
                {
                    history?.map((f, i) => (
                        <div key={i}>
                            <h2> Restaurant: {f.rName} </h2>
                            <p> {f.date} --- {f.status} </p>
                            <h3> price: {f.price} </h3>
                        </div>
                    ))
                }
            </div>
        </div>
    )
}

export default Orders;