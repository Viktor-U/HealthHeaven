import { Link } from "react-router-dom";
import { useAuthContext } from "../../../contexts/AuthContext";
import { useTranslation } from "react-i18next";

export default function BestDoctors({
    id,
    name,
    imageURL
}) {
    const {isAuthenticated} = useAuthContext();
    const { t } = useTranslation();

    return(
        

        <div className="doctor">
            <div className="image-wrap">
                <img src={imageURL}/>
            </div>
            <h3>{name}</h3>
            <div className="rating">
                <span>☆</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span>
            </div>
            <div className="data-buttons">
                {isAuthenticated
                    ? <Link to={`/items/${id}/details`} className="btn details-btn">{t('details')}</Link>
                    : <Link to={`/login`} className="btn details-btn">{t('details')}</Link>
                }
               
            </div>
        </div>
    );
}