import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import SearchBar from "./SearchBar";
import BackButton from './BackButton';

export default function ProductSearch() {
    const { searchTerm } = useParams();
    const [searchResults, setSearchResults] = useState([]);
    const [noProductsFound, setNoProductsFound] = useState(false);
    const [sortedResults, setSortedResults] = useState([]);
    const [sortOrder, setSortOrder] = useState('asc');
    const [selectedCategory, setSelectedCategory] = useState('all');
    const [selectedChain, setSelectedChain] = useState('all');
    const [selectedSubCategory, setSelectedSubCategory] = useState('all');
    const [showSubCategory, setShowSubCategory] = useState(false);

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

    const handleResetFilter = () => {
        setSelectedChain('all');
        setSelectedSubCategory('all');
        setSelectedCategory('all');
        setShowSubCategory(false);
        setSortedResults(searchResults);
    };

    const handleCategorySelect = (event) => {
        const category = event.target.value;
        setSelectedChain('all');
        setSelectedSubCategory('all');
        setSelectedCategory(category);
        const filteredByCategory = category === 'all' ? searchResults : searchResults.filter(product => product.category === category);
        const filteredByChain = selectedChain === 'all' ? filteredByCategory : filteredByCategory.filter(product => product.chain === selectedChain);
        setSortedResults(filteredByChain);
        // Check if the selected category is not 'all', then set a flag to display the additional dropdown menu
        if (category !== 'all') {
            setShowSubCategory(true);
        } else {
            setShowSubCategory(false);
        }
    };

    const handleChainChange = (event) => {
        const chain = event.target.value;
        setSelectedChain(chain);
        const filteredByChain = chain === 'all' ? searchResults : searchResults.filter(product => product.chain === chain);
        const filteredByCategory = selectedCategory === 'all' ? filteredByChain : filteredByChain.filter(product => product.category === selectedCategory);
        const filteredBySubCategory = selectedSubCategory === 'all' ? filteredByCategory : filteredByCategory.filter(product => product.subcategory === selectedSubCategory);
        setSortedResults(filteredBySubCategory);
    };

    const handleSubCategoryChange = (event) => {
        const subCategory = event.target.value;
        setSelectedSubCategory(subCategory);
        const filteredByCategory = selectedCategory === 'all' ? searchResults : searchResults.filter(product => product.category === selectedCategory);
        const filteredByChain = selectedChain === 'all' ? filteredByCategory : filteredByCategory.filter(product => product.chain === selectedChain);
        const filteredBySubCategory = subCategory === 'all' ? filteredByChain : filteredByChain.filter(product => product.subcategory === subCategory);
        setSortedResults(filteredBySubCategory)

    };

    useEffect(() => {
        // Fetch products for the given search term from the backend
        axios.get(`http://localhost:8080/product?name=${searchTerm}`)
            .then(response => {
                if (response.data.length > 0) {
                    setSelectedChain('all');
                    setSelectedSubCategory('all');
                    setSelectedCategory('all');
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
                console.log(error);
                setNoProductsFound(true);
            });
    }, [searchTerm]);

    // Generate a list of unique categories
    const uniqueCategories = new Set();
    searchResults.forEach(product => uniqueCategories.add(product.category));

    const uniqueChains = new Set();
    sortedResults.forEach(product => uniqueChains.add(product.chain));

    const uniqueSubCategory = new Set();
    sortedResults.forEach(product => uniqueSubCategory.add(product.subcategory));

    return (
        <div>
            <BackButton />
            <SearchBar />
            <br />
            <h1>{sortedResults.length} items found, Search Results for: {searchTerm}</h1>
            <div className='filter-bar'>
                <button class="category-button" onClick={handleSort} sortOrder={sortOrder}>
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
                {showSubCategory && (
                    <select value={selectedSubCategory} onChange={handleSubCategoryChange}>
                        <option value="all">All Subcategories</option>
                        {[...uniqueSubCategory].map(subcategory => (
                            <option key={subcategory} value={subcategory}>
                                {subcategory}
                            </option>
                        ))}
                    </select>
                )}
                <select value={selectedChain} onChange={handleChainChange}>
                    <option value="all">All Chains</option>
                    {[...uniqueChains].map(chain => (
                        <option key={chain} value={chain}>
                            {chain}
                        </option>
                    ))}
                </select>
                <button class="category-button" onClick={handleResetFilter}>
                    Reset Filter
                </button>
            </div>
            {noProductsFound ? (
                <div>No products found for the given search term.</div>
            ) : sortedResults.length === 0 ? (
                <div>No products found with the category {selectedCategory} and chain {selectedChain}.</div>
            ) : (
                <div className='product-list'>
                    {sortedResults.map(product => (
                        <div
                            key={product.productID}
                            className='products'
                            onClick={() => handleImageClick(product.name, product.productID)}
                        >
                            <img src={product.imageLocation} alt={product.name} width="100" />
                            <br />
                            <strong>{product.name}</strong>
                            <br />
                            ${product.price.toFixed(2)} AUD
                            <br />
                            Sold by: {product.chain}
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}
