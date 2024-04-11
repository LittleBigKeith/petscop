import "../styles/Register.css"
import Alert from '@mui/material/Alert';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import { Outlet, Link, useNavigate } from "react-router-dom"
import { useState } from 'react'
import axios from "axios"
import { BASE_URL } from "../assets/baseUrl";
import moment from 'moment';

const Register = (props) => {

    const [username, setUsername] = props.username
    const [password, setPassword] = useState("")
    const [message, setMessage] = useState("")
    // Bearer
    const [bearer, setBearer] = props.bearer

    const navigate = useNavigate()
    
    async function handleSubmit(e) {

        e.preventDefault();
        console.log("submit");

        const endpointRegister = BASE_URL + "owner/create"
        const requestBody = {
            username: username,
            password: password,
            role: "User",
            cakeDate: moment().format("yyyy-MM-DD"),
            gold: 2000
        }
        try {
            await axios.post(endpointRegister, requestBody)
                .then(response => {
                    // Reset any messages that may have been set on previous tries
                    setMessage("")
                    console.log("Submitted!")
                    console.log("Username: " + username)

                    setUsername(username)
                    setPassword(password)
                })
                // Upon failure
                .catch(error => {
                    // Can target specific HTTP error codes and display appropriate messages
                    if (error.response.status === 409) {
                        setMessage("User already exists")
                        // This will print entire error object out to console. You can see what fields to access from here
                        console.log(error)
                    } else if (error.response.status === 422) {
                        if (username.length === 0) {
                            setMessage("Empty username")
                        } else if (username.indexOf(" ") > -1) {
                            setMessage("Username must not contain space")
                        } else {
                            setMessage("Password too short (>= 8 characters)")
                        }
                    } else {
                    console.log(error.response.data.message)
                    }
                    throw error
                }
            )
        
            // Login
            const endpointLogin = BASE_URL + "auth/login"

            const requestOptions = {
                auth: {
                username: username,
                password: password,
                }
            }
            
            // POST Request
            axios.post(endpointLogin, {}, requestOptions)
                // Upon Success
                .then(response => {
                // Reset any messages that may have been set on previous tries
                setMessage("")
                console.log("Log in Success!")
                console.log("Username: " + username)

                setBearer("Bearer "+response.data)
                
                console.log("Bearer "+response.data)
                navigate("/pet-base/" + username)
                })
                // Upon failure
                .catch(error => {
                // Can target specific HTTP error codes and display appropriate messages
                    setMessage("Incorrect username or password")
                    console.log(username)
                    console.log(password)
                    console.log(error.response.data.message)
                    
                }
            )
        } catch {
            
        }
            
    }

    return (
        <>
        <Box
            className="box-form"
            component="form"
            onSubmit={handleSubmit}
            sx={{
                '& > :not(style)': { m: 1, width: '25ch' },
            }}
            noValidate
            autoComplete="off"
        >
            <TextField className="filled-basic" label="Username" variant="filled" value={username} onChange={e => setUsername(e.target.value)}/>
            <TextField className="filled-basic" label="Password" variant="filled" value={password} onChange={e => setPassword(e.target.value)} />
            <br />
            <Button variant="contained" type="submit" onClick={handleSubmit}>Register</Button><br />
        </Box>
        <div className="alert">
        {message !== "" && <Alert severity="error" className="alert">{message}</Alert>}
        </div>
        <div className="alert message">
        Already have an account?   <Link to="/">Login</Link>
        </div>
        
        <Outlet />
        </>
    )
}

export default Register;