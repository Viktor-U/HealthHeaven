import* as request from './requester';

const BASE_URL = 'http://localhost:8080/doctors'

export const getAll = () => request.get(BASE_URL)
