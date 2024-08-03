import {Link} from 'react-router-dom';
import { useAuthContext } from '../../../contexts/AuthContext';

export default function DoctorListItem({
    id,
    name,
    specialization,
    profilePictureURL

}) {
    const {isAuthenticated} = useAuthContext();


    return (
        <div className="allDocotors">
            <div className="allDocotors-info">
                <img src={profilePictureURL}/>
                <h6>{name}</h6>
                <h2>{specialization}</h2>
                {isAuthenticated
                    ?<Link to={`/doctors/${id}/details`} className="details-button">Details</Link>
                    :<Link to={`/login`} className="details-button">Details</Link>
                }
                
            </div>
        </div>
    );
}