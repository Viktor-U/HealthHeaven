import* as request from './requester';

const BASE_URL = 'http://localhost:8080/shop';


export const getAll = () => request.get(`${BASE_URL}/items`);

export const getOne = (itemId) => request.get(`${BASE_URL}/items/${itemId}`);

export const getAllInCart = (userId) => request.post(`${BASE_URL}/cart`, userId);

export const putInCart = (order) => request.post(`${BASE_URL}/cart/add`, order);

export const deleteProductFromCart = (order) => request.del(`${BASE_URL}/cart/del`, order);

export const deleteAllProductsFromCart = (order) => request.del(`${BASE_URL}/cart/del/all`, order);



const shopAPI = {
    getAll,
    getOne,
    getAllInCart,
    putInCart,
    deleteProductFromCart,
    deleteAllProductsFromCart
};

export default shopAPI;