import { Link } from "react-router-dom";

export default function BestDoctors({
    id,
    name,
    profilePictureURL
}) {
    return(

        <div className="doctor">
            <div className="image-wrap">
                <img src={profilePictureURL}/>
            </div>
            <h3>{name}</h3>
            <div className="rating">
                <span>☆</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span>
            </div>
            <div className="data-buttons">
                <Link to={`/doctors/${id}/details`} className="btn details-btn">Details</Link>
            </div>
        </div>
    );
}