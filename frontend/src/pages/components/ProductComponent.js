import React from "react";
import { useNavigate } from "react-router-dom";

export default function ProductComponent({ product }) {
    const navigate = useNavigate();

    const handleImageClick = (productIdentifier) => {
        navigate(`/product/${product.name}`, { state: { productID: productIdentifier } });
    };

    return (
        <div
            key={product.productID}
            className='products'
            onClick={() => handleImageClick(product.productID)}
        >
            <img src={product.imageLocation} alt={product.name} width="100" />
            <br />
            <strong>{product.name}</strong>
            <br />
            ${product.price.toFixed(2)} AUD
            <br />
            Sold by: {product.chain}
            {product.isPromoted == true ?
                <div>Product is on Sale!</div>
                :
                <></>
            }
        </div>
    );
}
