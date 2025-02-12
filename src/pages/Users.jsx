import UserTable from "../components/Users/UserTable";

export default function Users({loggedIn}){

    const displayUserData = () => {
        if (loggedIn){
          return <UserTable/>
        } else{
          return <div>Log in to see users</div>
        }
    }

    return (
        <div>
            <h1>Users</h1>
            {displayUserData()}
            
        </div>
    )
}