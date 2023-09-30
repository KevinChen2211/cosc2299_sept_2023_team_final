import React, { useEffect, useState } from "react";
import SearchBar from "./components/SearchBar";
import supermarket from "../assets/supermarket.png";
import axios from 'axios';
import { useNavigate } from "react-router-dom";

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

    const handleImageClick = (product, productIdentify) => {
        // Replace with the actual URLs with the navigate 
        navigate("/product/" + product, { state: { productID: productIdentify } });
        return;
    }

    const routeChange = () => {
        let path = `/categories`;
        navigate(path);
    }


    return (
        <>
            < div className="home-page-search-categories" >
                < button onClick={routeChange} > Categories</button >
                <SearchBar />
            </div >
            <img
                src={supermarket}
                className="main-page-image"
            />
            <h1>Popular Items</h1>
            <div className="specials-container">
                {products.map(product => (
                    <div
                        className="home-popular-items"
                        key={product.productID}
                        onClick={() => handleImageClick(product.name, product.productID)}
                    >
                        <img
                            src={product.imageLocation}
                            alt={product.name} width="100"
                        />
                        <br />
                        <strong>{product.name}</strong> - ${product.price.toFixed(2)}
                    </div>
                ))}

            </div>
        </>

    );
}