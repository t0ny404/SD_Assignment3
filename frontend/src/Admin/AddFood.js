import React, { useState } from 'react';
import '../index.css';

function AddFood({admin}) {
    const [category, setCategory] = useState()
    const [name, setName] = useState();
    const [description, setDescription] = useState();
    const [price, setPrice] = useState();


    const handleAdd = async e => {
        e.preventDefault();
        let credentials = {
            name,
            category,
            description,
            price,
            admin
        }
        fetch('http://localhost:8082/menu/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credentials)
        })
            .then(data => data.json())
            .then(data => {
                if (data.severity === "FAILURE") {
                    alert(data.message)
                } else {
                    alert("Success!")
                }
            })

    }

    return(
        <div className="init">
            <form onSubmit={handleAdd}>
                <h1>Add food</h1>
                <select className="form-select" defaultValue="BREAKFAST" onChange={e => setCategory(e.target.value)}>
                    <option value="BREAKFAST"> Breakfast </option>
                    <option value="LUNCH"> Lunch </option>
                    <option value="SOUP"> Soup </option>
                    <option value="DESSERT"> Dessert </option>
                </select>
                <label>
                    <p>Name</p>
                    <input className="form-input" type="text" onChange={e => setName(e.target.value)}/>
                </label>
                <label>
                    <p>Description</p>
                    <input className="form-input" type="text" onChange={e => setDescription(e.target.value)}/>
                </label>
                <label>
                    <p>Price</p>
                    <input className="form-input" type="number" onChange={e => setPrice(e.target.value)}/>
                </label>
                <div>
                    <button className="form-button" type="submit">Add</button>
                </div>
            </form>
        </div>
    )
}

export default AddFood;