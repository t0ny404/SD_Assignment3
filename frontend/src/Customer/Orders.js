import React, {Component, useEffect, useState} from "react";
import '../index.css';
import {useLocation} from "react-router-dom";

function Orders() {

    const {state} = useLocation();
    const {user} = state

    const [history, setHistory] = useState()
    const [pending, setPending] = useState()

    useEffect(() => {
        const getHistory = async () => {
            fetch('http://localhost:8082/order/history/' + user.toString(), {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(data => data.json())
                .then(data => {
                    setHistory(data)
                })
        }
        const getPending = async () => {
            fetch('http://localhost:8082/order/pending/' + user.toString(), {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(data => data.json())
                .then(data => {
                    setPending(data)
                })
        }
        getHistory();
        getPending();
    }, [])

    return (
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