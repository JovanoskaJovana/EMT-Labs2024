import React from "react";
import { Link } from "react-router-dom";
import ReactPaginate from 'react-paginate';
import BookTerm from "../BookTerm/bookTerm";
import bookStoreRepository from "../../../repository/BookStoreRepository";

class Books extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            page: 0,
            size: 5,
            books: props.books,
            searchTerm: ''
        };
    }

    componentDidUpdate(prevProps) {
        if (this.props.books !== prevProps.books) {
            this.setState({ books: this.props.books });
        }
    }

    loadBooks = () => {
        bookStoreRepository.fetchBooks()
            .then(response => {
                this.setState({ books: response.data });
            })
            .catch(error => {
                console.error('Error loading books:', error);
            });
    }

    handlePageClick = (data) => {
        let selected = data.selected;
        this.setState({
            page: selected
        });
    }

    getBooksPage = (offset, nextPageOffset) => {
        return this.state.books.slice(offset, nextPageOffset).map((book, index) => (
            <BookTerm key={index} term={book} onDelete={this.props.onDelete} onEdit={this.props.onEditBook} onRentBook={this.props.onRentBook}/>
        ));
    }

    handleSearchChange = (event) => {
        this.setState({ searchTerm: event.target.value });
    }

    handleSearch = () => {
        bookStoreRepository.searchBook(this.state.searchTerm) // Make sure searchBook is correctly implemented
            .then(response => {
                this.setState({ books: response.data });
            })
            .catch(error => {
                console.error('Failed to fetch books:', error);
            });
    }

    render() {
        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.state.books.length / this.state.size);
        const books = this.getBooksPage(offset, nextPageOffset);

        return (
            <div className="container mm-4 mt-5">
                <div className="row">
                    <div className="col mb-3">
                        <input
                            type="text"
                            value={this.state.searchTerm}
                            onChange={this.handleSearchChange}
                            placeholder="Search books"
                            className="form-control"
                        />
                        <button onClick={this.handleSearch} className="btn btn-primary mt-2">Search</button>
                    </div>
                </div>
                <div className="row">
                    <div className="table-responsive">
                        <table className="table table-striped">
                            <thead>
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">Category</th>
                                <th scope="col">Author</th>
                                <th scope="col">Availability</th>
                            </tr>
                            </thead>
                            <tbody>
                            {books}
                            </tbody>
                        </table>
                    </div>
                </div>
                <ReactPaginate
                    previousLabel={"back"}
                    nextLabel={"next"}
                    breakLabel={<a href="/#">...</a>}
                    breakClassName={"break-me"}
                    pageCount={pageCount}
                    marginPagesDisplayed={2}
                    pageRangeDisplayed={5}
                    onPageChange={this.handlePageClick}
                    containerClassName={"pagination m-4 justify-content-center"}
                    activeClassName={"active"}
                />
                <div className="col mb-3">
                    <div className="row">
                        <div className="col-sm-12 col-md-12">
                            <Link className="btn btn-block btn-dark" to={"/books/add"}>Add new book</Link>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Books;
