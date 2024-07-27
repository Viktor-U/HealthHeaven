import { Link } from "react-router-dom";

export default function LatestGame({
    id,
    name,
    profilePictureURL
}) {
    return(

        <div className="game">
            <div className="image-wrap">
                <img src={profilePictureURL}/>
            </div>
            <h3>{name}</h3>
            <div className="rating">
                <span>☆</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span>
            </div>
            <div className="data-buttons">
                <Link to={`/games/${id}/details`} className="btn details-btn">Details</Link>
            </div>
        </div>
    );
}