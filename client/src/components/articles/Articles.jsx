import React, { useState } from 'react';
import './articles.css';
import Article from './article/Article';
import useGetAllArticles from '../../hooks/useArticles';

// Define the articles array



// Define the Articles component
function Articles() {
    const [sortOrder, setSortOrder] = useState('asc');
    const [currentPage, setCurrentPage] = useState(1);
    const articlesPerPage = 5;
    const[articles, setArticles] = useGetAllArticles();

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
                    date={article.createdDate} 
                    image={article.imageURL} 
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
