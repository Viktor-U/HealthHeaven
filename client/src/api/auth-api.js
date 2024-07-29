import requester from "./requester"

const BASE_URL = 'http://localhost:8080/auth';

export const login = async (email, password) => {
    const authData = await requester.post(`${BASE_URL}/login`, { email, password } );

    return authData;
};