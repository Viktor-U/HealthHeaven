import { Link } from 'react-router-dom';
import '../articles.css';

export default function Article({id, title, date, image, author, isAuthenticated}) {
    return (
        <div className="article">
            <img src={image} alt={title} className='articles-iamge'/>
            <h2>{title}</h2>
            <p className="date">{new Date(date).toLocaleDateString()}</p>
            <p>Author: {author}</p>
            {isAuthenticated
                ?<Link to={`/articles/${id}`} >Read more</Link>
                :<Link to={`/login`} >Read more</Link>
            }
            
        </div>
    );
}