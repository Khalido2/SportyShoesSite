const USER_ROOT_PATH = "http://localhost:8091/users"

export async function fetchUsers() {
    try {
      const response = await fetch(USER_ROOT_PATH)

      if (response.ok) {
        return await response.json();
      }
    } catch (error) {
      console.error("Failed to fetch users")
    }

    return []
}

export async function authenticate(username, password) {
    try {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({name: username, password: password})
        };

        const response = await fetch("http://localhost:8091/authenticate", requestOptions);

        const data = await response.text();
        alert(data)
        
        if(response.ok){
            return 1
        }
    
    } catch (error) {
        alert("Login failed. Please try again.");
    }

    return 0
}
