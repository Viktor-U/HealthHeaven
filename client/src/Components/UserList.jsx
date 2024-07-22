// src/components/UserList.js
import React, { useState, useEffect } from 'react';
import UserService from '../service/UserService';


const UserList = () => {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        UserService.getUsers().then((response) => {
            setUsers(response.data);
        });
    }, []);

    return (
        <div>
            <h2>User List</h2>
            <ul>
                {users.map((user) => (
                    <li key={user.id}>{user.name} - {user.email}</li>
                ))}
            </ul>
        </div>
    );
};

export default UserList;
