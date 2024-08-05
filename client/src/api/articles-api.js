import* as request from './requester';

const BASE_URL = 'http://localhost:8080/articles';


export const getAll = () => request.get(BASE_URL);

export const getOne = (articleId) => request.get(`${BASE_URL}/${articleId}`);

// export const getAllInCart = (userId) => request.post(`${BASE_URL}/cart`, userId);

// export const putInCart = (order) => request.post(`${BASE_URL}/cart/add`, order);

// export const deleteProductFromCart = (order) => request.del(`${BASE_URL}/cart/del`, order);


// export const create = (doctorData) => request.post(BASE_URL, doctorData);

// export const deleteDoctor = (doctorId) => request.del(`${BASE_URL}/${doctorId}`)

// export const edit  = (doctorData) => request.post(`${BASE_URL}/edit`, doctorData)

const articlesAPI = {
    getAll,
    getOne,
    // getAllInCart,
    // putInCart,
    // deleteProductFromCart,
    // create,
    // edit,
};

export default articlesAPI;