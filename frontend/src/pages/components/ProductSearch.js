import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import SearchBar from "./SearchBar";

export default function ProductSearch() {
    const { searchTerm } = useParams();
    const [searchResults, setSearchResults] = useState([]);
    const [noProductsFound, setNoProductsFound] = useState(false);
    const [sortedResults, setSortedResults] = useState([]);
    const [sortOrder, setSortOrder] = useState('asc');
    const [selectedCategory, setSelectedCategory] = useState('all');

    const navigate = useNavigate();

    const handleImageClick = (product, productIdentifier) => {
        // Replace with the actual URLs with the navigate 
        navigate("/product/" + product, { state: { productID: productIdentifier } });
    };

    const handleSort = () => {
        const sortedProducts = [...sortedResults].sort((a, b) => {
            if (sortOrder === 'asc') {
                return a.price - b.price;
            } else {
                return b.price - a.price;
            }
        });
        setSortedResults(sortedProducts);
        setSortOrder(sortOrder === 'asc' ? 'desc' : 'asc');
    };

    const handleCategorySelect = (event) => {
        const category = event.target.value;
        setSelectedCategory(category);

        if (category === 'all') {
            setSortedResults(searchResults);
        } else {
            const filteredProducts = searchResults.filter(product => product.category === category);
            setSortedResults(filteredProducts);
        }
    };

    useEffect(() => {
        // Fetch products for the given search term from the backend
        axios.get(`http://localhost:8080/product?name=${searchTerm}`)
            .then(response => {
                if (response.data.length > 0) {
                    setSearchResults(response.data);
                    setSortedResults(response.data);
                    setNoProductsFound(false);
                } else {
                    setSearchResults([]);
                    setSortedResults([]);
                    setNoProductsFound(true);
                }
            })
            .catch(error => {
                setNoProductsFound(true);
            });
    }, [searchTerm]);

    // Generate a list of unique categories
    const uniqueCategories = new Set();
    searchResults.forEach(product => uniqueCategories.add(product.category));

    return (
        <div>
            <SearchBar />
            <h1>Search Results for: {searchTerm}</h1>
            <div>
                <button onClick={handleSort} sortOrder={sortOrder}>
                    Sort by Price ({sortOrder === 'asc' ? 'High to Low' : 'Low to High'})
                </button>
                <select value={selectedCategory} onChange={handleCategorySelect}>
                    <option value="all">All Categories</option>
                    {[...uniqueCategories].map(category => (
                        <option key={category} value={category}>
                            {category.replace(/-/g, ' ')}
                        </option>
                    ))}
                </select>
            </div>
            {noProductsFound ? (
                <div>No products found for the given search term.</div>
            ) : (
                <div className='productList'>
                    {sortedResults.map(product => (
                        <div
                            key={product.productID}
                            className='products'
                            onClick={() => handleImageClick(product.name, product.productID)}
                        >
                            <img src={product.imageLocation} alt={product.name} width="100" />
                            <br />
                            <strong>{product.name}</strong> - ${product.price.toFixed(2)}
                            <br />
                            Sold by: {product.chain}
                            <br />
                            Average Rating: {product.avgRating}
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}
