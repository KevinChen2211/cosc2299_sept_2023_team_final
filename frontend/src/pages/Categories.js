import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router";
import supermarket from "../assets/supermarket.png";


export default function Categories() {
    const [categories, setCategories] = useState([]);
    const navigate = useNavigate();

    const categoryImages = {
        "bakery": "bakery.jpg", // Replace with actual image URLs
        "dairy-and-eggs": "dairy.jpg",
        "fruit-and-veg": "fruit.jpg",
        "meat-and-seafood": "meat.jpg",
    };

    const categoriesClick = (subCategory) => {
        navigate("/categories/" + subCategory, { state: { subCategories: subCategory } });
        return;
    }


    useEffect(() => {
        // Fetch categories for the given subcategory from the backend
        axios.get(`http://localhost:8080/cate/all/cate`)
            .then(response => {
                setCategories(response.data);
            })
            .catch(error => {
                console.error("There was an error fetching categories: ", error);
            });
    }, []);

    return (
        <div>
            <h1>Categories</h1>
            <div className="category-list">
                {categories.map((category) => (
                    <div
                        key={category}
                        className="category-item"
                        onClick={() => categoriesClick(category)}
                    >
                        <img
                            // src={categoryImages[category]}
                            src={supermarket}
                            alt={category}
                        />
                        <p>{category.replace(/-/g, ' ')}</p>
                    </div>
                ))}
            </div>
        </div >
    );
}