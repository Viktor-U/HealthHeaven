import {Link} from 'react-router-dom';
import { useAuthContext } from '../../../contexts/AuthContext';
import { useTranslation } from 'react-i18next';

export default function DoctorListItem({
    id,
    name,
    specialization,
    profilePictureURL

}) {
    const {isAuthenticated} = useAuthContext();
    const { t } = useTranslation();

    return (
        <div className="allDocotors">
            <div className="allDocotors-info">
                <img src={profilePictureURL}/>
                <h6>{name}</h6>
                <h2>{specialization}</h2>
                {isAuthenticated
                    ?<Link to={`/doctors/${id}/details`} className="details-button">{t('details')}</Link>
                    :<Link to={`/login`} className="details-button">{t('details')}</Link>
                }
                
            </div>
        </div>
    );
}