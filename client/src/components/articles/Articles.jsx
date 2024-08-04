import React, { useState } from 'react';
import './articles.css';
import Article from './article/Article';

// Define the articles array
const articles = [
    { id: 1, title: "Article 1", content: "This is the content of article 1.", link: "#", date: "2023-08-01", image: "https://via.placeholder.com/400x200" },
    { id: 2, title: "Article 2", content: "This is the content of article 2.", link: "#", date: "2023-07-15", image: "https://via.placeholder.com/400x200" },
    { id: 3, title: "Article 3", content: "This is the content of article 3.", link: "#", date: "2023-08-03", image: "https://via.placeholder.com/400x200" },
    { id: 4, title: "Article 4", content: "This is the content of article 4.", link: "#", date: "2023-08-05", image: "https://via.placeholder.com/400x200" },
    { id: 5, title: "Article 5", content: "This is the content of article 5.", link: "#", date: "2023-08-07", image: "https://via.placeholder.com/400x200" },
    { id: 6, title: "Article 6", content: "This is the content of article 6.", link: "#", date: "2023-08-09", image: "https://via.placeholder.com/400x200" },
    { id: 7, title: "Article 7", content: "This is the content of article 7.", link: "#", date: "2023-08-11", image: "https://via.placeholder.com/400x200" },
    { id: 8, title: "Article 8", content: "This is the content of article 1.", link: "#", date: "2023-08-01", image: "https://via.placeholder.com/400x200" },
    { id: 9, title: "Article 9", content: "This is the content of article 2.", link: "#", date: "2023-07-15", image: "https://via.placeholder.com/400x200" },
    { id: 10, title: "Article 10", content: "This is the content of article 3.", link: "#", date: "2023-08-03", image: "https://via.placeholder.com/400x200" },
    { id: 11, title: "Article 11", content: "This is the content of article 4.", link: "#", date: "2023-08-05", image: "https://via.placeholder.com/400x200" },
    { id: 12, title: "Article 12", content: "This is the content of article 5.", link: "#", date: "2023-08-07", image: "https://via.placeholder.com/400x200" },
    { id: 14, title: "Article 13", content: "This is the content of article 6.", link: "#", date: "2023-08-09", image: "https://via.placeholder.com/400x200" },
    { id: 13, title: "Article 14", content: "This is the content of article 7.", link: "#", date: "2023-08-11", image: "https://via.placeholder.com/400x200" }
];



// Define the Articles component
function Articles() {
    const [sortOrder, setSortOrder] = useState('asc');
    const [currentPage, setCurrentPage] = useState(1);
    const articlesPerPage = 5;

    const sortedArticles = [...articles].sort((a, b) => {
        if (sortOrder === 'asc') {
            return new Date(a.date) - new Date(b.date);
        } else {
            return new Date(b.date) - new Date(a.date);
        }
    });

    // Logic for displaying current articles
    const indexOfLastArticle = currentPage * articlesPerPage;
    const indexOfFirstArticle = indexOfLastArticle - articlesPerPage;
    const currentArticles = sortedArticles.slice(indexOfFirstArticle, indexOfLastArticle);

    // Logic for page numbers
    const totalPages = Math.ceil(sortedArticles.length / articlesPerPage);

    return (
        <div className="container">
            <button className="sort-button" onClick={() => setSortOrder(sortOrder === 'asc' ? 'desc' : 'asc')}>
                Sort by date ({sortOrder === 'asc' ? 'ascending' : 'descending'})
            </button>
            {currentArticles.map(article => (
                <Article 
                    key={article.id} 
                    title={article.title} 
                    content={article.content} 
                    link={article.link} 
                    date={article.date} 
                    image={article.image} 
                />
            ))}
            <div className="pagination">
                <button onClick={() => setCurrentPage(currentPage - 1)} disabled={currentPage === 1}>Back</button>
                <button onClick={() => setCurrentPage(currentPage + 1)} disabled={currentPage === totalPages}>Next</button>
            </div>
        </div>
    );
}

// Export the Articles component
export default Articles;
