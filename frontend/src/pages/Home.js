import React from "react";
import { useNavigate } from "react-router-dom";
import SearchBar from "./components/SearchBar";
import supermarket from "../assets/supermarket.png"

function Home(props) {

  // Get an array of
  const images = ['apple.png', 'milk.png', 'bread.png', 'water.png'];


  const navigate = useNavigate();

  const handleImageClick = (product) => {
    // Replace with the actual URLs with the navigate 
    navigate("/product/" + product, { state: { productName: product } });
    return;
  }



  const handleSubmit = (event) => {
    navigate("/signup");
    return;
  }
  return (
    <div className="text-center">
      {/* content describing website purpose */}

      <h1>SuperPrice</h1>
      <div className="form-group">

      </div>
      {props.email === null ?
        <>
          <p>this is the landing page</p>
          <input onClick={handleSubmit} className="btn btn-primary" value="Sign Up" />
        </>
        :
        <>
          <div className="home-page-search-catagories">
            {/* TODO: add feature to broswe tab */}
            <button>browse</button>
            <SearchBar />
          </div>
          <img
            src={supermarket}
            className="main-page-image"
          />
          <h1>specials</h1>
          <div className="specials-container">
            {images
              .map((image, index) => (
                <div key={index}>
                  <img
                    key={index}
                    // this will need to change into an api call
                    src={require(`./components/exampleimages/${image}`)}
                    alt={image}
                    onClick={() => handleImageClick(image)}
                    style={{
                      cursor: 'pointer',
                    }}
                  />
                  <p>{image}</p>
                </div>
              ))}

          </div>
        </>
      }

    </div>
  );
}

export default Home;
