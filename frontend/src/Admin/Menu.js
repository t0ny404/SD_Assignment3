import React, {Component, useState} from "react";
import { saveAs } from 'file-saver';
import '../index.css';

function Menu({restaurant}) {
    const [menu, setMenu] = useState()

    const getFood = async e => {
        e.preventDefault()
        fetch('http://localhost:8082/menu/all', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: "include"
        })
            .then(data => data.json())
            .then(data => {
                setMenu(data)
            })
    }

    const getPDF = async () => {
        let httpClient = new XMLHttpRequest();
        let pdfLink = 'http://localhost:8082/menu/pdf/' + restaurant;
        httpClient.open('get', pdfLink, true);
        httpClient.withCredentials = true;
        httpClient.responseType = "blob";
        httpClient.onload = function() {
            const file = new Blob([httpClient.response], { type: 'application/pdf' });
            const fileURL = URL.createObjectURL(file);
            const link = document.createElement("a");
            link.href = fileURL;
            link.download = "menu.pdf";
            link.click();
            URL.revokeObjectURL(fileURL);
        };
        httpClient.send();
    }

    if (menu) return (
            <div>
                <button className="form-button" onClick={getFood}> View Menu </button>
                <button className="form-button" onClick={getPDF}> Menu PDF </button>
                <h1> Menu </h1>
                {
                    Object.keys(menu).map((c, m) => (
                        <div key={m}>
                        <h2> {c} </h2>
                            {
                                menu[c].map(f => (
                                    <div>
                                        <h3> {f} </h3>
                                    </div>
                                ))
                            }
                        </div>
                    ))
                }
            </div>
        )
    else return (
        <div>
            <button className="form-button" onClick={getFood}> View Menu </button>
            <button className="form-button" onClick={getPDF}> Menu PDF </button>
        </div>
    )
}

export default Menu;
