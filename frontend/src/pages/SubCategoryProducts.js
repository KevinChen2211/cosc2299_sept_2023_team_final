// Subcategory.js
import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import BackButton from './components/BackButton';
import ProductComponent from './components/ProductComponent';

const Subcategory = (props) => {
    const [products, setProducts] = useState([]);
    const [sortedResults, setSortedResults] = useState([]);
    const [sortOrder, setSortOrder] = useState('asc');
    const [selectedChain, setSelectedChain] = useState('all');
    const location = useLocation();
    const navigate = useNavigate();

    const handleImageClick = (product, productIdentifier) => {
        // Replace with the actual URLs with the navigate 
        navigate("/product/" + product, { state: { productID: productIdentifier } });
        return;
    }

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
        setSortedResults(products);
    };

    const handleChainChange = (event) => {
        const chain = event.target.value;
        setSelectedChain(chain);
        const filteredByChain = chain === 'all' ? products : products.filter(product => product.chain === chain);
        setSortedResults(filteredByChain);
    };

    useEffect(() => {
        // Fetch products for the given subcategory from the backend
        axios.get(`http://localhost:8080/product/subcate/${location.state.subCategoriesProducts}`)
            .then(response => {
                setProducts(response.data);
                setSortedResults(response.data);
            })
            .catch(error => {
                console.error("There was an error fetching products for the subcategory:", error);
            });
    }, []);

    const uniqueChains = new Set();
    products.forEach(product => uniqueChains.add(product.chain));

    return (
        <div>
            <BackButton />
            <div className='filter-bar'>
                <button className='category-button' onClick={handleSort} sortOrder={sortOrder}>
                    Sort by Price ({sortOrder === 'asc' ? 'High to Low' : 'Low to High'})
                </button>
                <select value={selectedChain} onChange={handleChainChange}>
                    <option value="all">All Chains</option>
                    {[...uniqueChains].map(chain => (
                        <option key={chain} value={chain}>
                            {chain}
                        </option>
                    ))}
                </select>
                <button className='category-button' onClick={handleResetFilter}>
                    Reset Filter
                </button>
            </div>
            <h2>Products for Subcategory: {location.state.subCategoriesProducts}</h2>
            <div className='product-list' >
                {sortedResults.map(product => (
                    <ProductComponent product={product} />
                ))}
            </div>

        </div>
    );
}

export default Subcategory;
