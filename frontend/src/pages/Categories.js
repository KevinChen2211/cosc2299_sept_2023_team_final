import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router";
import BackButton from "./components/BackButton";
import bakery from "../assets/bakery.png"
import dairy from "../assets/dairy.png"
import fruit from "../assets/fruit.png"
import meat from "../assets/meat.png"


export default function Categories() {
    const [categories, setCategories] = useState([]);
    const navigate = useNavigate();

    const categoryImages = {
        "bakery": bakery,
        "dairy-and-eggs": dairy,
        "fruit-and-veg": fruit,
        "meat-and-seafood": meat,
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
            <BackButton />
            <h1>Categories</h1>
            <div className="category-list">
                {categories.map((category) => (
                    <div
                        key={category}
                        className="category-item"
                        onClick={() => categoriesClick(category)}
                    >
                        <img
                            src={categoryImages[category]}
                            alt={category}
                        />
                        <p>{category.replace(/-/g, ' ')}</p>
                    </div>
                ))}
            </div>
        </div >
    );
}