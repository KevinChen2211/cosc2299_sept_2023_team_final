// Subcategory.js
import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';

const Subcategory = (props) => {
    const [products, setProducts] = useState([]);
    const location = useLocation();
    const navigate = useNavigate();

    const handleImageClick = (product) => {
        // Replace with the actual URLs with the navigate 
        navigate("/product/" + product, { state: { productName: product } });
        return;
    }

    useEffect(() => {
        // Fetch products for the given subcategory from the backend
        axios.get(`http://localhost:8080/product/subcate/${location.state.subCategoriesProducts}`)
            .then(response => {
                setProducts(response.data);
            })
            .catch(error => {
                console.error("There was an error fetching products for the subcategory:", error);
            });
    }, []);

    return (
        <div>
            <h2>Products for Subcategory: {location.state.subCategoriesProducts}</h2>
            <div className='productList'>
                {products.map(product => (
                    <div
                        className='products'
                        onClick={() => handleImageClick(product.name)}
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
        </div>
    );
}

export default Subcategory;
