import requester from "./requester"

const BASE_URL = 'http://localhost:8080/doctors';

const buildUrl = (doctorId, commentId) => {
    let url = `${BASE_URL}/${doctorId}/comments`;
    if (commentId) {
        url += `?commentId=${commentId}`;
    }
    return url;
};
const create = async (doctorId, author, content) => requester.post(buildUrl(doctorId), { author, content });

const del = async (doctorId, commentId) => requester.del(buildUrl(doctorId, commentId));

export default {
    create,
    del
}