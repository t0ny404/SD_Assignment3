import React, {Component, useEffect, useState} from "react";
import '../index.css';

function Menu({menu, setMenu}) {

    return (
        <div>
            <h1> Menu </h1>
            {
                menu?.map((f, i) => (
                    <div key={i}>
                        <h2> {f} </h2>
                    </div>
                ))
            }
        </div>
    )
}

export default Menu;