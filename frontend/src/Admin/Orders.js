import React, {Component, useEffect, useState} from "react";
import '../index.css';
import {useLocation} from "react-router-dom";

function Orders({restaurant}) {

    const [status, setStatus] = useState()
    const [orders, setOrders] = useState()

    const getOrders = async () => {
        fetch('http://localhost:8082/order/all/' + restaurant.toString(), {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: "include"
        })
            .then(data => data.json())
            .then(data => {
                setOrders(data)
            })
    }

    useEffect(() => {
        getOrders();
    }, [])

    const filter = async () => {
        if (status === "ALL")
            getOrders()
        else fetch('http://localhost:8082/order/all/' + restaurant.toString() + '/filter/' + status.toString(), {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: "include"
        })
            .then(data => data.json())
            .then(data => {
                setOrders(data)
            })
    }

    const changeS = async (id, s) => {
        console.log(id, s)
        fetch('http://localhost:8082/order/change/' + id.toString() + '/' + s, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: "include"
        })
            .then(getOrders)
    }

    return (
        <div>
            <label>
                <select className="form-select" defaultValue="ALL" onChange={e => setStatus(e.target.value)}>
                    <option value="ALL"> All </option>
                    <option value="PENDING"> Pending </option>
                    <option value="ACCEPTED"> Accepted </option>
                    <option value="DECLINED"> Declined </option>
                    <option value="IN_DELIVERY"> In delivery </option>
                    <option value="DELIVERED"> Delivered </option>
                </select>
                <button className="form-button" onClick={filter}> Filter </button>
            </label>

            {
                orders?.map((f, i) => (
                    <div key={i}>
                        <h2> ClientId: {f.cId} </h2>
                        <p> {f.date} --- {f.status} </p>
                        <h3> price: {f.price} </h3>
                        {
                            (f.status === 'PENDING') && <div>
                                <button className="form-button" onClick={() => changeS(f.id, 'ACCEPTED')}> Accept
                                </button>
                                <button className="form-button" onClick={() => changeS(f.id, 'DECLINED')}> Decline
                                </button>
                            </div>
                        }{
                            (f.status === 'ACCEPTED') && <div>
                                <button className="form-button" onClick={() => changeS(f.id, 'IN_DELIVERY')}> Change Status </button>
                            </div>
                        }{
                            (f.status === 'IN_DELIVERY') && <div>
                                <button className="form-button" onClick={() => changeS(f.id, 'DELIVERED')}> Change Status </button>
                            </div>
                        }
                    </div>
                ))
            }
        </div>
    )
}

export default Orders;