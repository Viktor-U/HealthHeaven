import requester from "./requester"

const BASE_URL = 'http://localhost:8080/doctors';

const buildUrl = (doctorId) => `${BASE_URL}/${doctorId}/comments`;

const create = async (doctorId, author, content) => requester.post(buildUrl(doctorId), { author, content });

export default {
    create,
}