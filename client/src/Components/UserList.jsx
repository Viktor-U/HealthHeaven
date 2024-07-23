import React, { useEffect, useState } from 'react';
import axios from 'axios';

function UsersList() {
  const [users, setUsers] = useState([])

  useEffect(() => {
    axios.get('http://localhost:8080/api/auth/users')
      .then(response =>{
       setUsers(response.data)
    })
  }, []);

  const usersArr = users.map(user => <li key={user.id}>{user.firstName} {user.lastName}</li>)



  return (
    <div>
      <h1>UsersList</h1>
      <ul>
        {usersArr}
      </ul>
    </div>
  )
}

export default UsersList;