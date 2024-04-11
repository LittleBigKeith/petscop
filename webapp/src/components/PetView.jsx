import "../styles/PetView.css"
import { Outlet, Link } from "react-router-dom"
import { useParams } from 'react-router-dom'
import { BASE_URL } from "../assets/baseUrl";
import { useEffect } from 'react'
import { useState } from 'react'
import axios from 'axios'
const PetView = (props) => {

    const params = useParams()
    const [bearer, setBearer] = props.bearer
    const [ownerPet, setOwnerPet] = useState()

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
                console.log(response.data)
            })
            .catch(error => {
                console.log(error.response.data.message)
            })
    }

    useEffect(()=>{
        // Run GET request in here - works as a "refresh user list"
        loadOwnerPet()
    }, [])
    
    
    return  (
        <>
        <div className="pet-view">
            <h1>Pet View</h1>
            <div className="split">
                <div className="left">
                    <p className="box">
                    Pet image
                    </p>
                    <p className="box">
                    Type: {ownerPet != null? ownerPet.pet.defaultName : ""} <br />
                    Name: {ownerPet != null? ownerPet.givenName : ""} <br />
                    Health: {ownerPet != null? ownerPet.hungerPoint + "/" + ownerPet.pet.maxHungerPoint : ""} <br /> {ownerPet != null ? "(" + ownerPet.hungerPoint / ownerPet.pet.maxHungerPoint * 100 + "%)" : ""}
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