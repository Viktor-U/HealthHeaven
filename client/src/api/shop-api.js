import* as request from './requester';

const BASE_URL = 'http://localhost:8080/shop/items';


export const getAll = () => request.get(BASE_URL);

// export const getOne = (doctorId) => request.get(`${BASE_URL}/${doctorId}`);


// export const create = (doctorData) => request.post(BASE_URL, doctorData);

// export const deleteDoctor = (doctorId) => request.del(`${BASE_URL}/${doctorId}`)

// export const edit  = (doctorData) => request.post(`${BASE_URL}/edit`, doctorData)

const shopAPI = {
    getAll,
    // getOne,
    // create,
    // deleteDoctor,
    // edit,
};

export default shopAPI;