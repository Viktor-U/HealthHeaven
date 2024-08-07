import requester from "./requester"

const BASE_URL = 'http://localhost:8080';

const buildDoctorUrl = (doctorId, commentId) => {
    
    let url = `${BASE_URL}/doctors/${doctorId}/comments`;
    if (commentId) {
        url += `?commentId=${commentId}`;
    }
    return url;
};
const buildArticleUrl = (articleId, commentId) => {
    
    let url = `${BASE_URL}/articles/${articleId}/comments`;
    if (commentId) {
        url += `?commentId=${commentId}`;
    }
    return url;
};
const createDoctorComment = async (doctorId, author, content) => requester.post(buildDoctorUrl(doctorId), { author, content });

const deleteDoctorComment = async (doctorId, commentId) => requester.del(buildDoctorUrl(doctorId, commentId));

const createArticleComment = async (articleId, author, content) => requester.post(buildArticleUrl(articleId), { author, content });

const deleteArticleComment = async (articleId, commentId) => requester.del(buildArticleUrl(articleId, commentId));

export default {
    createDoctorComment,
    deleteDoctorComment,
    createArticleComment,
    deleteArticleComment
}