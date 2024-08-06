import { useEffect, useState } from 'react';
import './home.css';
import BestDoctors from './best-doctors/BestItems';
import shopAPI from '../../api/shop-api';
import { useTranslation } from 'react-i18next';

export default function Home() {
    const [bestItems, setBestItems] = useState([]);
    const { t } = useTranslation();


    useEffect(() => {
        (async () => {
            const result = await shopAPI.getAll();

            const filteredItems = result.filter(item => item.rating === 5);
            const bestItems = filteredItems.reverse().slice(0, 3);       
            setBestItems(bestItems);
        })();
    }, [])


    return (
        <section id="welcome-world">

            <div className='doctors'>

                <img src="./images/doctor_img2.png" alt="hero"/>

                <div className="welcome-message">
                    <h2>{t('your_health_is_our_success')}</h2>
                    <h3>{t('health_heaven')}</h3>
                </div>

                <img src="./images/doctor_img.png" alt="hero"/>

            </div>
            <div id="home-page">
                <h1>{t('best_items')}</h1>

                <div className='best-items'>
                    {bestItems.length > 0
                        ? bestItems.map(item => <BestDoctors key={item.id} {...item}/>)
                        : <p className="no-articles">{t('no_items_yet')}</p>
                    }
                </div>
            </div>
        </section>

    );
}