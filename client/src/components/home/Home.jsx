import './home.css';

export default function Home() {
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
                <h1>Latest Games</h1>

                {/* <!-- Display div: with information about every game (if any) --> */}
                <div className="game">
                    <div className="image-wrap">
                        <img src="./images/CoverFire.png"/>
                    </div>
                    <h3>Cover Fire</h3>
                    <div className="rating">
                        <span>☆</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span>
                    </div>
                    <div className="data-buttons">
                        <a href="#" className="btn details-btn">Details</a>
                    </div>
                </div>
                <div className="game">
                    <div className="image-wrap">
                        <img src="./images/ZombieLang.png"/>
                    </div>
                    <h3>Zombie Lang</h3>
                    <div className="rating">
                        <span>☆</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span>
                    </div>
                    <div className="data-buttons">
                        <a href="#" className="btn details-btn">Details</a>
                    </div>
                </div>
                <div className="game">
                    <div className="image-wrap">
                        <img src="./images/MineCraft.png"/>
                    </div>
                    <h3>MineCraft</h3>
                    <div className="rating">
                        <span>☆</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span>
                    </div>
                    <div className="data-buttons">
                        <a href="#" className="btn details-btn">Details</a>
                    </div>
                </div>

                {/* <!-- Display paragraph: If there is no games  --> */}
                <p className="no-articles">No games yet</p>
            </div>
        </section>

    );
}