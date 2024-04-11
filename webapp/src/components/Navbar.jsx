import "../styles/Navbar.css"
import { Outlet, Link } from "react-router-dom";
import { useState } from "react";
import { useEffect } from 'react';
import { BASE_URL } from "../assets/baseUrl";
import axios from "axios"

const Navbar = (props) => {

    const [username, setUsername] = props.username
    const [bearer, setBearer] = props.bearer
    const [experience, setExperience] = props.experience
    const [gold, setGold] = props.gold

    const logout = () => {
        setBearer("")
    }

    const loadStats = () => {
        const endpoint = BASE_URL + "owner/find/name/" + username

        const requestOptions = {
            headers:{
                "Authorization":bearer
            }
        }

        // GET Request
        axios.get(endpoint, requestOptions)
        .then(response=>{
            console.log(response.data)
            setExperience(response.data.experience)
            setGold(response.data.gold)
        })
        .catch(error=>{
            console.log(error.response.data.message)
        })
    }

     // useEffect - run every time the component renders (dependencies are empty)
    useEffect(()=>{
        // Run GET request in here - works as a "refresh user list"
        loadStats()
    }, [])

    return (
        <>
        {bearer.length > 0 ? 
        <nav className="navbar">
            <div className="stats">
                Gold: {gold}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Exp: {experience}
            </div>
            <div className="navigation">
                <ul className="menu">
                    <li className="link">
                        <Link to={"/pet-base/" + username} className="text">Pet Base</Link>
                    </li>
                    <li className="link">
                        <Link to="/shop" className="text">Shop</Link>
                    </li>
                    <li className="link">
                        <Link to="/" className="text" onClick={logout}>Log out</Link>
                    </li>
                </ul>
            </div>
        </nav> : 
        <></>}

        <Outlet />
        </>
    )
}

export default Navbar;