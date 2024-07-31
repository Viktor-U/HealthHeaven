import* as request from './requester';

const BASE_URL = 'http://localhost:8080/doctors'

export const getAll = () => request.get(BASE_URL);

export const getOne = (gameId) => request.get(`${BASE_URL}/${gameId}`);


export const create = (gameData) => request.post(BASE_URL, gameData);

const gamesAPI = {
    getAll,
    getOne,
    create
};

export default gamesAPI;