import "../styles/PetView.css"
import { Outlet, Link } from "react-router-dom"
import { useParams } from 'react-router-dom'
import { BASE_URL } from "../assets/baseUrl";
import { useEffect } from 'react'
import { useState } from 'react'
import axios from 'axios'
import moment from 'moment';
const PetView = (props) => {

    const params = useParams()
    const [bearer, setBearer] = props.bearer
    const [currentHunger, setCurrentHunger] = props.currentHunger
    const [ownerPet, setOwnerPet] = useState()
    const [imageSrc, setimageSrc] = useState("")

    const endpoint = BASE_URL + "ownerpet/find/id/" + params.id

    const loadOwnerPet = () => {
        const requestOptions = {
            headers: {
                "Authorization": bearer
            }
        }

        // GET Request
        axios.get(endpoint, requestOptions)
            .then(response => {
                setOwnerPet(response.data)
                setCurrentHunger(response.data.hungerPoint)
                setimageSrc("/src/assets/images/" + response.data.pet.defaultName + ".png")
            })
            .catch(error => {
                console.log(error.response.data.message)
            })
    }

    useEffect(() => {
        // Run GET request in here - works as a "refresh user list"
        loadOwnerPet()
    }, [])


    return (
        <>
            <div className="pet-view">
                <h1>{ownerPet != null ? ownerPet.pet.defaultName + ": " + ownerPet.givenName : ""}</h1>
                <div className="split">
                    <div className="left">
                        <img src={imageSrc} className="box" />
                        <p className="box">
                            Type: {ownerPet != null ? ownerPet.pet.defaultName : ""} <br />
                            Name: {ownerPet != null ? ownerPet.givenName : ""} <br />
                            Hunger: {ownerPet != null ? currentHunger + "/" + ownerPet.pet.maxHungerPoint : ""} <br /> {ownerPet != null ? "(" + Math.round(currentHunger / ownerPet.pet.maxHungerPoint * 100) + "%)" : ""}<br />
                            Age: {ownerPet != null ? Math.floor(Math.abs(new Date() - new Date(ownerPet.birthDate)) / 86400000) + " days" : ""}
                        </p>
                    </div>
                    <div className="right">
                        <div className="menu">
                            <Link to="" className="box">
                                Feed
                            </Link>
                            <Link to="quest" className="box">
                                Quest
                            </Link>
                        </div>
                        <Outlet />
                    </div>
                </div>
            </div>
        </>
    )
}

export default PetView;