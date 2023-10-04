import '../styling.css';
import { useNavigate } from "react-router-dom"; 
import React from "react";


export default function ShoppingCart({cartItems}){
    const navigate = useNavigate();
    const calculateTotal = () => {
        let total = 0;
        for (const item of cartItems) {
          total += (item.price * item.boughtQuantity);
        }
        return total;
      }
    const deliveryFee = 7;
  return (
        <div align='center'>
            <div align='left'>
                <button onClick={() => navigate('/checkout')} >
                    <span>
                        Back to checkout
                    </span>
                </button>
            </div>
            <h2>Select Delivery Option</h2><br/>
            <h5>Standard Delivery</h5>
            <input type='radio' value='Standard Delivery' name='delivery' checked={true}/>
            <span> Delivered by: DD/MM/YY - DD/MM/YY </span>
            <span>$ {deliveryFee}</span>
            <br/>Select time preference:
            <button> 9AM - 1PM </button> <button> 1PM - 5PM </button>
            <hr/>

            <h5>Express Delivery</h5>
            <input type='radio' value='Standard Delivery' name='delivery' />
            <span> Delivered by: DD/MM/YY - DD/MM/YY </span>
            <span>$ {deliveryFee}</span>
            <br/>Select time preference:
            <button> 9AM - 1PM </button> <button> 1PM - 5PM </button> <br/><br/>
            <hr/>
            <div>Amount: ${calculateTotal()}</div>
            <div>Total amount: ${calculateTotal() + deliveryFee}</div>

            <input type='text' placeholder='Add a note '/>

            <div className='form-group' align='center'>
                <br/>
                <button className='checkoutButton' 
                    onClick={() => navigate('/payment', { state: { deliveryFee: deliveryFee } })}>
                    Continue to Payment
                </button>
            </div>

        </div>
      );
}
