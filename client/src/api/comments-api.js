import requester from "./requester"

const BASE_URL = 'http://localhost:8080/doctors';

const buildUrl = (gameId) => `${BASE_URL}/${gameId}/comments`;

const create = async (gameId, author, content) => requester.post(buildUrl(gameId), { author, content });

export default {
    create,
}