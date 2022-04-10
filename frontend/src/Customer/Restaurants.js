import React, {Component, useEffect, useState} from "react";
import '../index.css';

function Restaurants({setRestaurant, setMenu}) {
    const [rName, setRName] = useState()
    const [restaurants, setRestaurants] = useState()

    useEffect(() => {
        const getRestaurants = async () => {
            fetch('http://localhost:8082/restaurant/all', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(data => data.json())
                .then(data => {
                    setRestaurants(data)
                })
        }
        getRestaurants();
    }, [])

    const searchByName = async () => {
        fetch('http://localhost:8082/restaurant/' + rName, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(data => data.json())
            .then(data => {
                setRestaurants(data)
            })
    }


    const getMenu = async (id) => {
        fetch('http://localhost:8082/menu/' + id.toString(), {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(data => data.json())
            .then(data => {
                setMenu(data)
                setRestaurant(id)
            })
    }

    return (
        <div>
            <label>
                <input className="form-input" type="text" onChange={e => setRName(e.target.value)}/>
                <button className="form-button" onClick={searchByName}> Search </button>
            </label>

            <h1> Restaurants </h1>
            {
                restaurants?.map((r, i) => (
                    <div key={i}>
                        <button onClick={() => getMenu(r.id)}> <h2> {r.name} </h2> </button>
                        <h3> Address: {r.location} </h3>
                        <h4> Available delivery zones: {r.zones} </h4>
                    </div>
                ))
            }
        </div>
    )
}

export default Restaurants;