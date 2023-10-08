import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';


function SearchBar() {
    const navigate = useNavigate();
    const [searchTerm, setSearchTerm] = useState('');


    const handleSearch = () => {
        if (searchTerm.length > 0) {
            navigate("/productsearch/" + searchTerm, { state: { productName: searchTerm } });
        }

    };

    return (
        <div className='search-bar-div'>
            <input
                className='search-bar-input'
                type="text"
                placeholder="Search for items..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
            />
            <button
                class="default-home-button"
                onClick={() => handleSearch()}
            >Search
            </button>
        </div>
    );
}

export default SearchBar;
