import React, { useState, useEffect } from "react";
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import BackButton from './components/BackButton';

function Notifications({ email, password }) {

  const [isNotified, setIsNotified] = useState({});
  const navigate = useNavigate();
  const [promotedItems, setPromotedItems] = useState([]);
  const [sortedResults, setSortedResults] = useState([]);
  const [selectedChain, setSelectedChain] = useState('all');
  const [sortOrder, setSortOrder] = useState('asc');
  const [showSubCategory, setShowSubCategory] = useState(false);
  const [selectedSubCategory, setSelectedSubCategory] = useState('all');
  const [selectedCategory, setSelectedCategory] = useState('all');

  // Generate a list of unique categories
  const uniqueCategories = new Set();
  promotedItems.forEach(product => uniqueCategories.add(product.category));

  const uniqueChains = new Set();
  sortedResults.forEach(product => uniqueChains.add(product.chain));

  const uniqueSubCategory = new Set();
  sortedResults.forEach(product => uniqueSubCategory.add(product.subcategory));

  const handleImageClick = (product, productIdentifier) => {
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
    setSortedResults(promotedItems);
  };

  const handleChainChange = (event) => {
    const chain = event.target.value;
    setSelectedChain(chain);
    const filteredByChain = chain === 'all' ? promotedItems : promotedItems.filter(product => product.chain === chain);
    const filteredByCategory = selectedCategory === 'all' ? filteredByChain : filteredByChain.filter(product => product.category === selectedCategory);
    const filteredBySubCategory = selectedSubCategory === 'all' ? filteredByCategory : filteredByCategory.filter(product => product.subcategory === selectedSubCategory);
    setSortedResults(filteredBySubCategory);
  };

  const handleSubCategoryChange = (event) => {
    const subCategory = event.target.value;
    setSelectedSubCategory(subCategory);
    const filteredByCategory = selectedCategory === 'all' ? promotedItems : promotedItems.filter(product => product.category === selectedCategory);
    const filteredByChain = selectedChain === 'all' ? filteredByCategory : filteredByCategory.filter(product => product.chain === selectedChain);
    const filteredBySubCategory = subCategory === 'all' ? filteredByChain : filteredByChain.filter(product => product.subcategory === subCategory);
    setSortedResults(filteredBySubCategory)

  };

  const handleCategorySelect = (event) => {
    const category = event.target.value;
    setSelectedChain('all');
    setSelectedSubCategory('all');
    setSelectedCategory(category);
    const filteredByCategory = category === 'all' ? promotedItems : promotedItems.filter(product => product.category === category);
    const filteredByChain = selectedChain === 'all' ? filteredByCategory : filteredByCategory.filter(product => product.chain === selectedChain);
    setSortedResults(filteredByChain);
    // Check if the selected category is not 'all', then set a flag to display the additional dropdown menu
    if (category !== 'all') {
      setShowSubCategory(true);
    } else {
      setShowSubCategory(false);
    }
  };

  useEffect(() => {
    // Fetch user data from the backend
    axios.get(`http://localhost:8081/v1/account/${email}/${password}`)
      .then(response => {
        setIsNotified(response.data.isNotified); // Extract isNotified from the response data
      })
      .catch(error => {
        console.error("There was an error fetching user data:", error);
      });
  }, []);

  useEffect(() => {
    axios.get(`http://localhost:8080/product/promotion`)
      .then(response => {
        setSelectedChain('all');
        setSelectedSubCategory('all');
        setSelectedCategory('all');
        setPromotedItems(response.data);
        setSortedResults(response.data);
      })
      .catch(error => {
        console.error("There was an error fetching products for the subcategory:", error);
      });
  }, []);

  return (
    <div className="text-center">
      <h1>Notifications</h1>
      {isNotified === true ?
        <>
          <BackButton />
          <div className='filter-bar'>
            <button className='category-button' onClick={handleSort} sortOrder={sortOrder}>
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
            <button className='category-button' onClick={handleResetFilter}>
              Reset filter
            </button>
          </div>
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
        </>
        :
        <div>Notifications are not available as you have opt out of receiving any.</div>
      }
    </div>
  );
}

export default Notifications;