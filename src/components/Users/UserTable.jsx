import { useEffect, useState } from "react";
import Table from 'react-bootstrap/Table';
import { fetchUsers } from "../../services/UserService";

export default function UserTable() {
    const [searchTerm, setSearchTerm] = useState('');
    const [users, setUsers] = useState([]);

    useEffect(() => {
      getUsers();
      }, [])

    const getUsers = async() => {
      const data = await fetchUsers()
      setUsers(data);
    }
  
    const handleSearchChange = (event) => {
      setSearchTerm(event.target.value);
    };
  
    const filteredUsers = users.filter((user) =>
      user.name.toLowerCase().includes(searchTerm.toLowerCase())
    );
  
    return (
      

      <div>
        <input
          type="text"
          placeholder="Search..."
          value={searchTerm}
          onChange={handleSearchChange}
        />
      
      <Table striped bordered hover>
      <thead>
        <tr>
        <th>#</th>
          <th>Username</th>
          <th>Role</th>
        </tr>
      </thead>
      <tbody>
      {
      filteredUsers.map((user, index) => (
                <tr key={index}>
                  <td>{user.id}</td>
                    <td>{user.name}</td>
                    <td>{user.userRole}</td>
                </tr>
            ))
    }
      </tbody>
    </Table>
      </div>
    );
  }