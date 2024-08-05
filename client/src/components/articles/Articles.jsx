import React, { useState } from 'react';
import './articles.css';
import Article from './article/Article';
import useGetAllArticles from '../../hooks/useArticles';
import { useAuthContext } from '../../contexts/AuthContext';



function Articles() {
    const [sortOrder, setSortOrder] = useState('asc');
    const [currentPage, setCurrentPage] = useState(1);
    const articlesPerPage = 5;
    const[articles] = useGetAllArticles();
    const {isAuthenticated} = useAuthContext();



    const sortedArticles = [...articles].sort((a, b) => {
        if (sortOrder === 'asc') {
            return new Date(a.timeOnPost) - new Date(b.timeOnPost);
        } else {
            return new Date(b.timeOnPost) - new Date(a.timeOnPost);
        }
    });


    const indexOfLastArticle = currentPage * articlesPerPage;
    const indexOfFirstArticle = indexOfLastArticle - articlesPerPage;
    const currentArticles = sortedArticles.slice(indexOfFirstArticle, indexOfLastArticle);

    const totalPages = Math.ceil(sortedArticles.length / articlesPerPage);

    return (
        <div className="container">
            <button className="sort-button" onClick={() => setSortOrder(sortOrder === 'asc' ? 'desc' : 'asc')}>
                Sort by date ({sortOrder === 'asc' ? 'ascending' : 'descending'})
            </button>
            {currentArticles.map(article => (
                <Article 
                    key={article.id} 
                    id={article.id}
                    title={article.title} 
                    date={article.timeOnPost} 
                    image={article.imageURL} 
                    author={article.author}
                    isAuthenticated={isAuthenticated}
                />
            ))}
            <div className="pagination">
                <button onClick={() => setCurrentPage(currentPage - 1)} disabled={currentPage === 1}>Back</button>
                <button onClick={() => setCurrentPage(currentPage + 1)} disabled={currentPage === totalPages}>Next</button>
            </div>
        </div>
    );
}

export default Articles;
