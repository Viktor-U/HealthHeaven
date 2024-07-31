import { useEffect, useState } from 'react';
import './home.css';
import doctorsAPI from '../../api/games-api';
import BestDoctors from './best-doctors/BestDoctors';

export default function Home() {
    const [bestDoctors, setBestDoctors] = useState([]);

    useEffect(() => {
        (async () => {
            //TODO: modify to fetch latest doctors 
            const result = await doctorsAPI.getAll();

            setBestDoctors(result.reverse().slice(0, 3));
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
                    {bestDoctors.length > 0
                        ? bestDoctors.map(doctor => <BestDoctors key={doctor.id} {...doctor}/>)
                        : <p className="no-articles">No doctors yet</p>
                    }
                </div>
            </div>
        </section>

    );
}