import "../styles/PetBase.css"
import { Outlet, Link, useNavigate } from "react-router-dom";
import { useParams } from 'react-router-dom'
import { useEffect, useState } from 'react' 
import { BASE_URL } from "../assets/baseUrl";
import axios from "axios"
import Button from '@mui/material/Button';

const PetBase = (props) => {

    const params = useParams()
    const [ownerPetArray, setOwnerPetArray] = useState([])
    const [bearer, setBearer] = props.bearer

    const navigate = useNavigate()

    const loadOwnerPets = () => {
        const endpoint = BASE_URL + "ownerpet/find/owner/" + params.username
    
        const requestOptions = {
            headers:{
              "Authorization":bearer
            }
          }
    
        // GET Request
        axios.get(endpoint, requestOptions)
        .then(response=>{
           setOwnerPetArray(response.data)
           console.log(ownerPetArray)
        })
        .catch(error=>{
           console.log(error.response.data.message)
        })
    }
    
    // useEffect - run every time the component renders (dependencies are empty)
    useEffect(()=>{
        // Run GET request in here - works as a "refresh user list"
        loadOwnerPets()
    }, [])

    const gotoShop = () => {
        navigate("/shop/")
    }

    return  (
        <div className="pet-base">
            <h1>Hello,  {params.username}!</h1>
            {ownerPetArray.length > 0 ?
            <div className="container">
                {ownerPetArray.map((row => (
                <Link to={"/pet-view/" + row["id"]} className="box" key={row["id"]}>
                    {row["givenName"]}
                </Link>
                )))}
            </div> :
            <div className="message-container">
            <h1 className="message">
            You don't have any pets yet!
            </h1>
            <Button variant="contained" type="submit" className="button" onClick={gotoShop}>Shop pets</Button>
            </div>
            }
            <Outlet />
        </div>
    )
}

export default PetBase;