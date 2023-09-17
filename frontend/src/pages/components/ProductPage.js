import React from "react";
import { useLocation } from "react-router-dom";

export default function ProductPage() {

    const location = useLocation();

    return (
        <h1>Product template here of :{location.state.productID}</h1>
    )
}