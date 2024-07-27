import* as request from './requester';

const BASE_URL = 'http://localhost:8080/doctors'

export const getAll = () => request.get(BASE_URL);

export const getOne = (gameId) => request.get(`${BASE_URL}/${gameId}`);


const gamesAPI = {
    getAll,
    getOne,
};

export default gamesAPI;