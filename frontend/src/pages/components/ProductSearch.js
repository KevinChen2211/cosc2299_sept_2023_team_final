import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import SearchBar from "./SearchBar";


export default function ProductSearch() {
    const { searchTerm } = useParams();
    const [searchResults, setSearchResults] = useState([]);
    const [noProductsFound, setNoProductsFound] = useState(false);
    const navigate = useNavigate();


    const handleImageClick = (product, productIdentifier) => {
        // Replace with the actual URLs with the navigate 
        navigate("/product/" + product, { state: { productID: productIdentifier } });
        return;
    }

    useEffect(() => {
        // Fetch products for the given search term from the backend
        axios.get(`http://localhost:8080/product?name=${searchTerm}`)
            .then(response => {
                if (response.data.length > 0) {
                    setSearchResults(response.data);
                    setNoProductsFound(false);
                } else {
                    setSearchResults([]);
                    setNoProductsFound(true);
                }
            })
            .catch(error => {
                console.error("There was an error fetching products for the search term:", error);
                setNoProductsFound(true); // Set noProductsFound to true on error as well
            });
    }, [searchTerm]);

    return (
        <div>
            <SearchBar />
            <h1>Search Results for: {searchTerm}</h1>
            {noProductsFound ? (
                <div>No products found for the given search term.</div>
            ) : (
                <div className='productList'>
                    {searchResults.map(product => (
                        <div
                            className='products'
                            onClick={() => handleImageClick(product.name, product.productID)}
                        >
                            <img src={product.imageLocation} alt={product.name} width="100" />
                            <br />
                            <strong>{product.name}</strong> - ${product.price.toFixed(2)}
                            <br />
                            Sold by: {product.chain}
                            <br />
                            Average Rating: {product.avgRating}
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}