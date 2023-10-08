import React, { useEffect, useState } from "react";
import SearchBar from "./components/SearchBar";
import supermarket from "../assets/supermarket.png";
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import ProductComponent from "./components/ProductComponent";

export default function LoggedIn() {
    const navigate = useNavigate();

    const [products, setProducts] = useState([]);
    useEffect(() => {
        axios.get(`http://localhost:8080/product?name=`)
            .then(response => {
                const randomProducts = getRandomProducts(response.data, 3);
                setProducts(randomProducts);
            })
            .catch(error => {
                console.error("There was an error fetching products for the subcategory:", error);
            });
    }, []);


    const getRandomProducts = (array, num) => {
        const shuffled = array.sort(() => 0.5 - Math.random());
        return shuffled.slice(0, num);
    };

    const routeChange = () => {
        let path = `/categories`;
        navigate(path);
    }


    return (
        <>
            < div className="home-page-search-categories" >
                <button class="default-home-button" role="button" onClick={routeChange} > Categories</button>
                <SearchBar />
            </div >
            <img
                src={supermarket}
                className="main-page-image"
            />
            <h1>Popular Items</h1>
            <div className="specials-container">
                {products.map(product => (
                    <ProductComponent product={product} />
                ))}

            </div>
        </>

    );
}