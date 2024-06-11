import axios from "../custom-axios/axios";

const BookStoreService = {
    fetchBooks: () => {
      return axios.get("/books");
    },
    fetchAuthors: () => {
        return axios.get("/authors");
    },
    fetchCategories: () => {
        return axios.get("/books/categories");
    },
    addBook: (name, bookCategory, authorId, availableCopies) => {
        return axios.post("/books/add", {
            "name": name,
            "bookCategory": bookCategory,
            "authorId": authorId,
            "availableCopies": availableCopies
        });
    },
    editBook: (id, name, bookCategory, authorId, availableCopies) => {
        return axios.put(`/books/edit/${id}`, {
            "name": name,
            "bookCategory": bookCategory,
            "authorId": authorId,
            "availableCopies": availableCopies
        });
    },
    deleteBook: (id) => {
      return axios.delete(`/books/delete/${id}`);
    },
    rentBook: (id) => {
        return axios.post(`/books/rent/${id}`);
    },
    getBookById: (id) => {
        return axios.get(`/books/${id}`);
    },
    searchBook: (query) => {
        return axios.get("books/search",
            {params: {query}});
    }
}

export default BookStoreService;