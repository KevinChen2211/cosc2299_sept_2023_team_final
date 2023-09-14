import React, { useState } from "react";
import '../styling.css'

export default function Item() {


  return (
    <tbody>
      <table width={'100%'} align="center">
       <tr width='100%'>
        <td><img src="../assets/milk.jpeg" alt="itemImage" width={'350px'} height={'350px'} ></img> </td>
        <td width={'40px'}></td>
        <td className="itemInformation" align="center">  <br></br><br></br>
              <h3>Product Name </h3><br></br>
              <h4>Price</h4><br></br>
              Product Information <br></br>
              *****<br></br><br></br>
              <button>Add to cart</button>
        </td>
       </tr>
      </table>

      <br></br>
      <table>
       <tr>
        <td className="setPadding"><img src="../assets/milk.jpeg" alt="itemImage" width={'110px'} height={'110px'} ></img> </td>
        <td className="setPadding"><img src="../assets/milk.jpeg" alt="itemImage" width={'110px'} height={'110px'} ></img> </td>
        <td className="setPadding"><img src="../assets/milk.jpeg" alt="itemImage" width={'110px'} height={'110px'} ></img> </td>
       </tr>

       </table>
    </tbody>
  );
}