import {Link} from 'react-router-dom';

export default function DoctorListItem({
    id,
    name,
    specialization,
    profilePictureURL

}) {

    return (
        <div className="allDocotors">
            <div className="allDocotors-info">
                <img src={profilePictureURL}/>
                <h6>{name}</h6>
                <h2>{specialization}</h2>
                <Link to={`/doctors/${id}/details`} className="details-button">Details</Link>
            </div>
        </div>
    );
}