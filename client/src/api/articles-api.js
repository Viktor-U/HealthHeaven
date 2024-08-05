import* as request from './requester';

const BASE_URL = 'http://localhost:8080/articles';


export const getAll = () => request.get(BASE_URL);

export const getOne = (articleId) => request.get(`${BASE_URL}/${articleId}`);

export const create = (articleData) => request.post(BASE_URL, articleData);

export const del = (articleId) => request.del(`${BASE_URL}/${articleId}`)


const articlesAPI = {
    getAll,
    getOne,
    create,
    del,
};

export default articlesAPI;