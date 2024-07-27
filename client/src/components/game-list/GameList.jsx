import { useEffect, useState } from 'react';
import * as gamesAPI from '../../api/games-api';
import GameListItem from './game-list-item/GameListItem';

export default function GameList(){
    const [games, setGames] = useState([]);

    useEffect(() => {
        gamesAPI.getAll()
            .then(result => setGames(result));

    }, [])



    return(

        <section id="catalog-page">
            <h1>All Doctors</h1>

          {games.length > 0 
                ? games.map(game => <GameListItem key={game.id} {...game} />)
                : <h3 className="no-articles">No doctors on work yet</h3>
          }
        </section>
   
    );
}