import React from "react"
import Shoes from "../../assets/docmartens.jpg"

export default function ShoeView({name, description, price, imagePath}) {
    return (
        <div className="grid-item">
            <img src={imagePath} alt={name}/>
            <h2>{name}</h2>
            <p>{description}</p>
            <p>£{price}</p>
        </div>
    )
}