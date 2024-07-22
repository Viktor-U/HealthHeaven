// src/services/UserService.js
import axios from 'axios';

const API_URL = 'http://localhost:8080/api/users';

class UserService {
    getUsers() {
        return axios.get(API_URL);
    }

    createUser(user) {
        return axios.post(API_URL, user);
    }

    getUserById(userId) {
        return axios.get(`${API_URL}/${userId}`);
    }

    updateUser(userId, user) {
        return axios.put(`${API_URL}/${userId}`, user);
    }

    deleteUser(userId) {
        return axios.delete(`${API_URL}/${userId}`);
    }
}

export default new UserService();
