import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';


function SearchBar() {
    const navigate = useNavigate();
    const [searchTerm, setSearchTerm] = useState('');


    const handleSearch = () => {
        // You can perform filtering or searching logic here based on the searchTerm
        // For simplicity, we'll just display the search term as text.
        navigate("/product/" + searchTerm, { state: { productName: searchTerm } });
    };

    return (
        <div>
            <input
                type="text"
                placeholder="Search..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
            />
            <button onClick={() => handleSearch()}>Search</button>
        </div>
    );
}

export default SearchBar;
