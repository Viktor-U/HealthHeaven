import requester from "./requester"

const BASE_URL = 'http://localhost:8080/auth';

export const login = (email, password) =>  requester.post(`${BASE_URL}/login`, { email, password } );

export const register = (firstName, lastName, email, password) =>  requester.post(`${BASE_URL}/register`, {firstName, lastName, email, password } );
