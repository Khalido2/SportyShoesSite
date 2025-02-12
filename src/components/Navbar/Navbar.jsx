import React from "react"
import Logo from "../../assets/Shoe-Logo.png"
import AccountIcon from "../../assets/Account-Icon.png"
import ShoppingCartIcon from "../../assets/shopping-bag.png"

const Menus = [
    {
        id: 1,
        name: "Shoes",
        categoryId: 2,
        link: "/"
    },
    {
        id: 2,
        name: "Users",
        categoryId: 3,
        link: "/users"
    },
    {
        id: 3,
        name: "Payment Records",
        categoryId: 4,
        link: "/payments"
    },
];

const Navbar = ({logIn, loggedIn, logOut}) => {

    const logInTriggered = (e) => {

        if(!loggedIn){
            let username = prompt("Please enter your username:");
            let password = prompt("Please enter your password:");
    
            if (username && password) {
                logIn(username, password)
            } else {
                alert("Username and password cannot be empty.");
            }
        }else{
            if (confirm("Would you like to log out?")){
                logOut()
                alert("You have succesfully logged out.");
            }
        }
        
    }
    
    return (
        <div className="navbar">

{/* Logo section */}

        <div className="float-container">
            
            <img src={Logo} className="logo-image"/>
            <span className="logo-text">Sporty Shoes</span>
            
        </div>

{/* Links section */}
        <div className="float-container">
                <ul className="navbar-menu">
                    {Menus.map((data, index) => (
                        <li key={index}>
                            <a href={data.link}>
                                <h1 className="navbar-menu-header">{data.name}</h1>
                            </a>
                        </li>
                    ))}
                </ul>
                <button className="navbar" type="button" onClick={logInTriggered}>
                    <img src={AccountIcon} className="navbar-icon"/>
                </button>

                <button className="navbar" type="button">
                    <img src={ShoppingCartIcon} className="navbar-icon"/>
                </button>
                </div>  
          

        </div>
    );
}

export default Navbar