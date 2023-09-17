import '../styling.css';
import React, { useState } from "react";

export default function delivery(){
    const [formData, setFormData] = useState({
        deliveryOption: 'standard',
        timePreference: '9AM - 1PM',
        specialInstructions: ''    
    });

    const handleSubmit = (e) =>{
    e.preventDefault();
    console.log("Form submitted with data: ",formData);
    }

    const handleInputChange = (e) =>{
        const {name, value} = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };
/* 
FYI: The whole code is implemented inside a single container.
STANDARD/EXPRESS SHIPPING:
    - Button for selection is either standard or express
    - needs to fill in estimated date
    - needs estimated delivery fee
    - time range are in buttons, more or less buttons can be modified as required.
- instruction note is optional
- Proceed to payment button will lead to payment page
*/
  return (
      <main> 
        <head>
          <title>Delivery</title>
        </head>

        <body className='container'>
          <div className='center'>Delivery Options</div>
          <form onSubmit={handleSubmit}>
            <table>
                {/* ______Standard Delivery__________ */}
                <tr>Standard Delivery</tr>
                <tr>
                    <td><button className='quantityButton'></button> </td>
                    <td>
                        Estimated by: 
                        <span>DD/MM/YYYY</span> - <span>DD/MM/YYYY</span>
                    </td>
                    <td className='right'>$ Delivery fee</td>
                </tr>
                <tr>
                    <td colSpan={3}>Select Time Preference </td>
                </tr>
                <tr>
                    <table>
                        <tr>
                            <td> <button className='timeRangeButton'>9AM - 1PM</button> </td>
                            <td> <button className='timeRangeButton'>1PM - 5PM</button> </td>
                        </tr>
                    </table>
                </tr>

                <tr><hr></hr></tr>

                {/* ______ Express Delivery __________ */}
                <tr>Express Delivery</tr>
                <tr>
                    <td><button className='quantityButton'></button> </td>
                    <td>
                        Estimated by: 
                        <span>DD/MM/YYYY</span> - <span>DD/MM/YYYY</span>
                    </td>
                    <td className='right'>$ Delivery fee</td>
                </tr>
                <tr>
                    <td colSpan={3}>Select Time Preference </td>
                </tr>
                <tr>
                    <table>
                        <tr>
                            <td> <button className='timeRangeButton'>9AM - 1PM</button> </td>
                            <td> <button className='timeRangeButton'>1PM - 5PM</button> </td>
                        </tr>
                    </table>
                </tr>

                {/* ______ Special Delivery Instructions __________ */}
                <tr>Special Delivery Instructions (optional)</tr>
                <tr>
                    <td colSpan={2}>
                        <textarea name="special delivery instructions" rows="3" cols='80%'> Add a note... </textarea> 
                    </td>
                    <td>
                        <button className='checkoutButton'>
                            <span> Proceed to Payment </span>
                        </button>
                    </td>
                </tr>
            </table>
          </form>
        </body>
      </main>
      );
} 
