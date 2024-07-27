import {Link} from 'react-router-dom';

export default function GameListItem({
    id,
    name,
    specialization,
    profilePictureURL

}) {

    return (
        <div className="allGames">
            <div className="allGames-info">
                <img src={profilePictureURL}/>
                <h6>{name}</h6>
                <h2>{specialization}</h2>
                <Link to={`/games/${id}/details`} className="details-button">Details</Link>
            </div>
        </div>
    );
}