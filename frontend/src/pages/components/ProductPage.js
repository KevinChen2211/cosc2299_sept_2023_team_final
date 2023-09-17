import React from "react";
import { useLocation } from "react-router-dom";

export default function ProductPage() {

    const location = useLocation();

    console.log(location.state.productName);
    return (
        <h1>Product template here of :{location.state.productName}</h1>
    )
}