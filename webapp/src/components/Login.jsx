import "../styles/Login.css"
import Alert from '@mui/material/Alert';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import { Outlet, Link, useNavigate } from "react-router-dom"
import { useState } from 'react'
import axios from "axios"
import { BASE_URL } from "../assets/baseUrl";

const Login = (props) => {

    const [username, setUsername] = props.username
    const [password, setPassword] = useState("")
    const [message, setMessage] = props.loginMsg
    const [messageSeverity, setMessageSeverity] = props.loginMsgSeverity
    // Bearer
    const [bearer, setBearer] = props.bearer

    const navigate = useNavigate()

    const handleSubmit = (e) => {

        e.preventDefault()

        // Login
        const endpoint = BASE_URL + "auth/login"

        const requestOptions = {
            auth: {
                username: username,
                password: password,
            }
        }

        // POST Request
        axios.post(endpoint, {}, requestOptions)
            // Upon Success
            .then(response => {
                // Reset any messages that may have been set on previous tries
                setMessage("")

                setUsername(username)
                setBearer("Bearer " + response.data)

                navigate("/pet-base/" + username)
            })
            // Upon failure
            .catch(error => {

                setMessage("Incorrect username or password")
                setMessageSeverity("error")
                console.log(bearer)
                console.log(error.response.data.message)
            }
            )
    }

    return (
        <>
            <Box
                className="box-form"
                component="form"
                sx={{
                    '& > :not(style)': { m: 1, width: '25ch' },
                }}
                noValidate
                autoComplete="off"
            >
                <TextField className="filled-basic" label="Username" variant="filled" value={username} onChange={e => setUsername(e.target.value)} />
                <TextField className="filled-basic" label="Password" variant="filled" value={password} onChange={e => setPassword(e.target.value)} type="password" />
                <br />
                <Button variant="contained" type="submit" onClick={handleSubmit}>Login</Button><br />
            </Box>
            <div className="alert">
                {message !== "" && <Alert severity={messageSeverity} className="alert">{message}</Alert>}
            </div>
            <p className="alert message">
                No account yet ? <Link to="/register">Register</Link>
            </p>
            <Outlet />
        </>
)
}

export default Login;