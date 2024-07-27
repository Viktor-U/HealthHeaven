import { useEffect, useState } from 'react';
import './home.css';
import gamesAPI from '../../api/games-api';
import LatestGame from './latest-games/LatestGame';

export default function Home() {
    const [latestGames, setLatestGames] = useState([]);

    useEffect(() => {
        (async () => {
            //TODO: modify to fetch latest games 
            const result = await gamesAPI.getAll();

            setLatestGames(result.reverse().slice(0, 3));
        })();
    }, [])


    return (
        <section id="welcome-world">

            <div className='doctors'>

                <img src="./images/doctor_img2.png" alt="hero"/>

                <div className="welcome-message">
                    <h2>your health is our success</h2>
                    <h3>Health Heavan</h3>
                </div>

                <img src="./images/doctor_img.png" alt="hero"/>

            </div>
            <div id="home-page">
                <h1>Best Doctors</h1>

                <div className='latest-doctors'>
                    {latestGames.length > 0
                        ? latestGames.map(game => <LatestGame key={game.id} {...game}/>)
                        : <p className="no-articles">No games yet</p>
                    }
                </div>
            </div>
        </section>

    );
}