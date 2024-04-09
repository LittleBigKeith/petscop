import "../styles/Welcome.css"
import WelcomeDragon from "../assets/images/welcome-dragon.png"

const Welcome = () => {

    return (
        <> 
        <div className="welcomeContainer">
            <img src={WelcomeDragon} alt="Welcome image" className="welcomeImage" />
            <h1 className="title">Welcome Home</h1>
            <h2 className="subtitle">ただいま</h2>
        </div>
        </>
    )
} 

export default Welcome;