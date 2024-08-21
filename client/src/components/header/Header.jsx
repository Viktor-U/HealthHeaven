import {Link} from "react-router-dom";
import { useAuthContext } from "../../contexts/AuthContext";
import { useTranslation } from "react-i18next";
import './header.css';
import { useState } from "react";


export default function Header(){
    const {isAuthenticated, role, email} = useAuthContext();
    const { t, i18n } = useTranslation();
    const [activeLanguage, setActiveLanguage] = useState(i18n.language);

    const changeLanguage = (lng) => {
        i18n.changeLanguage(lng);
        setActiveLanguage(lng);
    };

    return(
        <header>
            <h1><Link className="home" to="/">HealthHeaven</Link></h1>
         
            <button className={`change-language ${activeLanguage === 'en' ? 'active' : ''}`} onClick={() => changeLanguage('en')}>English</button>
            <button className={`change-language ${activeLanguage === 'bg' ? 'active' : ''}`} onClick={() => changeLanguage('bg')}>Български</button>
      
            <nav>
            <Link to="/doctors">{t('all_doctors')}</Link>
                <Link to="/shop">{t('shop')}</Link>
                <Link to="/articles">{t('articles')}</Link>

                {role === "ADMIN" && (
                    <Link to="/doctors/create">{t('create_doctor')}</Link>
                )}

                {isAuthenticated ? (
                    <div id="user">
                        <Link to="/cart">{t('my_cart')}</Link>
                        <Link to="/logout">{t('logout')}</Link>
                    </div>
                ) : (
                    <div id="guest">
                        <Link to="/login">{t('login')}</Link>
                        <Link to="/register">{t('register')}</Link>
                    </div>
                )}
                
            </nav>
            
        </header>
    );
}
