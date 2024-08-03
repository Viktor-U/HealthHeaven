import* as request from './requester';

const BASE_URL = 'http://localhost:8080/shop';


export const getAll = () => request.get(`${BASE_URL}/items`);

export const getOne = (itemId) => request.get(`${BASE_URL}/items/${itemId}`);

export const getAllInCart = (userId) => request.post(`${BASE_URL}/cart`, userId);

export const putInCart = (order) => request.post(`${BASE_URL}/cart/add`, order);

export const deleteProductFromCart = (productId) => request.del(`${BASE_URL}/cart/add`, productId);


// export const create = (doctorData) => request.post(BASE_URL, doctorData);

// export const deleteDoctor = (doctorId) => request.del(`${BASE_URL}/${doctorId}`)

// export const edit  = (doctorData) => request.post(`${BASE_URL}/edit`, doctorData)

const shopAPI = {
    getAll,
    getOne,
    getAllInCart,
    putInCart,
    deleteProductFromCart,
    // create,
    // edit,
};

export default shopAPI;