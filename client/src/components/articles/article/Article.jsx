import '../articles.css';

export default function Article({ title, content, link, date, image }) {
    return (
        <div className="article">
            <img src={image} alt={title} />
            <h2>{title}</h2>
            <p className="date">{new Date(date).toLocaleDateString()}</p>
            <p>{content}</p>
            <a href={link}>Read more</a>
        </div>
    );
}