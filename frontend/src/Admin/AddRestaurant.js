import React, { useState } from 'react';
import '../index.css';

function AddRestaurant({admin}) {
    const [name, setName] = useState();
    const [location, setLocation] = useState();
    const [zones, setZones] = useState();

    const [hide, setHide] = useState()

    const handleAdd = async e => {
        e.preventDefault();
        let credentials = {
            name,
            location,
            zones,
            admin
        }

        fetch('http://localhost:8082/restaurant/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credentials)
        })
            .then(() => {
                setHide(true)
            })
    }

    return(
        !hide && <div className="init">
            <form onSubmit={handleAdd}>
                <h1>Add restaurant</h1>
                <label>
                    <p>Name</p>
                    <input className="form-input" type="text" onChange={e => setName(e.target.value)}/>
                </label>
                <label>
                    <p>Location</p>
                    <input className="form-input" type="text" onChange={e => setLocation(e.target.value)}/>
                </label>
                <label>
                    <p>Delivery zones</p>
                    <input className="form-input" type="text" onChange={e => setZones(e.target.value)}/>
                </label>
                <div>
                    <button className="form-button" type="submit">Add</button>
                </div>
            </form>
        </div>
    )
}

export default AddRestaurant;