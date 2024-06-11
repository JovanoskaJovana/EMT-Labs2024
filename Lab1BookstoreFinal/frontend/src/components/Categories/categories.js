import React from 'react';

const Categories = ({ categories }) => {
    return (
        <div className="container mt-5">
            <h1 className="mb-4 text-center">Book Categories</h1>
            <ul className="list-group">
                {categories.map((category, index) => (
                    <li key={index} className="list-group-item">{category}</li>
                ))}
            </ul>
        </div>
    );
}

export default Categories;
