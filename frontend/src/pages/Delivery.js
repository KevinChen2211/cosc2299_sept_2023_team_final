import '../styling.css';
import { useLocation, useNavigate } from "react-router-dom";
import React, { useState } from "react";
import SwitchButton from './components/SwitchButton';


export default function ShoppingCart({ cartItems }) {
    const navigate = useNavigate();
    const location = useLocation();
    const [deliveryFee, setDeliveryFee] = useState(7);

    const calculateTotal = () => {
        let total = 0;
        for (const item of cartItems) {
            total += (item.price * item.boughtQuantity);
        }
        return total.toFixed(2);
    }

    function addDaysToDate(days) {
        let today = new Date();
        today.setDate(today.getDate() + days);

        let date = today.getDate() + "-" + (today.getMonth() + 1) + "-" + today.getFullYear();

        return date;
    }

    const handleFeeChange = (fee) => {
        // Set the global variable to the input value
        setDeliveryFee(fee);
    };

    return (
        <div align="center">
            <div align="left">
                <button onClick={() => navigate('/checkout')}>
                    <span>Back to checkout</span>
                </button>
            </div>
            <h2>Select Delivery Option</h2>
            <h3>delivering to {location.state.address}</h3>
            <div className="standard-delivery">
                <h5>Standard Delivery</h5>
                <input
                    type="radio"
                    value="Standard Delivery"
                    name="delivery"
                    checked={deliveryFee === 7}
                    onChange={() => handleFeeChange(7)}
                />
                <span> Delivered between: {addDaysToDate(7)} - {addDaysToDate(14)} </span>
                <br />
                <span>$7 delivery fee</span>
                <br />
                Select time preference: <SwitchButton />
                <hr />
            </div>
            <div className="express-delivery">
                <h5>Express Delivery</h5>
                <input
                    type="radio"
                    value="Express Delivery"
                    name="delivery"
                    checked={deliveryFee === 14}
                    onChange={() => handleFeeChange(14)}
                />
                <span> Delivered between: {addDaysToDate(1)} - {addDaysToDate(7)} </span>
                <br />
                <span>$14 delivery fee</span>
                <br />
                Select time preference: <SwitchButton />
            </div>
            <br />
            <br />
            <hr />
            <div>Amount: ${calculateTotal()}</div>
            <div>
                Total amount: $
                {(parseFloat(calculateTotal()) + deliveryFee).toFixed(2)}
            </div>
            <input type="text" placeholder="Add a note " />
            <div className="form-group" align="center">
                <br />
                <button
                    className="checkoutButton"
                    onClick={() => navigate('/payment', { state: { deliveryFee } })}
                >
                    Continue to Payment
                </button>
            </div>
        </div>
    );
}