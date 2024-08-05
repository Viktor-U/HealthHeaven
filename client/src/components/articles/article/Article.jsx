import { Link } from 'react-router-dom';
import '../articles.css';

export default function Article({id, title, date, image, author}) {
    return (
        <div className="article">
            <img src={image} alt={title} className='articles-iamge'/>
            <h2>{title}</h2>
            <p className="date">{new Date(date).toLocaleDateString()}</p>
            <p>Author: {author}</p>
            <Link to={`/articles/${id}`} >Read more</Link>
        </div>
    );
}