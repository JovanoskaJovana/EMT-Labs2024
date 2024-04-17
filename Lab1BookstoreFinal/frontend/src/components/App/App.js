import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Route, Routes, Navigate} from "react-router-dom";
import Books from "../Books/BookList/books";
import BookStoreService from "../../repository/BookStoreRepository";
import BookAdd from "../Books/BookAdd/bookAdd";
import BookEdit from "../Books/BookEdit/bookEdit";
import Header from "../Header/header";
import CategoryList from "../Categories/categories";
import Categories from "../Categories/categories";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      books: [],
      authors: [],
      categories: [],
      selectedBook: {}
    }
  }

  render() {
    return (
       <Router>
           <Header/>
           <main>
               <div className="container">
                   <Routes>
                       <Route path="/books/categories" element={<Categories categories={this.state.categories} />} />
                       <Route path="/books/add" element={<BookAdd books={this.state.books}
                                                                  authors={this.state.authors}
                                                                  onAddBook={this.addBook}/>} />
                       <Route path="/books/edit/:id" element={<BookEdit books={this.state.selectedBook}
                                                                        authors={this.state.authors}
                                                                        onEditBook={this.editBook} /> } />
                       <Route path="/books" element={<Books books={this.state.books}
                                                            onDelete={this.deleteBook}
                                                            onEditBook={this.getBook}
                                                            onRentBook={this.rentBook}/>} />
                       <Route path="/" element={<Navigate to="/books" replace />} />

                   </Routes>
               </div>
           </main>
       </Router>
    );
  }

  componentDidMount() {
        this.loadBooks();
        this.loadAuthors();
        this.loadCategories();
  }


  loadBooks = () => {
    BookStoreService.fetchBooks()
        .then((data) => {
          this.setState({
            books: data.data
          })
        });
  }

    loadAuthors = () => {
        BookStoreService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }

    loadCategories = () => {
      BookStoreService.fetchCategories()
          .then((data) => {
              this.setState({
                  categories: data.data
              })
          });
    }

  addBook = (name, bookCategory, authorId, availableCopies) => {
      BookStoreService.addBook(name, bookCategory, authorId, availableCopies)
          .then((data) => {
              this.loadBooks();
          })
  }

  deleteBook = (id) => {
      BookStoreService.deleteBook(id)
          .then(() => {
              this.loadBooks();
          });
  }

  editBook = (id, name, bookCategory, authorId, availableCopies) => {
      BookStoreService.editBook(id, name, bookCategory, authorId, availableCopies)
          .then(() => {
              this.loadBooks();
          });
  }

    getBook = (id) => {
        BookStoreService.getBookById(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            })
    }

    rentBook = (id) => {
        BookStoreService.rentBook(id)
            .then(() => {
                this.loadBooks();
            });
    }
}
export default App;
