import* as request from './requester';

const BASE_URL = 'http://localhost:8080/doctors'

export const getAll = () => request.get(BASE_URL);

export const getOne = (doctorId) => request.get(`${BASE_URL}/${doctorId}`);


export const create = (doctorData) => request.post(BASE_URL, doctorData);

const doctorsAPI = {
    getAll,
    getOne,
    create
};

export default doctorsAPI;