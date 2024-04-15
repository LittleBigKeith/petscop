import "../styles/Welcome.css"
import WelcomeDragon from "../assets/images/welcome-dragon.png"
import { Outlet } from "react-router-dom";

const Welcome = () => {

    return (
        <>
            <div className="welcome">
                <img src={WelcomeDragon} alt="Welcome image" className="welcomeImage" />
                <h1 className="title">Welcome Home</h1>
                <h3 className="subtitle">おかえり</h3>
            </div>
            <Outlet />
        </>
    )
}

export default Welcome;