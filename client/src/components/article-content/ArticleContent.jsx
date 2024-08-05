// ArticleContent.jsx
import React, { useState } from 'react';
import './article-content.css';
import { useGetOneArticle } from '../../hooks/useArticles';
import { useParams } from 'react-router-dom';
import { useAuthContext } from '../../contexts/AuthContext';
import commentsApi from '../../api/comments-api';
import { format } from 'date-fns';



export default function ArticleContent (){

  const [comment, setComment] = useState('');
  const {articleId} = useParams();
  const [article, setArticle] = useGetOneArticle(articleId);
  const {email, isAuthenticated, role} = useAuthContext();


  

  const commentSubmitHandler = async (e) => {
      e.preventDefault();
      
      const newComment =  await commentsApi.createArticleComment(articleId, email, comment);
      
      setArticle(prevState => ({
          ...prevState,
          comments: [...prevState.comments, newComment]
      }));
      (article.comments).map((comment) => console.log(comment.author))
      
      setComment("");
  }
  const commentDeleteHandler = async (commentId) => {
    await commentsApi.deleteArticleComment(articleId, commentId);

    setArticle(prevState => ({
        ...prevState,
        comments: prevState.comments.filter(comment => comment.id !== commentId)
    }));
}


  return (
    <div className="article-container">
      <img src={article.imageURL} alt="Article" className="article-image" />
      <h1 className="article-title">{article.title}</h1>
      <p className="article-author">By {article.author} on {new Date(article.timeOnPost).toLocaleDateString()}</p>
      <p className="article-content">{article.content}</p>
      <div className="comment-form">
          <textarea
            value={comment}
            onChange={(e) => setComment(e.target.value)}
            placeholder="Write your comment..."
            rows="4"
            className="comment-textarea"
          ></textarea>
          <button onClick={commentSubmitHandler} className="comment-button">
            Post Comment
          </button>
        </div>
      <div className="comments-section">
        <h2>Comments</h2>
        <ul className="comments-list">
          {Object.keys(article.comments || {}).length > 0
            ? Object.values(article.comments).map((comment) => (
              <li key={comment.id} className="comment-item">
                  <p>{comment.author}: {comment.content}</p>
                  <div >
                      <span className="data data-article"> {format(comment.timeOnPost, "yyyy MMMM d,  H:MM")}</span>
                  </div>
                  <div className="del-article-comment">
                  {comment.authorEmail === email || role === "ADMIN"
                      ?<button onClick={() => commentDeleteHandler(comment.id)} className='del-button'>Delete</button>
                      :<></>
                  }
                  </div>
              </li>
          ))
          :<p className="no-comment">No comments yet.</p>   
        }

        </ul>

      </div>
    </div>
  );
};



