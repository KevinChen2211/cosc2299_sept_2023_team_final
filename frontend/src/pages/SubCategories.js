// Subcategory.js
import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';
import BackButton from './components/BackButton';

const SubCategory = (props) => {
    const [subCategories, setSubCategories] = useState([]);
    const location = useLocation();
    const navigate = useNavigate();

    useEffect(() => {
        // Fetch subCategories for the given subcategory from the backend
        axios.get(`http://localhost:8080/cate/sub/${location.state.subCategories}`)
            .then(response => {
                setSubCategories(response.data);
            })
            .catch(error => {
                console.error("There was an error fetching subCategories for the subcategory:", error);
            });
    }, []);

    const subCategoriesClick = (subCategoryProduct) => {
        navigate("/categories/" + location.state.subCategories + "/" + subCategoryProduct, { state: { subCategoriesProducts: subCategoryProduct } });
        return;
    }

    return (
        <div>
            <BackButton />
            <h1>Sub Categories for {location.state.subCategories.replace(/-/g, ' ')} :</h1>
            <div className="sub-category-list">
                {subCategories.map((subcategory) => (
                    <div
                        key={subcategory}
                        className="category-item"
                        onClick={() => subCategoriesClick(subcategory)}
                    >
                        <p>{subcategory}</p>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default SubCategory;
