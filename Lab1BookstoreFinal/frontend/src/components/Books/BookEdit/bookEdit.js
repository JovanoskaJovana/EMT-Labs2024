import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import BookStoreService from "../../../repository/BookStoreRepository";

const BookEdit = (props) => {
    const navigate = useNavigate();
    const { id } = useParams();
    const [formData, setFormData] = useState({
        name: "",
        bookCategory: "",
        authorId: "",
        availableCopies: ""
    });

    useEffect(() => {
        if (id) {
            BookStoreService.getBookById(id)
                .then((data) => {
                    setFormData({
                        name: data.name,
                        bookCategory: data.bookCategory,
                        authorId: data.author.id,
                        availableCopies: data.availableCopies
                    });
                })
                .catch(error => {
                    console.error('Error fetching book details:', error);
                });
        }
    }, [id]);

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        });
    };

    const onFormSubmit = (e) => {
        e.preventDefault();
        props.onEditBook(id, formData.name, formData.bookCategory, formData.authorId, formData.availableCopies);
        navigate("/books");
    };

    return (
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Book name</label>
                        <input
                            type="text"
                            className="form-control"
                            id="name"
                            name="name"
                            value={formData.name}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="bookCategory">Book Category</label>
                        <input
                            type="text"
                            className="form-control"
                            id="bookCategory"
                            name="bookCategory"
                            value={formData.bookCategory}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Author</label>
                        <select
                            name="authorId"
                            className="form-control"
                            value={formData.authorId}
                            onChange={handleChange}
                        >
                            {props.authors.map((author) => (
                                <option key={author.id} value={author.id}>{author.name}</option>
                            ))}
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="availableCopies">Available copies</label>
                        <input
                            type="text"
                            className="form-control"
                            id="availableCopies"
                            name="availableCopies"
                            value={formData.availableCopies}
                            onChange={handleChange}
                        />
                    </div>
                    <button type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    );
};

export default BookEdit;
