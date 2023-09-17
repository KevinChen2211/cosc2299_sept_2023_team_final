import React, { useState } from "react";
import '../styling.css'

export default function Item() {

  const [cartItems, setCartItems] = useState([
    { id: 1, name: "Product 1", price: 12 , image: '../assets/milk.jpeg', avgRating: 2,
    chain: 'Coles', category: 'dairy-and-eggs'
  },
  ]);
  
  return (
    <tbody>
      <table width={'0%'} align="center">
       <tr >
        <td><img src="../assets/milk.jpeg" alt="itemImage" width={'350px'} height={'350px'} ></img> </td>
        <td width={'10px'}></td>
        <td className="itemInformation" align="center" width={'50%'}>  <br></br><br></br>
        {cartItems.map((item, index) => (
            <div>
              <h3><span className='productName'>{item.name}</span></h3><br></br>
              <h4><span className='productPrice'>Price ${item.price}</span></h4><br></br>
              Category: {item.category}<br/>
              Average Rating: {item.avgRating}<br/>
              Chain: {item.chain}<br/>
              <br/><br/>
              <button> Add to cart</button>
            </div>
          ))}
        </td>
       </tr>
      </table>

      <br></br>
    </tbody>
  );
}