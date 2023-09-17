import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import '../../styling.css'
import axios from "axios";

export default function ProductPage({ addToCart, cartItems, updateCartItems }) {
    const [cartItem, setCartItem] = useState([]);
    const location = useLocation();
    const navigate = useNavigate();

    const [clickCount, setCount] = useState(1);

    useEffect(() => {
        // Fetch products for the given subcategory from the backend
        axios.get(`http://localhost:8080/product/id/${location.state.productID}`)
            .then(response => {
                setCartItem(response.data);
            })
            .catch(error => {
                console.error("There was an error fetching products for the subcategory:", error);
            });
    }, []);

    const handleAddToCart = () => {
        if (!Array.isArray(cartItems)) {
            cartItems = [];
        }
        const itemIndex = cartItems.findIndex((item) => item.id === cartItem.id);

        if (itemIndex !== -1) {
            //const updatedCartItems = [...cartItems];
            //updatedCartItems[itemIndex].quantity += clickCount + 1; 
            cartItem.quantity++
            //addToCart(cartItem);
            //updateCartItems(updatedCartItems);
            updateCartItems(cartItem);
        } else {
            addToCart(cartItem);
        }
        setCount(clickCount + 1);
    };


    return (
        <tbody>
            <td align='right'>
                <button onClick={() => navigate('/shoppingCart')}>
                    <span >
                        Shopping Cart
                    </span>
                </button>
            </td>
            <br />
            <td className="itemInformation" align="center" width={'50%'}>  <br></br><br></br>
                {/* {cartItems.map((cartItem, index) => ( */}
                <div style={{ display: 'flex' }}>
                    <div>
                        <span><img src={cartItem.image} width={'250px'} height={'250px'} ></img> </span>
                        <span width={'10px'}></span>
                    </div>
                    <div>
                        <h3><span className='productName'>{cartItem.name}</span></h3><br></br>
                        <h4><span className='productPrice'>Price ${cartItem.price}</span></h4><br></br>
                        Category: {cartItem.category}<br />
                        Average Rating: {cartItem.avgRating}<br />
                        Chain: {cartItem.chain}<br />
                        <br />
                        <button onClick={handleAddToCart}> Add to cart</button>
                        <br /><br />
                    </div>
                </div>
                {/* ))} */}
            </td>
            <br></br>
        </tbody>
    );
}