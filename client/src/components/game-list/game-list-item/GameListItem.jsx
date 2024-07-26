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
                <a href="#" className="details-button">Details</a>
            </div>
        </div>
    );
}