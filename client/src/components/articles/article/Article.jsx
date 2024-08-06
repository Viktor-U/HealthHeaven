import { Link } from 'react-router-dom';
import '../articles.css';
import { useTranslation } from 'react-i18next';

export default function Article({id, title, date, image, author, isAuthenticated}) {

    const { t } = useTranslation();

    return (
        <div className="article">
            <img src={image} alt={title} className='articles-iamge'/>
            <h2>{title}</h2>
            <p className="date">{new Date(date).toLocaleDateString()}</p>
            <p>{t('author')}: {author}</p>
            {isAuthenticated
                ?<Link to={`/articles/${id}`} >{t('read_more')}</Link>
                :<Link to={`/login`} >{t('read_more')}</Link>
            }
            
        </div>
    );
}